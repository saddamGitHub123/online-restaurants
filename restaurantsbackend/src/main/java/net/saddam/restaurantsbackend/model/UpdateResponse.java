package net.saddam.restaurantsbackend.model;

import net.saddam.restaurantsbackend.dto.User_Data;

public class UpdateResponse extends Response{
	
	User_Data userData;

	public User_Data getUserData() {
		return userData;
	}

	public void setUserData(User_Data userData) {
		this.userData = userData;
	}

	public UpdateResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
