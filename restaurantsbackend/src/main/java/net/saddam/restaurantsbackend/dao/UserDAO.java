package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.Product;
import net.saddam.restaurantsbackend.dto.User;

/**
 * 
 * @author saddam
 *
 */

public interface UserDAO {

	boolean register(User user);
	
	     //List of product
			List<User> list();
			
			
	     //Get list of product by shopid 
			
			List<Product> productsByShopId(String Shop_ID);

}
