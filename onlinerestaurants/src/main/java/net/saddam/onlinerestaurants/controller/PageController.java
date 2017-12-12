package net.saddam.onlinerestaurants.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.saddam.restaurantsbackend.common.ApiErrors;
import net.saddam.restaurantsbackend.common.JsonResponse;
import net.saddam.restaurantsbackend.dao.UserDAO;

import net.saddam.restaurantsbackend.dto.Product;
import net.saddam.restaurantsbackend.dto.User;
import net.saddam.restaurantsbackend.dto.UserALL;
import net.saddam.restaurantsbackend.model.AllProduct;

/**
 * 
 * @author saddam
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@EnableWebMvc

public class PageController {
	
	 private static final Logger logger = LoggerFactory.getLogger(PageController.class);
		
	  
	
	@Autowired
	public UserDAO userDAO;

	//This is for home page 
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", "welcome to spring web mvc ");
		return mv;
	}
	/*@RequestMapping(value = "/register", method = RequestMethod.GET)
	  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("register");
	   mav.addObject("user", new User());
	    return mav;
	  }*/
	/*
	 @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("user") User user) {
	  userDao.register(user);
	  return new ModelAndView("welcome", "firstname", user.getFirstname());
	  }*/
	
	/**
	 * This API showing list of data from database
	 * **/

	 @ResponseStatus(value=HttpStatus.OK)
	@RequestMapping("/all/user")
	@ResponseBody
	public List<User> gerAllProducts(){
		 
		 logger.info("Inside PageController list of user method - INFO");
		 logger.debug("Inside PageController list of user method - DEBUG");
		 
		
		return userDAO.list();
		
	}
	 
	 /**
	  * 
	  * Api for the hard coded value
	  * **/
	 
	 @RequestMapping("/all/person")
	  public @ResponseBody UserALL getAllUser(HttpServletRequest request) {
		 
		 logger.info("Inside PageController Nested JSON show  - INFO");
		 logger.debug("Inside PageController All User JSON - DEBUG");
		 
		 //Create main class Object
		 UserALL userAll = new UserALL();		 
		 
		 List<User> userList = new ArrayList();
		 
		 //Create child class Object
		 User users = new User();
		 
		 //Add element to list 
		 users.setUser_Id("user1");
		 users.setShop_ID("shop1");
		 users.setUsername("TouchCor");
		 users.setName("Mr. TouchCor");
		 users.setPassword("rr");
		 users.setContact(2);
		 users.setEmail("tochCore@gmail.com");
		 
		 //Add users element to userList
		 userList.add(users);
		 
		 //set data to the parent class
		 userAll.setStatus_code("OK OK");
		 userAll.setStatus_message("Now hard coded json success");
		 userAll.setUser(userList);
		 
		 return userAll;
		 
	  }
	 
	 /**
	  * 
	  * For using the back end data 
	  * **
	  */
	 
	 @RequestMapping("/all/backuser")
	  public @ResponseBody UserALL getAllUserBack(HttpServletRequest request) {
		 
		 logger.info("Inside PageController Nested JSON show  - INFO");
		 logger.debug("Inside PageController All User JSON - DEBUG");
		 
		 //Create main class Object
		 UserALL userAll = new UserALL();	
		 
		 //set data to the parent class
		 userAll.setStatus_code("200");
		 userAll.setStatus_message("Successfully Authenticated");
		 
		 //Store the database list of data into list
		 List<User> listOfSimpleEntities =userDAO.list();
		 userAll.setUser(listOfSimpleEntities);
		 
		 return userAll;
		 
	  }
	 
	 /***
	  * 
	  * Tested url for testing 
	  * *****/
/*	 
	 @RequestMapping("/all/test")
	  public @ResponseBody Child getTest(HttpServletRequest request) {
		 
		 logger.info("Inside PageController Single JSON   - INFO");
		 logger.debug("Inside PageController For testing JSON - DEBUG");
		 
		 Child ch = new Child();
		 ch.setNumber(2);
		 
		 return ch;
		 
	  }
	 */
	 
	 
	 
	 /**
	  * Using shopID to get all product list
	  * 
	  * **
	  */

	    @RequestMapping(value = "/list/product/shop/{shopId}/{userId}", method = RequestMethod.GET)
	    public @ResponseBody
	    AllProduct listOfProductByShopId(HttpServletRequest request,@PathVariable("shopId")String shopId,
	    		                                                   @PathVariable("userId")String userId )
	            								 
	    {
	        logger.info("Entered listOfProductByShopId()  - Get a Product list from shopId");	        
	        AllProduct allProduct = new AllProduct();
	        

	        try
			{
				
				if (userId == null) {
				    //** no products exist, error message *//
				    allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
				    allProduct.setStatus_message(JsonResponse.CODE__ERROR);
				    logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
				    return allProduct;
				}
				
				List<Product> listOfSimpleEntities = userDAO.productsByShopId(shopId);
				//allProduct.setProduct(listOfSimpleEntities);
				//allProduct.setUniqueProduct(listOfSimpleEntities);
				 
				if (listOfSimpleEntities == null || listOfSimpleEntities.size()== 0 ) {
				    //** no products exist, error message *//
				    allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
				    allProduct.setStatus_message(ApiErrors.ERROR__NO_PRODUCTS_EXIST);
				    logger.error(ApiErrors.ERROR__NO_PRODUCTS_EXIST);
				    return allProduct;
				}
				
				
				
				//** set status OK *//*
				allProduct.setStatus_code(JsonResponse.CODE__OK);
				allProduct.setStatus_message("Successfully Authenticated");
				
			}
			catch (Exception e)
			{
				logger.error("listOfProductByShopId(): Error - " + e);
				allProduct.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
				allProduct.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
	            return allProduct;
			}
	        logger.info("Returning listOfProductByShopId()");

	        return allProduct;
	    }
 


}
