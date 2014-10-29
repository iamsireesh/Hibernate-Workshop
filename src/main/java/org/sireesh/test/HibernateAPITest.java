package org.sireesh.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sireesh.model.UserDetails;

public class HibernateAPITest {

	public static void main(String[] args) {
		UserDetails user=new UserDetails();
		user.setUserId(1);
		user.setUserName("Sireesh");
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
		session.save(user);
		//6. commit the transaction 
		session.getTransaction().commit();
		//7. close the session object
		session.close();
		
	}
}
