package net.saddam.restaurantsbackend.model;

import java.util.List;

import net.saddam.restaurantsbackend.dto.Order;

public class OrderRequest {
	
	
	private String Shop_ID;
    private String User_ID;
    
    java.sql.Timestamp TimeStamp;
    
    List<Order> orderList;

	public String getShop_ID() {
		return Shop_ID;
	}

	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public java.sql.Timestamp getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(java.sql.Timestamp timeStamp) {
		TimeStamp = timeStamp;
	}
    
    
    

}
