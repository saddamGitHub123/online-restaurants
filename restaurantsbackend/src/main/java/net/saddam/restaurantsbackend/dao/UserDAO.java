package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.User;

public interface UserDAO {

	boolean register(User user);
	
	     //List of product
			List<User> list();

}
