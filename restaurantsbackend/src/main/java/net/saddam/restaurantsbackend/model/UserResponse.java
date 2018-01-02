package net.saddam.restaurantsbackend.model;

import net.saddam.restaurantsbackend.dto.Address;
import net.saddam.restaurantsbackend.dto.UserDetails;

public class UserResponse extends Response{
	
	UserDetails userDetails;
	
	Address userAddress;
	

	public Address getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
	
	

}
