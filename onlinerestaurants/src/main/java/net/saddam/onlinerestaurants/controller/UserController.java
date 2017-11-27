package net.saddam.onlinerestaurants.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.saddam.restaurantsbackend.common.ApiErrors;
import net.saddam.restaurantsbackend.common.JsonResponse;
import net.saddam.restaurantsbackend.dao.UserDAO;
import net.saddam.restaurantsbackend.dto.UserDetails;
import net.saddam.restaurantsbackend.dto.User_Data;
import net.saddam.restaurantsbackend.model.UpdateRequest;
import net.saddam.restaurantsbackend.model.UpdateResponse;
import net.saddam.restaurantsbackend.model.UserDetailsResponse;
import net.saddam.restaurantsbackend.model.UserRequest;
import net.saddam.restaurantsbackend.model.UserResponse;
/**
 * 
 * @author sk saddam hosan
 *
 */

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@EnableWebMvc
@RequestMapping("/user")
public class UserController {
	
private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	

    @Autowired
   public UserDAO userDAO;
	
	
	
	/**
	 * Add user using shopid and user details 
	 * **/
	
	@RequestMapping(value = "/add/userList", method = RequestMethod.POST)
	public @ResponseBody UserResponse addUserList(@RequestBody UserRequest userRequest) {
		logger.info("Entered addUserList()  - Add all user ");
		
		//parent model for user
		UserDetails userDetails = userRequest.getUserDetails();
        //child model for the add user 
		UserResponse userResponse = new UserResponse();
		//hard coded username
        String userName = "User_";
		int count = -1;
		
		try {
			if (userRequest.getShop_ID() == null || userDetails.getName()== null || userDetails.getPassword() == null) {
				// ** no users exist, error message *//
				userResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				userResponse.setStatus_message(JsonResponse.CODE__ERROR);
				logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
				//allProduct.setProductData(null);
				return userResponse;
			}

			List<UserDetails> listOfUser = userDAO.listUserByShopId(userRequest.getShop_ID());
			
			// If list will be null then it will be execute 
			if (listOfUser == null || listOfUser.size() == 0) {
				
				userResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				userResponse.setStatus_message("CODE__ERROR");
				//userResponse.setRequest_Type("Product Is Not Exist ");
				//allProduct.setProductData(productData);
				//productDAO.addProduct(listOfProduct);
				return userResponse;
			}
			

			for (UserDetails entity : listOfUser) {
				// do something useful with entity;

				System.out.println("The value of database: "+ entity);
				count++;

			}
			
			
			if (listOfUser.get(count).getUser_Id() == null || listOfUser.size() == 0) {
				/* When user id is null and automatic increase the userid */
				int i = 0;
				userDetails.setShop_ID(userRequest.getShop_ID());

				userDetails.setUser_Id(userName + i);
				userResponse.setStatus_code(JsonResponse.CODE__OK);
				userResponse.setStatus_message("Successfully Authenticated");
				//userResponse.setRequest_Type("Add-New_Product");
				userResponse.setUserDetails(userDetails);
				userDAO.addUser(userDetails);
				return userResponse;
			}

			// Add new user id of existing product

			userDetails.setShop_ID(userRequest.getShop_ID());
			
			//added user and id
			userDetails.setUser_Id(userName + count);

			userResponse.setStatus_code(JsonResponse.CODE__OK);
			userResponse.setStatus_message("Successfully Authenticated");
			//userResponse.setRequest_Type("Update_Product_Id");
			userResponse.setUserDetails(userDetails);
			
			//save user details to user table
			userDAO.addUser(userDetails);
			return userResponse;
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			userResponse.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			userResponse.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return userResponse;
		}

	}
	
	
	
	
	/**
	 * Add user using shopid and user details 
	 * **/
	
	@RequestMapping(value = "/update/userList", method = RequestMethod.POST)
	public @ResponseBody UpdateResponse updateUserList(@RequestBody UpdateRequest updateRequest,
			HttpServletRequest request) {
		logger.info("Entered updateUserList()  - update particular user ");

		UpdateResponse updateResponse = new UpdateResponse();
		// String shopid = updateRequest.getShop_ID();
		// String userId = updateRequest.getUser_ID();
		// User_Data userData = updateRequest.getUser_Data();
		// Address address = userData.getUserAddress();
		System.out.println("" + request.getSession().getCreationTime() + "" + request.getParameterMap());

        //call the update method
		User_Data user = userDAO.updateUser(updateRequest);

		if (user.getUsername() == null) {

			updateResponse.setStatus_code(JsonResponse.CODE__EMPTY);
			updateResponse.setStatus_message("CODE__ERROR");
			// updateResponse.setRequest_Type("Product Is Not Exist ");
			// allProduct.setProductData(updateData);
			return updateResponse;
		}

		updateResponse.setStatus_code(JsonResponse.CODE__OK);
		updateResponse.setStatus_message("Successfully Authenticated");
		// allProduct.setRequest_Type("Update_Product_List");
		// allProduct.setProductData(updateData);
		updateResponse.setUserData(user);
		logger.info("Returning updateUserList and Address");
		return updateResponse;
	}
	
	/**
	 * API Get a user details usig  userid and shopid 
	 * */
	
	@RequestMapping(value = "/details/shopid/userid", method = RequestMethod.POST)
	public @ResponseBody UpdateResponse getUserDetails(@RequestBody UpdateRequest updateRequest,
			HttpServletRequest request) {
	   
		logger.info("Entered getUserDetails()  - one user details ");
		UpdateResponse updateResponse = new UpdateResponse();
		try {
		

		User_Data user = userDAO.userDetailByShopIdAndUserId(updateRequest); 

		
		if (user.getShop_ID() == null || user.getUser_ID() == null) {

			updateResponse.setStatus_code(JsonResponse.CODE__EMPTY);
			updateResponse.setStatus_message("CODE__ERROR");
			// updateResponse.setRequest_Type("Product Is Not Exist ");
			// allProduct.setProductData(updateData);
			return updateResponse;
		}

		updateResponse.setStatus_code(JsonResponse.CODE__OK);
		updateResponse.setStatus_message("Successfully Authenticated");
		updateResponse.setUserData(user);
		logger.info("Returning updateUserList and Address");
		return updateResponse;
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			updateResponse.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			updateResponse.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return updateResponse;
		}

	}
	
	
	/**
	 * Get All user list using shopID
	 * */
	
	@RequestMapping(value = "/details/shopid", method = RequestMethod.POST)
	public @ResponseBody UserDetailsResponse getAllUserDetailsByShopId(@RequestBody UpdateRequest updateRequest,
			HttpServletRequest request) {
		
		logger.info("Entered getUserDetails()  - one user details ");
		
		String shopid = updateRequest.getShop_ID();
		UserDetailsResponse updateResponse = new UserDetailsResponse();
		
		try {
		

		List<User_Data> user = userDAO.userDetailsByShopID(updateRequest); 

		
		if (user.isEmpty()) {

			updateResponse.setStatus_code(JsonResponse.CODE__EMPTY);
			updateResponse.setStatus_message(ApiErrors.ERROR__NO_USER_EXIST);
			// updateResponse.setRequest_Type("Product Is Not Exist ");
			// allProduct.setProductData(updateData);
			return updateResponse;
		}

		updateResponse.setStatus_code(JsonResponse.CODE__OK);
		updateResponse.setStatus_message("Successfully Authenticated");
		updateResponse.setUserData(user);
		logger.info("Returning updateUserList and Address");
		return updateResponse;
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			updateResponse.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			updateResponse.setStatus_message(JsonResponse.CODE__ERROR);
			return updateResponse;
		}
	}
	
	

}
