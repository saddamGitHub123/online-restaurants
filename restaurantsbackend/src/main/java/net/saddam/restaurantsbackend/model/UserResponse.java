package net.saddam.restaurantsbackend.model;

import net.saddam.restaurantsbackend.dto.UserDetails;

public class UserResponse extends Response{
	
	UserDetails userDetails;

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
	
	

}
