package net.saddam.restaurantsbackend.model;

import java.util.List;

import net.saddam.restaurantsbackend.dto.User_Data;

public class UserDetailsResponse extends Response {

	
	List <User_Data> userData;

	public List<User_Data> getUserData() {
		return userData;
	}

	public void setUserData(List<User_Data> userData) {
		this.userData = userData;
	}
	
	
}
