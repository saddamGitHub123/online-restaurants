package net.saddam.onlinerestaurants.controller;

import java.util.List;

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
import net.saddam.restaurantsbackend.dao.OrderDAO;
import net.saddam.restaurantsbackend.model.OrderRequest;
import net.saddam.restaurantsbackend.model.Ordered_List;
import net.saddam.restaurantsbackend.model.Response;
import net.saddam.restaurantsbackend.model.ShopkeeperOrderResponse;

/**
 * 
 * @author sk saddam hosan
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@EnableWebMvc
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	   public OrderDAO orderDAO;
	

	/**
	 * Add orderList using shopID and userID used by User
	 * **/
	
	@RequestMapping(value = "/addOrderList", method = RequestMethod.POST)
	public @ResponseBody Response addOrderListByShopID(@RequestBody OrderRequest orderRequest) {

		logger.info("User & Shopkeeper Entered addOrderListByShopID() in OrderController  - Post all order list");
		Response response = new Response();
		
		try {
			
			
			/*String shopid = orderRequest.getShop_ID();
			String userid = orderRequest.getUser_ID();
			List<Order> order = orderRequest.getOrderList();*/
			
			//call the add order method
			if(orderDAO.addOrder(orderRequest)) {
			
			response.setStatus_code(JsonResponse.CODE__OK);
			response.setStatus_message(JsonResponse.CODE__SUCCESS);
			return response;
			
			//System.out.println(shopid+" "+userid+" "+order);
			}
			else {
				response.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
				response.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
				return response;
			}

		} catch (Exception e) {
			
			logger.error("addOrderListByShopID(): Error - " + e);
			response.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			response.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return response;
		}
	}


	/**
	 * returning all the details using shopID list of user and details 
	 * **/
	
	@RequestMapping(value = "/shopkeeper/user/details", method = RequestMethod.POST)
	public @ResponseBody ShopkeeperOrderResponse userOrderListByShopID(@RequestBody OrderRequest orderRequest) {

		logger.info("User & Shopkeeper Entered userOrderListByShopID() in OrderController  - Post only shopID");
		ShopkeeperOrderResponse response = null ;
		
		try {
		     
		     List<Ordered_List>  orderList =  orderDAO.userOrderListByShopId(orderRequest.getShop_ID());		
		     response = new ShopkeeperOrderResponse(orderList);
		     
		     if(orderList == null ) {
		    	
		    	 response.setStatus_code(JsonResponse.CODE__EMPTY);
					response.setStatus_message(ApiErrors.ERROR__ORDER_LIST_EMPTY);
		     }
		     return response;

		} catch (Exception e) {
			
			logger.error("userOrderListByShopID(): Error - " + e);
			response.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			response.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return response;
		}
		
	}


	
	
}
