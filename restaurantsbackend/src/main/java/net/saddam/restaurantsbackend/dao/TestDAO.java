package net.saddam.restaurantsbackend.dao;

import net.saddam.restaurantsbackend.dto.Test;
import net.saddam.restaurantsbackend.dto.User;

/**
 * 
 * @author saddam
 *
 */
public interface TestDAO {
	
	
	//save otp as a password
	
	User saveOTPasPaswd(User user);
	
	
	//Fetching data from multiple table
	
	
	// save image to database usign blob file
	Test saveImage(Test test);

}
