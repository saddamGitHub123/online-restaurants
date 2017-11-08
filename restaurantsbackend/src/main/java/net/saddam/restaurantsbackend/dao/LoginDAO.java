package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.LoginUser;

/**
 * 
 * @author saddam
 *
 */

public interface LoginDAO {
	
	//public boolean checkLogin(String userName, int userPassword);
    List<LoginUser> checkLogin(String userName, int userPassword);


}
