package org.sireesh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "TableAnnotations",
uniqueConstraints = { @UniqueConstraint(name = "user_unique", columnNames = { "username" }) }, 
indexes = { @Index(name = "user_idx", columnList = "username") })
public class TableAnnotations {
	@Id
	private int id;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public static void main(String[] args) {
		TableAnnotations t=new TableAnnotations();
		t.setId(1);
		t.setPassword("hello");
		t.setUsername("Sireesh");
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
		session.save(t);
		//6. commit the transaction 
		session.getTransaction().commit();
		//7. close the session object
		session.close();
		
	}
}
