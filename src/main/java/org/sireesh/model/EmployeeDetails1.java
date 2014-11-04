package org.sireesh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.cfg.Configuration;
@Entity
public class EmployeeDetails1 {
	@Id
	private int Id;
	@Column(name = "EMP_NAME")
	private String name;
	@ElementCollection
	@JoinTable(name="ADDRESS",joinColumns=@JoinColumn(name="USER_ID"))
	//The below annotations are hibernate annotations not JPA annotations
	                 //can be any name      
	@GenericGenerator(name="hilo-generator",strategy="hilo")         //name should match
	@CollectionId(columns={@Column(name="ADDRESS_ID")}, generator = "hilo-generator", type = @Type(type="int"))
	private List<Address> address=new ArrayList<Address>();

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public static void main(String[] args) {
		EmployeeDetails1 emp = new EmployeeDetails1();
		emp.setName("SIREESH");
		emp.setId(1);

		Address homeAddress = new Address();
		homeAddress.setCity("Hyd");
		homeAddress.setPincode(500081);
		homeAddress.setState("TS");
		homeAddress.setStreet("Galli");

		Address officeAddress = new Address();
		officeAddress.setCity("Bangalore");
		officeAddress.setPincode(500081);
		officeAddress.setState("KA");
		officeAddress.setStreet("Infantry");

		emp.getAddress().add(homeAddress);
		emp.getAddress().add(officeAddress);
		// set address reference
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		session.close();
		
		session=sessionFactory.openSession();
		
		//Success scenario
		emp=null;
		emp=(EmployeeDetails1)session.get(EmployeeDetails1.class, 1);
		System.out.println(emp.getId());
		System.out.println(emp.getName());
		
		//If the fetch type is eager, Hibenate will load list of addresses (sub class details)
		//even before accessing it
		//By default if we doesn't config anything hibernate default configuration takes it as Lazy loading
		System.out.println(emp.getAddress().size());
		session.close();
		
		//Output
		//Hibernate: select employeede0_.Id as Id1_3_0_, employeede0_.EMP_NAME as EMP_NAME2_3_0_ from EmployeeDetails1 employeede0_ where employeede0_.Id=?
		//1
		//SIREESH
		//(Querying to child table after calling or accessing it)
		//Hibernate: select address0_.USER_ID as USER_ID1_3_0_, address0_.CITY as CITY2_0_0_, address0_.pincode as pincode3_0_0_, address0_.STATE as STATE4_0_0_, address0_.STREET as STREET5_0_0_, address0_.ADDRESS_ID as ADDRESS_6_0_ from ADDRESS address0_ where address0_.USER_ID=?
		//2 (size of addresses)
		
		
		
		System.out.println("Failure Scenario");
		session=sessionFactory.openSession();
		//Failue scenario
		emp=null;
		emp=(EmployeeDetails1)session.get(EmployeeDetails1.class, 1);
		System.out.println(emp.getId());
		System.out.println(emp.getName());
		//closing the session before accessing the address (sub class object)
		session.close();
		//Calling addresses after closing the session when fetch type is LAZY
		//If the fetch type is EAGER we won't get any error, As it will load before calling it.
		System.out.println(emp.getAddress().size());
		
		//Output
		//Hibernate: select employeede0_.Id as Id1_3_0_, employeede0_.EMP_NAME as EMP_NAME2_3_0_ from EmployeeDetails1 employeede0_ where employeede0_.Id=?
		//1
		//SIREESH
		//Exception in thread "main" org.hibernate.LazyInitializationException: 
		//failed to lazily initialize a collection of role: 
		//org.sireesh.model.EmployeeDetails1.address, could not initialize proxy - no Session
		
	}
}
