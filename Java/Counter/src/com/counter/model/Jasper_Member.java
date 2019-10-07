/*
 * Project Name: Rabbit Counter
 * FileName: Jasper_Member.java
 * Created Date: 2019-09-07
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 
 * 
*/
package com.counter.model;

import java.sql.Timestamp;

public class Jasper_Member {

	private int id;
	private String email;
	private String passwd;
	private String phone;
	private int groupId;
	private int locationId;
	private Timestamp regidate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public int getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public Timestamp getRegidate() {
		return regidate;
	}
	
	public void setRegidate(Timestamp regidate) {
		this.regidate = regidate;
	}
	
}
