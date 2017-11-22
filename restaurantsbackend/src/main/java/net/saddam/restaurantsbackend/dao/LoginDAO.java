package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.LoginUser;

import net.saddam.restaurantsbackend.dto.User;

/**
 * 
 * @author saddam
 *
 */

public interface LoginDAO {
	
	/**
	 * user name and password validation checking method
	 * **/
	LoginUser checkLogin(String userName, String userPassword);
    
    
    /**
     * user and shopkeeper both are login
     * **/
    List<User> checkLoginBoth(String userName, String userPassword);




}
