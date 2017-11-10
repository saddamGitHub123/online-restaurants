package net.saddam.onlinerestaurants.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.saddam.restaurantsbackend.common.ApiErrors;
import net.saddam.restaurantsbackend.common.JsonResponse;
import net.saddam.restaurantsbackend.dao.LoginDAO;
import net.saddam.restaurantsbackend.dto.LoginJsonResponse;
import net.saddam.restaurantsbackend.dto.LoginUser;
import net.saddam.restaurantsbackend.dto.LoginUserResponse;
import net.saddam.restaurantsbackend.dto.ShopKeeper;
import net.saddam.restaurantsbackend.dto.User;


/**
 * 
 * @author sk saddam hosan
 *
 */
@Controller
@EnableWebMvc
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	public LoginDAO loginDAO;
	
	
	
	/*@RequestMapping(method = RequestMethod.POST)
	public String processForm(@Valid LoginForm loginForm, BindingResult result,
			Map model) {*/
		
	@RequestMapping(value = "/user/{userName}/password/{password}",method = RequestMethod.POST)
		 public @ResponseBody
		    List<LoginUser> loginUser(HttpServletRequest request,@PathVariable("userName")String userName,
		    		                                                   @PathVariable("password")String password ) {

		  
		//List<LoginUser> listOfProduct=loginDAO.checkLogin(userName, password);
		  
		  
		 // return listOfProduct;
		
		return null;
	}
	
	
	/*
	 * accept JSON value
	 * **/
	
	@RequestMapping(value = "/postUser",method = RequestMethod.POST) 		                                                  
		 public  @ResponseBody List<LoginUser>  loginUserJson(@RequestBody LoginUser loginuser,HttpServletRequest request) {
		
		 try {
			    System.out.println(loginuser.getUserName()+" "+ loginuser.getUser_password());
			 
	             //String userName = loginuser.getUserName();
	             // String userPassword = loginuser.getUser_password();		
	        // List<LoginUser> listOfProduct=loginDAO.checkLogin(loginuser.getUserName(), loginuser.getUser_password());
	  
	          //return listOfProduct;
			    return null;

	
	        } catch (Exception e) {
	            
	            e.printStackTrace();
	            return null;
	        }
	
		  

}
	/**
	 * User Login API only for user 
	 * **/
	
	@RequestMapping(value = "/addUser",method = RequestMethod.POST) 		                                                  
	 public  @ResponseBody  LoginUserResponse loginUser(@RequestBody LoginJsonResponse loginuser) {
		
		logger.info("User Entered loginUser() in LoginController  - Post username and password for USER");
	
		try {
			// Create main class Object
			LoginUserResponse loginUseRes = new LoginUserResponse();

			// set data to the parent class
			//loginUseRes.setStatus_code(JsonResponse.CODE__OK);
			//loginUseRes.setStatus_message(ApiErrors.SUCCESS__LOGIN_STATUS);

			// String userName = loginuser.getUsername();
			// String userPassword = loginuser.getUser_password();
			//LoginUser user = new LoginUser();
			LoginUser listOfUser = loginDAO.checkLogin(loginuser.getUsername(), loginuser.getUser_password());	
			
			//System.out.println(listOfUser.getUser_Id());
			
			//loginUseRes.setLoginUser(listOfUser);
			System.out.println(listOfUser.getUser_Id());
			
			/*if (listOfUser == null  ) {
			    // no products exist, error message
				loginUseRes.setStatus_code(JsonResponse.CODE__EMPTY);
				loginUseRes.setStatus_message(ApiErrors.ERRORE_STATUS_MESSAGE);
			    logger.error(ApiErrors.ERROR__NO_USERS_EXIST);
			    loginUseRes.setLoginUser(listOfUser);
			    return loginUseRes;
			}*/
			
			if(listOfUser.getUser_Id()== null) {
				// set data to the parent class
				System.out.println("shopkeeper");
				loginUseRes.setStatus_code(JsonResponse.CODE__OK);
				loginUseRes.setStatus_message(ApiErrors.SUCCESS__LOGIN_STATUS);
				
				ShopKeeper shopKeeper = loginDAO.shopKeeperModel(listOfUser.getShop_ID());
				

				loginUseRes.setShopKeeper(shopKeeper);
				return loginUseRes;
				
			}
			else {
				
				System.out.println("User");
				loginUseRes.setStatus_code(JsonResponse.CODE__OK);
				loginUseRes.setStatus_message(ApiErrors.SUCCESS__LOGIN_STATUS);
				loginUseRes.setLoginUser(listOfUser);
				return loginUseRes;
				
			}
			
			

		} catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
           return null;
       }
	}
		
		
		
		/**
		 * Login API for the user and shopkeeper 
		 * **/
		
		@RequestMapping(value = "/addUserAndShopkeeper",method = RequestMethod.POST) 		                                                  
		 public  @ResponseBody  User loginUserAndShopkeeper(@RequestBody LoginJsonResponse loginuser) {
			
			logger.info("User and shopkeeper Entered loginUserAndShopkeeper() - Post username and password for Both");
		
			try {
				// Create main class Object
				//LoginUserResponse loginUseRes = new LoginUserResponse();

				// set data to the parent class
				//loginUseRes.setStatus(JsonResponse.CODE__OK);
				//loginUseRes.setStatus_message(ApiErrors.SUCCESS__LOGIN_STATUS);

				 String userName = loginuser.getUsername();
				 String userPassword = loginuser.getUser_password();
				//User user = new User();
				User loginUseRes = new User();
				List<User> listOfUser = loginDAO.checkLoginBoth(loginuser.getUsername(),loginuser.getUser_password());
                 
                 
                 System.out.println(loginUseRes.getUser_Id());
				
				//loginUseRes.setLoginUser(listOfUser);
				
				
				if (listOfUser == null || listOfUser.size()== 0 ) {
				    //** no products exist, error message /
					//loginUseRes.setStatus(JsonResponse.CODE__EMPTY);
					//loginUseRes.setStatus_message(ApiErrors.ERRORE_STATUS_MESSAGE);
				   // logger.error(ApiErrors.ERROR__NO_USERS_EXIST);
				   // return loginUseRes;
				}
				return loginUseRes;

			} catch (Exception e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	           return null;
	       }

	  

}

}
