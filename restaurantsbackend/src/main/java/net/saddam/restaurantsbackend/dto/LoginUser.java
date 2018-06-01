package net.saddam.restaurantsbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usershopkeeper")
public class LoginUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;

	
	@Column(name = "username")
	
	private String username;

    
	@Column(name = "password")
    
	private String User_password;

	@Column(name = "shop_id")
	private String Shop_ID;

	@Column(name = "user_id")
	private String User_Id;

	 @Column(name="name")
	   private String Name;
	 @Column(name="contact")
	   private String Contact;


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}


	public String getShop_ID() {
		return Shop_ID;
	}

	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}

	public String getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(String user_Id) {
		User_Id = user_Id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}
     
	
	@JsonIgnore
	public String getUser_password() {
		return User_password;
	}

	public void setUser_password(String user_password) {
		User_password = user_password;
	}

	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}

}
