package org.sireesh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
public class ColumnAnnotation {
@Id
@Column(name="user_id")
private int id;
@Column(insertable=false) //by default true (optional), If we don't want the data to be inserted then we will use
private String userName;
@Column(length=8,nullable=false) //default value nullable is true
private String password;
@Column(precision=6,scale=2)  //9999.99
private double salary;
@Column(unique=true,updatable=false) //default value is false, If we don't want the field to be updated then we will
//go for updatable=false, default is true
private String emailId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public static void main(String[] args) {
	//Successful data
	ColumnAnnotation c=new ColumnAnnotation();
	c.setId(1);
	c.setUserName("Sireesh");
	c.setPassword("12345678");
	c.setSalary(9999.99);
	c.setEmailId("si@gmail.com");
	
	//Failure data
	/*ColumnAnnotation c=new ColumnAnnotation();
	c.setId(2);
	c.setUserName("Sireesh");
	//length check
	c.setPassword("12345678");
	//precision and scale check
	c.setSalary(889999.997777);
	//uniqueness check
	c.setEmailId("sii@gmail.com");*/
	
	//This line of code explanation
	//1.configre method will read hibernate.cfg.xml file and creates an object with the information
	//provided in xml file
	//2. It will create factory object with the configuration details.
	//Only one factory object is enough for application/xml file
	SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
	//3. Using this factory object we can open any number of sessions
	Session session=sessionFactory.openSession();
	//4. Begin the trasaction to perform the unit of operations
	session.beginTransaction();
	//5. save or persist the user object
	session.save(c);
	//6. commit the transaction 
	session.getTransaction().commit();
	//7. close the session object
	session.close();
	
}
}
