package net.saddam.restaurantsbackend.model;

import net.saddam.restaurantsbackend.dto.Address;
import net.saddam.restaurantsbackend.dto.UserDetails;

public class UserRequest {

	private String Shop_ID;
	
	UserDetails userDetails;
	
	Address userAddress;
	
	

	public Address getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}

	public String getShop_ID() {
		return Shop_ID;
	}

	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}



	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	
	
	
}
