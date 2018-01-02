package net.saddam.restaurantsbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usershopkeeper")
public class User_Data {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @JsonIgnore
	   private int id;
	
	   @Column(name="name")
	   private String Name;
	   
	   @Column(name="username")
	   private String Username;
	   
	   @Column(name="shop_id")
	   private String Shop_ID;
	   
	   @Column(name="user_id")
	   private String User_ID;
	   
	   @Column(name="password")
	   private String Password;
	   @Column(name="contact")
	   private String Contact;
	   @Column(name="email")
	   private String Email;
	   
	  
	   @Transient
	   Address userAddress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	
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
	}public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public User_Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	   
	   
	   

}
