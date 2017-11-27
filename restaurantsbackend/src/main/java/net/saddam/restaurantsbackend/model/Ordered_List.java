package net.saddam.restaurantsbackend.model;

import java.util.List;

import net.saddam.restaurantsbackend.dto.Address;
import net.saddam.restaurantsbackend.dto.Order;

public class Ordered_List {
	
	private String User_ID;
	Address address;
	List<Order> order ;
	
	
	
	
	
	public Ordered_List(String user_ID, Address address, List<Order> order) {
		super();
		User_ID = user_ID;
		this.address = address;
		this.order = order;
	}
	@Override
	public String toString() {
		return "Ordered_List [User_ID=" + User_ID + ", address=" + address + ", order=" + order + "]";
	}
	public String getUser_ID() {
		return User_ID;
	}
	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	
	

}
