package org.sireesh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name="USER_DETAILS")
public class UserDetails {
	
	private int userId;
	private String userName;
	@Id
	@Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	//We can use annotation on either on top of property definition or on getter
	//We can't configure one property on getter and one property on property defination
	//We need to maintain the uniformness to see the changes.
	//Annotation can't be possible to configure on top of setter
	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
