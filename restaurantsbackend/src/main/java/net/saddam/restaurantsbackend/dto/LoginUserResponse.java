package net.saddam.restaurantsbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.saddam.restaurantsbackend.model.Response;

public class LoginUserResponse extends Response{

	   
	  // this is child class as a list
	
	
	  @JsonProperty("Data")
	  private  LoginUser loginUser;
	  


	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	public LoginUserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	  
	  
}
