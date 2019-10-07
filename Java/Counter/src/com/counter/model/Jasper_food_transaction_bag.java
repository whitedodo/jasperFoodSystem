/*
 * Project Name: Rabbit Counter
 * FileName: jasper_food_transaction_bag.java
 * Created Date: 2019-09-04
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 
 * 
*/

package com.counter.model;

import java.sql.Timestamp;

public class Jasper_food_transaction_bag {
	
	private int id;
	private int locationId;
	private int menuId;
	private String menuName;
	private int cnt;
	private int orderID;
	private Timestamp regidate;
	private String ip;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public int getMenuId() {
		return menuId;
	}
	
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	
	public String getMenuName() {
		return menuName;
	}
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public int getCnt() {
		return cnt;
	}
	
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Timestamp getRegidate() {
		return regidate;
	}
	
	public void setRegidate(Timestamp regidate) {
		this.regidate = regidate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
