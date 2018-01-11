package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.Address;
import net.saddam.restaurantsbackend.dto.Product;
import net.saddam.restaurantsbackend.dto.User;
import net.saddam.restaurantsbackend.dto.UserDetails;
import net.saddam.restaurantsbackend.dto.User_Data;
import net.saddam.restaurantsbackend.model.UpdateRequest;

/**
 * 
 * @author saddam
 *
 */

public interface UserDAO {

	boolean register(User user);

	// List of product
	List<User> list();

	// Get list of product by shopid

	List<Product> productsByShopId(String Shop_ID);

	/*
	 * list of user using shopid
	 **/

	List<UserDetails> listUserByShopId(String Shop_ID);

	boolean addUser(UserDetails userDetails,Address address);
	
	
	/**
	 * update the user list 
	 * ***/

	
	
	User_Data updateUser(UpdateRequest updateRequest);
	
	
	
	//User_Data updateUser1(UpdateRequest updateRequest);
	
	
	
	/**
	 * For only one user details 
	 * **/
	
	User_Data userDetailByShopIdAndUserId(UpdateRequest updateRequest);
	
	
	/**
	 * Getting all user details using shopID
	 * ***/
	
	List<User_Data> userDetailsByShopID(UpdateRequest updateRequest);
	
	
	//delete particuler user
	
	boolean deleteUser(UpdateRequest updateRequest);
	
}
