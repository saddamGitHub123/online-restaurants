package net.saddam.onlinerestaurants.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

import net.saddam.restaurantsbackend.common.ApiErrors;
import net.saddam.restaurantsbackend.common.JsonResponse;
import net.saddam.restaurantsbackend.dao.LoginDAO;
import net.saddam.restaurantsbackend.dto.LoginJsonResponse;
import net.saddam.restaurantsbackend.dto.LoginUser;
import net.saddam.restaurantsbackend.dto.LoginUserResponse;

/**
 * 
 * @author sk saddam hosan
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@EnableWebMvc
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	public LoginDAO loginDAO;

	/*
	 * @RequestMapping(method = RequestMethod.POST) public String processForm(@Valid
	 * LoginForm loginForm, BindingResult result, Map model) {
	 */

	@RequestMapping(value = "/user/{userName}/password/{password}", method = RequestMethod.POST)
	public @ResponseBody List<LoginUser> loginUser(HttpServletRequest request,
			@PathVariable("userName") String userName, @PathVariable("password") String password) {

		// List<LoginUser> listOfProduct=loginDAO.checkLogin(userName, password);

		// return listOfProduct;

		return null;
	}

	/*
	 * accept JSON value
	 **/

	@RequestMapping(value = "/postUser", method = RequestMethod.POST)
	public @ResponseBody List<LoginUser> loginUserJson(@RequestBody LoginUser loginuser, HttpServletRequest request) {

		try {
			System.out.println(loginuser.getUserName() + " " + loginuser.getUser_password());

			// String userName = loginuser.getUserName();
			// String userPassword = loginuser.getUser_password();
			// List<LoginUser> listOfProduct=loginDAO.checkLogin(loginuser.getUserName(),
			// loginuser.getUser_password());

			// return listOfProduct;
			return null;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	/**
	 * User and Shopkeeper Login API only for user
	 **/

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody LoginUserResponse loginUser(@RequestBody LoginJsonResponse loginuser) {

		logger.info("User & Shopkeeper Entered loginUser() in LoginController  - Post username and password for USER");

		try {
			// Create main class Object
			LoginUserResponse loginUseRes = new LoginUserResponse();
			
			System.out.println(loginuser.getUsername()+" "+loginuser.getUser_password());
			
			LoginUser listOfUser = loginDAO.checkLogin(loginuser.getUsername(), loginuser.getUser_password());
			// String userName = loginuser.getUsername();
			// String userPassword = loginuser.getUser_password();

			if (listOfUser == null) {
				// no products exist, error message
				loginUseRes.setStatus_code(JsonResponse.CODE__EMPTY);
				loginUseRes.setStatus_message(ApiErrors.ERRORE_STATUS_MESSAGE);
				logger.error(ApiErrors.ERROR__NO_USERS_EXIST);
				loginUseRes.setLoginUser(listOfUser);
				return loginUseRes;
			}

			loginUseRes.setStatus_code(JsonResponse.CODE__OK);
			loginUseRes.setStatus_message(ApiErrors.SUCCESS__LOGIN_STATUS);

			loginUseRes.setLoginUser(listOfUser);
			return loginUseRes;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	
	/**
	 * Forgotten User Name and Password
	 */

	@RequestMapping(value = "/forgotten/password", method = RequestMethod.POST)
	public @ResponseBody LoginUserResponse forgottenPassword(@RequestBody LoginUser loginuser, HttpServletRequest request) {

		try {
			logger.info("Entered forgottenPassword()  - Send User Name and password in your mobile");
			
			// Create main class Object
						LoginUserResponse loginUseRes = new LoginUserResponse();
						
			// Find your Account Sid and Token at twilio.com/user/account
		   // public static final String ACCOUNT_SID = "ACb984ebe5fa98b08b29f21139b7edd152";
		   // public static final String AUTH_TOKEN = "50420a58d72b94576f8a9d854d07ff55";
		    //public static final String TWILIO_NUMBER = "+19295002280";
			
			
			/* //this is kiora company credential 
			 String ACCOUNT_SID = "AC82908b6852b609b75dae53cfecf5d92c";
		    String AUTH_TOKEN = "9beaa9551c8669c6e977a3a4bfffd1c2";
		    String TWILIO_NUMBER = "+14844986253";*/
						
						
			 String ACCOUNT_SID = "ACb984ebe5fa98b08b29f21139b7edd152";
			  String AUTH_TOKEN = "50420a58d72b94576f8a9d854d07ff55";
			 String TWILIO_NUMBER = "+19295002280";
						
			LoginUser loginUser = loginDAO.getUserDataValue(loginuser);

			//LoginUser loginUserObject = loginUser.get(0);
			
			//checking null 
			if(loginUser == null || loginUser.getContact() == null) {
			
				
				loginUseRes.setStatus_code(JsonResponse.CODE__EMPTY);
				loginUseRes.setStatus_message("Phone number not registry ");
				logger.error(ApiErrors.ERROR__NO_USERS_EXIST);
				return loginUseRes;
			}
			else {
				
				String phonenumber = loginUser.getContact();
				String username = loginUser.getUserName();
				String password = loginUser.getUser_password();
		

			
		        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		 
		        // Build a filter for the MessageList
		        List<NameValuePair> params = new ArrayList<NameValuePair>();
		        params.add(new BasicNameValuePair("Body", "User Name: "+username+"\nPassword: "+password));
		        params.add(new BasicNameValuePair("To",phonenumber )); //Add real number here
		       // params.add(new BasicNameValuePair("To", "+919740092365"));
		        params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

		        MessageFactory messageFactory = client.getAccount().getMessageFactory();
		        Message message = messageFactory.create(params);
		        System.out.println(message.getSid());
		        
		        loginUseRes.setStatus_code(JsonResponse.CODE__EMPTY);
				loginUseRes.setStatus_message("User Name And Phone Number Successufully Send");
				//logger.error(ApiErrors.ERROR__NO_USERS_EXIST);
				loginUseRes.setLoginUser(loginUser);
		        return loginUseRes; 
			}
		    
		}   catch (TwilioRestException e) {
	        System.out.println(e.getErrorMessage());
	        return null;
	    }

	}
	
	
	
	/*
	
	*//**
	 * SMS integration with Twilio
	 *//*

	@RequestMapping(value = "/forgotten/password", method = RequestMethod.GET)
	public @ResponseBody void forgottenPassword(HttpServletRequest request) {

		try {
			logger.info("Entered forgottenPassword()  - Send password in your mobile");
			
			// Find your Account Sid and Token at twilio.com/user/account
		   // public static final String ACCOUNT_SID = "ACb984ebe5fa98b08b29f21139b7edd152";
		   // public static final String AUTH_TOKEN = "50420a58d72b94576f8a9d854d07ff55";
		    //public static final String TWILIO_NUMBER = "+19295002280";
			
			
			 //this is kiora company credential 
			 String ACCOUNT_SID = "AC82908b6852b609b75dae53cfecf5d92c";
		    String AUTH_TOKEN = "9beaa9551c8669c6e977a3a4bfffd1c2";
		    String TWILIO_NUMBER = "+14844986253";
		    
		    String ACCOUNT_SID = "ACb984ebe5fa98b08b29f21139b7edd152";
		    String AUTH_TOKEN = "50420a58d72b94576f8a9d854d07ff55";
		    String TWILIO_NUMBER = "+19295002280";
		    String username = "xyz";
		    String password = "1234";
			
		        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		 
		        // Build a filter for the MessageList
		        List<NameValuePair> params = new ArrayList<NameValuePair>();
		        params.add(new BasicNameValuePair("Body", "User Name: "+username+"\nPassword: "+password));
		        params.add(new BasicNameValuePair("To", "+919740092365")); //Add real number here
		       // params.add(new BasicNameValuePair("To", "+919740092365"));
		        params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

		        MessageFactory messageFactory = client.getAccount().getMessageFactory();
		        Message message = messageFactory.create(params);
		        System.out.println(message.getSid());
		        
		       // return 
		    
		}   catch (TwilioRestException e) {
	        System.out.println(e.getErrorMessage());
	    }

	}*/

}
