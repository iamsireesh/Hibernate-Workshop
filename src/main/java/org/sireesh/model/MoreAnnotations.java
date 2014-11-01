package org.sireesh.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "MoreAnnotations")
public class MoreAnnotations {
	@Id
	private int Id;
	@Transient
	// Avoid to store in table
	private String userName;
	@Basic(fetch = FetchType.LAZY)
	private String password;
	@Basic(optional = false) //not clear about this
	private int number;
	private Date datetime; // by default it is equal to // @Temporal(TemporalType.TIMESTAMP)
	@Temporal(TemporalType.DATE)
	private Date date;
	@Temporal(TemporalType.TIME)
	private Date time;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetimestamp;
	@Lob
	private String address;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getDatetimestamp() {
		return datetimestamp;
	}

	public void setDatetimestamp(Date datetimestamp) {
		this.datetimestamp = datetimestamp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static void main(String[] args) {
		MoreAnnotations moreAnnotations=new MoreAnnotations();
		moreAnnotations.setId(1);
		moreAnnotations.setUserName("Sireesh");
		moreAnnotations.setPassword("*********");
		//.setNumber(number);
		Date date=new Date();
		moreAnnotations.setDate(date);
		moreAnnotations.setDatetime(date);
		moreAnnotations.setTime(date);
		moreAnnotations.setDatetimestamp(date);
		String data="Hyderabad";
		String bigdata="";
		for(int i=0;i<1000;i++){
			bigdata=bigdata+" "+data;
		}
		moreAnnotations.setAddress(bigdata);
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(moreAnnotations);
		session.getTransaction().commit();
		session.close();
	}
}
