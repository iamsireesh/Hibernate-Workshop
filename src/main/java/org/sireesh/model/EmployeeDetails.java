package org.sireesh.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
	@AttributeOverrides({@AttributeOverride(name="pincode", column = @Column(name="HOME_PINCODE"))})
	// optional if we have @Embeddable annotation on Address class
	private Address homeAddress;
	@Embedded
	@AttributeOverrides({@AttributeOverride(name="street", column = @Column(name="OFFICE_STREET")),
	                    @AttributeOverride(name="city", column = @Column(name="OFFICE_CITY")),
	                    @AttributeOverride(name="state", column = @Column(name="OFFICE_STATE")),
	                    @AttributeOverride(name="pincode", column = @Column(name="OFFICE_PINCODE"))
	})
	private Address officeAddress;
	
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
	
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	public static void main(String[] args) {
		EmployeeDetails emp=new EmployeeDetails();
		emp.setName("SIREESH");
		emp.setPhone("9XXXXXXXX9");
		emp.setDob(new Date());
		
		Address homeAddress=new Address();
		homeAddress.setCity("Hyd");
		homeAddress.setPincode(500081);
		homeAddress.setState("TS");
		homeAddress.setStreet("Galli");
		
		Address officeAddress=new Address();
		officeAddress.setCity("Bangalore");
		officeAddress.setPincode(500081);
		officeAddress.setState("KA");
		officeAddress.setStreet("Infantry");
		
		//set address reference
		emp.setHomeAddress(homeAddress);
		emp.setOfficeAddress(officeAddress);
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		session.close();
	}

}
