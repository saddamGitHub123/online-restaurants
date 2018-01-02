package net.saddam.restaurantsbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usershopkeeper")
public class UserDetails {
	
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @JsonIgnore
	   private int id;
	   @JsonIgnore
	   @Column(name="shop_id")
	   private String Shop_ID;
	   
	   @Column(name="user_id")
	   private String User_Id;
	   
	   @Column(name="name")
	   private String Name;
	   
	   @Column(name="username")
	   private String Username;
	   
	   @Column(name="password")
	   private String Password;
	   @JsonIgnore
	   @Column(name="contact")
	   private String Contact;
	   @JsonIgnore
	   @Column(name="email")
	   private String Email;
	   
	   @JsonIgnore
	   @Column(name="is_active")
	   private boolean active=true;

	     
	   
	   


	@Override
	public String toString() {
		return "User [id=" + id + ", Shop_ID=" + Shop_ID + ", User_Id=" + User_Id + ", Name=" + Name + ", Username="
				+ Username + ", Password=" + Password + ", Contact=" + Contact + ", Email=" + Email + ", active=" + active
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	@JsonIgnore
	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}
	@JsonIgnore
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	@JsonIgnore
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	 
		
	   
	   

}
