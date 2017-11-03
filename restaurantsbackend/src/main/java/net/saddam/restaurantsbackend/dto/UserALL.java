package net.saddam.restaurantsbackend.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
//@Table(name="USERJSON ")
public class UserALL {

	  // @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @JsonIgnore
	   private int id;
      
	   
	   private String status_code;
	   
	   
	   private String status_message;
	   
	 //  @Column(name="is_active")
	   @JsonIgnore
	   private boolean active=true;
	   
	  // this is child class as a list
	  private  List<User> user;
	


	@Override
	public String toString() {
		return "UserALL [id=" + id + ", status_code=" + status_code + ", status_message=" + status_message + ", active="
				+ active + ", user=" + user + "]";
	}


	public List<User> getUser() {
		return user;
	}


	public void setUser(List<User> user) {
		this.user = user;
	}


	public UserALL() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getStatus_message() {
		return status_message;
	}

	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	   
	


		
	   
	   
	
	
	
}
