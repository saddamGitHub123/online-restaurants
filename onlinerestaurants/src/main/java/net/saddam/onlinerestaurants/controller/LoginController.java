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

import net.saddam.restaurantsbackend.dao.LoginDAO;
import net.saddam.restaurantsbackend.dto.LoginUser;


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
		
	@RequestMapping(value = "/user/{userName}/password/{password}",method = RequestMethod.POST,consumes = "application/json")
		 public @ResponseBody
		    List<LoginUser> loginUser(HttpServletRequest request,@PathVariable("userName")String userName,
		    		                                                   @PathVariable("password")int password ) {

		
		/*if (result.hasErrors()) {
			return "loginform";
		}*/
		
		
	//String Username = userName;
	//	int Password = password;
		
	//	User user = new User();
		  /*user.setUsername(Username);
		  user.setPassword(Password);*/
		  
		List<LoginUser> listOfProduct=loginDAO.checkLogin(userName, password);
		  
		  
		  return listOfProduct;
		  
		
		
		
		
		
		
		/*loginForm = (LoginForm) model.get("loginForm");
		if (!loginForm.getUserName().equals(userName)
				|| !loginForm.getPassword().equals(password)) {
			return "loginform";
		}
		*/
		/*boolean userExists = loginService.checkLogin(loginForm.getUserName(),
                loginForm.getPassword());
		if(userExists){
			model.put("loginForm", loginForm);
			return "loginsuccess";
		}else{
			result.rejectValue("userName","invaliduser");
			return "loginform";
		}*/

	}
	
	
	/*
	 * accept JSON value
	 * **/
	
	@RequestMapping(value = "/user/password/loginuser",method = RequestMethod.POST)
	 //public @ResponseBody
	 //   List<LoginUser> loginUserJson(HttpServletRequest request,@PathVariable("userName")String userName,
	  //  		                                                   @PathVariable("password")int password ) {
		 public  @ResponseBody List<LoginUser>  loginUserJson(@RequestBody LoginUser loginuser, HttpServletRequest request) {
		
/*	public @ResponseBody List<LoginUser> addPost(@RequestBody @Valid LoginUser loginuser, BindingResult bindingResult,
		        UriComponentsBuilder ucBuilder){*/
	
	/*public @ResponseBody List<LoginUser> processForm(@RequestBody @Valid LoginUser loginuser, BindingResult result,
			Map model){*/
			   System.out.println("Inside json user");
	  String userName = loginuser.getUserName() ;
	  int password = loginuser.getPassword() ;
		
	List<LoginUser> listOfProduct=loginDAO.checkLogin(userName, password);
	  
	  
	  return listOfProduct;
	  


}

}
