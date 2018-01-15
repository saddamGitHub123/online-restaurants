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
import net.saddam.restaurantsbackend.dto.Address;
import net.saddam.restaurantsbackend.dto.UserDetails;
import net.saddam.restaurantsbackend.dto.User_Data;
import net.saddam.restaurantsbackend.model.Response;
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
		
		//Address address = new Address();
		
		//parent model for user
		UserDetails userDetails = userRequest.getUserDetails();
		Address userAddress = userRequest.getUserAddress();
         //child model for the add user 
		UserResponse userResponse = new UserResponse();
		//hard coded username
        String userName = "User_";
		//int count = -1;
		
		try {
			if (userRequest.getShop_ID() == null || userDetails.getName()== null || userDetails.getPassword() == null) {
				// ** no users exist, error message *//
				userResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				userResponse.setStatus_message("Shopid is empty");
				logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
				//allProduct.setProductData(null);
				return userResponse;
			}

			List<UserDetails> listOfUser = userDAO.listUserByShopId(userRequest.getShop_ID());
			
			
			//check user name already save into database or not
			for(int j = 0;j<listOfUser.size();j++) {
			if(listOfUser.get(j).getUsername() == userDetails.getUsername()) {
				userResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				userResponse.setStatus_message("User Name already Exisit");
				logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
				//allProduct.setProductData(null);
				return userResponse;
				
			  }
			}
			
			
			// If list will be null then it will be execute 
			if (listOfUser == null || listOfUser.size() == 0) {
				
				userResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				userResponse.setStatus_message("CODE__ERROR");
				//userResponse.setRequest_Type("Product Is Not Exist ");
				//allProduct.setProductData(productData);
				//productDAO.addProduct(listOfProduct);
				return userResponse;
			}
			
			int size = listOfUser.size();
			
			//UserDetails userDetails1 =new UserDetails();
			/*for (UserDetails entity : listOfUser) {
				System.out.println("The value of database: "+ entity);
				count++;
				
				//If user name is exist or user name is empty
				if(  (listOfUser.get(count).getUsername()).equals(userDetails.getUsername()) || (listOfUser.get(count).getUsername()).isEmpty()) {
					userResponse.setStatus_code("400");
					userResponse.setStatus_message("User Name Already Exist or Empty!!!");				
					return userResponse;	
				  }				   
					
			  }*/
			
			
			if (listOfUser.get(size-1).getUser_Id() == null || listOfUser.size() == 0) {
				/* When user id is null and automatic increase the userid */
				int i = 0;
				userDetails.setShop_ID(userRequest.getShop_ID());

				userDetails.setUser_Id(userName + i);

				//userResponse.setRequest_Type("Add-New_Product");
				userResponse.setUserDetails(userDetails);				
				userAddress.setShop_ID(userRequest.getShop_ID());
				userAddress.setUser_ID(userName + i);
				userResponse.setUserAddress(userAddress);
				
				//Save all detail to user data base and userID save into address database
				userDAO.addUser(userDetails,userAddress);
				userResponse.setStatus_code(JsonResponse.CODE__OK);
				userResponse.setStatus_message("Successfully Authenticated");
				return userResponse;
			}
			else {
			// Add new user id of existing user

			userDetails.setShop_ID(userRequest.getShop_ID());
			
			//added user and id
			userDetails.setUser_Id(userName + (size-1));

			
			
			userResponse.setUserDetails(userDetails);
			
			userAddress.setShop_ID(userRequest.getShop_ID());
			userAddress.setUser_ID(userName + (size-1));
			userResponse.setUserAddress(userAddress);
			//Save all detail to user data base and userID save into address database
			userDAO.addUser(userDetails,userAddress);
			
			userResponse.setStatus_code(JsonResponse.CODE__OK);
			userResponse.setStatus_message("Successfully Authenticated");
			return userResponse;
			}
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			userResponse.setStatus_code(JsonResponse.CODE__EMPTY);
			userResponse.setStatus_message("userDAOImpl throwing exception");
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

		if (updateRequest.getUser_ID()== null || updateRequest.getShop_ID() == null || updateRequest.getUser_ID().isEmpty()) {

			updateResponse.setStatus_code(JsonResponse.CODE__EMPTY);
			updateResponse.setStatus_message("UserID and ShopId is empty");
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
			updateResponse.setStatus_message("shopid and userid is empty");
			// updateResponse.setRequest_Type("Product Is Not Exist ");
			// allProduct.setProductData(updateData);
			return updateResponse;
		}
		else {
		updateResponse.setStatus_code(JsonResponse.CODE__OK);
		updateResponse.setStatus_message("Successfully Authenticated");
		updateResponse.setUserData(user);
		logger.info("Returning updateUserList and Address");
		return updateResponse;
		}
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			updateResponse.setStatus_code(JsonResponse.CODE__EMPTY);
			updateResponse.setStatus_message("Something wrong!! userDetailByShopIdAndUserId() in userDAOImpl");
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
		
		//String shopid = updateRequest.getShop_ID();
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
	
	/**
	 * user delete using shopID and userID
	 * **/
	
	@RequestMapping(value = "/deleteUser/details", method = RequestMethod.POST)
	public @ResponseBody Response userDelete(@RequestBody UpdateRequest updateRequest,
			HttpServletRequest request) {
		
		logger.info("Entered getUserDetails()  - one user details ");
		
		
		Response response = new Response();
		try {
		
			if(updateRequest.getShop_ID() == null || updateRequest.getUser_ID() == null) {
				response.setStatus_code(JsonResponse.CODE__EMPTY);
				response.setStatus_message("ShopID and UserID Empty");
				return response;
			}

			
			if(userDAO.deleteUser(updateRequest)) {
				
				
				
				response.setStatus_code(JsonResponse.CODE__OK);
				response.setStatus_message("User Successfully deleted");
				return response;
				}
				else {
					response.setStatus_code(JsonResponse.CODE__EMPTY);
					response.setStatus_message("Something wrong!! user is not delted");
					return response;
				}

		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			response.setStatus_code(JsonResponse.CODE__EMPTY);
			response.setStatus_message("Something wrong!! userDAOImpl  ");
			return response;
		}
	}
	
	
	
	

}
