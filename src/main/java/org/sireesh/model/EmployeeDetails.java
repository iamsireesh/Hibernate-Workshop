package org.sireesh.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
public class EmployeeDetails {
	@Id
	@GeneratedValue
	private int Id;
	@Column(name = "EMP_NAME")
	private String name;
	@Embedded
	// optional if we have @Embeddable annotation on Address class
	private Address address;
	private String phone;
	@Temporal(TemporalType.DATE)
	private Date dob;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public static void main(String[] args) {
		EmployeeDetails emp=new EmployeeDetails();
		emp.setName("SIREESH");
		emp.setPhone("9XXXXXXXX9");
		emp.setDob(new Date());
		
		Address address=new Address();
		address.setCity("Hyd");
		address.setPincode(500081);
		address.setState("TS");
		address.setStreet("Galli");
		
		//set address reference
		emp.setAddress(address);
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		session.close();
	}

}
