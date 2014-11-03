package org.sireesh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
	@GeneratedValue
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
	}
}
