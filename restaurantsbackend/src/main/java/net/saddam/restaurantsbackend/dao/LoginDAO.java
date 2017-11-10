package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.LoginUser;
import net.saddam.restaurantsbackend.dto.ShopKeeper;
import net.saddam.restaurantsbackend.dto.User;

/**
 * 
 * @author saddam
 *
 */

public interface LoginDAO {
	
	/**
	 * Serivices login api for USER
	 * **/
	LoginUser checkLogin(String userName, String userPassword);
    
    
    /**
     * user and shopkeeper both are login
     * **/
    List<User> checkLoginBoth(String userName, String userPassword);


	ShopKeeper shopKeeperModel(String shop_ID);


}
