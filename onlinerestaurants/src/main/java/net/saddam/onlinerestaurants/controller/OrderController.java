package net.saddam.onlinerestaurants.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;
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
import net.saddam.restaurantsbackend.dto.Order;
import net.saddam.restaurantsbackend.model.DispatchRequest;
import net.saddam.restaurantsbackend.model.OrderListResponse;
import net.saddam.restaurantsbackend.model.OrderRequest;
import net.saddam.restaurantsbackend.model.OrderRequestAdd;
import net.saddam.restaurantsbackend.model.OrderSizeModel;
import net.saddam.restaurantsbackend.model.OrderSizeRequest;
import net.saddam.restaurantsbackend.model.OrderSizeResponse;
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
	public @ResponseBody Response addOrderListByShopID(@RequestBody OrderRequestAdd orderRequest) {

		logger.info("User & Shopkeeper Entered addOrderListByShopID() in OrderController  - Post all order list");
		Response response = new Response();
		
		try {

			
			//call the add order method
			//if(orderDAO.addOrder(orderRequest)) {
			if(orderDAO.addOrderAndOrderID(orderRequest)) {
			
			
			
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
			
			 String Shop_ID = orderRequest.getShop_ID();
		     String User_ID = orderRequest.getUser_ID();
		   ///  int Page_Value = orderRequest.getPage_Value();
		     boolean Dispatch = orderRequest.isDispatch();
			
			    
		     
		     List<Ordered_List>  orderList =  orderDAO.userOrderListByShopId(Shop_ID,User_ID,Dispatch);
		     
		     System.out.println("orderlist size"+orderList.size());
		     
		     if(orderList.size() == 0 ) {
		    	 response = new ShopkeeperOrderResponse(orderList);
		    	 response.setStatus_code(JsonResponse.CODE__EMPTY);
					response.setStatus_message(ApiErrors.ERROR__ORDER_LIST_EMPTY);
					return response;
		     }
		     //For paging
		     
		     int pageValue = orderRequest.getPage_Value();
		     System.out.println("page value :"+pageValue);
		     int low_index = 20*pageValue;
		     int higest_index = low_index+20;
		     
		     
		     if(higest_index < orderList.size()) {
		     List<Ordered_List>  orderListValue = orderList.subList(low_index, higest_index);
		     response = new ShopkeeperOrderResponse(orderListValue);
		     }
		     else
		     {   
		    	 
		    	 if(orderList.size() <= low_index) {
		    		 
		    		 //when list is empty 
		                   	 List<Ordered_List> myEmptyList = Collections.<Ordered_List>emptyList();
		    	             response = new ShopkeeperOrderResponse(myEmptyList);
		    	             response.setStatus_code("200");
			                 response.setStatus_message("Order List Is Empty");
			                 return response;
		                    }
		    	 //for last list of orderList from list 
		    	 List<Ordered_List>  orderListValue = orderList.subList(low_index, orderList.size());
			     response = new ShopkeeperOrderResponse(orderListValue); 
		     }
		     
		    
		     
		    // response = new ShopkeeperOrderResponse(orderList);
		     
		   
		     
		     response.setStatus_code(JsonResponse.CODE__OK);
		     response.setStatus_message("Successfully Authenticated");
		     response.setOrderSize(orderList.size());
		     return response;

		} catch (Exception e) {
			
			logger.error("userOrderListByShopID(): Error - " + e);
			response.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			response.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return response;
		}
		
	}

	
	/**
	 * 
	 * Stock value update using shopID using orderList and price table
	 * For dispatch order API
	 * */
	
	

	@RequestMapping(value = "/dispatch", method = RequestMethod.POST)
	public @ResponseBody Response orderDispatch(@RequestBody DispatchRequest dispatchRequest) {

		logger.info("Order Dispatch  Entered orderDispatch() in OrderController  - shopID,orderID");
		
		//orderDAO.
		Response response = new Response();
		
		try {
      if(orderDAO.orderDispatchOrderID(dispatchRequest)) {
			
			
			
			response.setStatus_code(JsonResponse.CODE__OK);
			response.setStatus_message(JsonResponse.DISPATCH__SUCCESS);
			return response;
			
			//System.out.println(shopid+" "+userid+" "+order);
          }
			
      } catch (Exception e) {
				
				logger.error("orderDispatch(): Error - " + e);
				response.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
				response.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
				return response;
			}
		
		
		return response;
		
		
	}
	
	
	
	
	
	/**
	 * 
	 * returing timestamp,orderid and total amount and orderlist size using
	 * shopid and userid 
	 * for mobile application
	 * */
	
	

	@RequestMapping(value = "/orderSize", method = RequestMethod.POST)
	public @ResponseBody OrderSizeResponse orderSizeStructure(@RequestBody OrderSizeRequest orderSizeRequest) {

		logger.info("Order Size orderSizeStructere() in OrderController  - shopID,userID");
		
		//orderDAO.
		OrderSizeResponse orderSizeResponse = null;
		
		try {
			
			
			List<OrderSizeModel> orderSize = orderDAO.orderSizeList(orderSizeRequest);
			System.out.println(orderSize);
			
			if(orderSize.size() == 0) {
				orderSizeResponse = new OrderSizeResponse(orderSize);
				orderSizeResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				orderSizeResponse.setStatus_message(JsonResponse.LIST__ERRORE);
				return orderSizeResponse;
				
			}
			
			orderSizeResponse = new OrderSizeResponse(orderSize);
			orderSizeResponse.setStatus_code(JsonResponse.CODE__OK);
			orderSizeResponse.setStatus_message("Successfully Authenticated");
			return orderSizeResponse;
	
		
      } catch (Exception e) {
				
				logger.error("orderDispatch(): Error - " + e);
				orderSizeResponse.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
				orderSizeResponse.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
				return orderSizeResponse;
			}
		
		
		
		
		
	}
	
	
	/**
	 * 
	 * returing orderlist using orderid and userid and shopid 
	 * for mobile app
	 * */
	
	

	@RequestMapping(value = "/orderId/orderList", method = RequestMethod.POST)
	public @ResponseBody OrderListResponse listOfOrder(@RequestBody OrderSizeRequest orderSizeRequest) {

		logger.info("Order List listOfOrder() in OrderController  - shopID,userID");
		
		//orderDAO.
		OrderListResponse orderListResponse = null;
		
		try {
			
			
			List<Order> orderSize = orderDAO. orderList(orderSizeRequest);
			System.out.println(orderSize);
			
			if(orderSize.size() == 0) {
				//orderSizeResponse = new OrderSizeResponse(orderSize);
				orderListResponse = new OrderListResponse(orderSize);
				orderListResponse.setStatus_code(JsonResponse.CODE__EMPTY);
				orderListResponse.setStatus_message(JsonResponse.LIST__ERRORE);
				return orderListResponse;
				
			}
			
			orderListResponse = new OrderListResponse(orderSize);
			orderListResponse.setStatus_code(JsonResponse.CODE__OK);
			orderListResponse.setStatus_message("Successfully Authenticated");
			return orderListResponse;
	
		
      } catch (Exception e) {
				
				logger.error("orderDispatch(): Error - " + e);
				orderListResponse.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
				orderListResponse.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
				return orderListResponse;
			}
		
		
		
		
		
	}
	
	
	

	 //push notification for the web page 
	@RequestMapping(value = "/addNotificationtype", method = RequestMethod.GET)
	public String addNotification() {

		logger.info("User & Shopkeeper Entered addOrderListByShopID() in OrderController  - Post all order list");
		//Response response = new Response();
		
		 String AUTH_KEY_FCM = "AAAAwNSbEj0:APA91bG-iq7DieKB5VF2nTk2Du6bo779LsoBIPFmIyJgxx3Ej_YD6bEiIWlGMNwTeknUv4M64RpS3FtUcfD44fF3YS_gGYe6i35LF2rzLckR2rJj8PlNL0AFsghNBUhBas2tchBQC5bt";
		 String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
		 String deviceToken = "XYWARTYGFRT4T6";
		 String result = "";
		
		try {

			 
			 
			    URL url = new URL(API_URL_FCM);
			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			    conn.setUseCaches(false);
			    conn.setDoInput(true);
			    conn.setDoOutput(true);

			    conn.setRequestMethod("POST");
			    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
			    conn.setRequestProperty("Content-Type", "application/json");

			    JSONObject json = new JSONObject();

			    /*json.put("to", deviceToken.trim());
			    JSONObject info = new JSONObject();
			    info.put("title", "notification title"); // Notification title
			    info.put("body", "message body"); // Notification
			                                                            // body
			    json.put("notification", info);*/
			    
			    
			    OutputStreamWriter wr = new OutputStreamWriter(
		                conn.getOutputStream());
		        wr.write(json.toString());
		        wr.flush();

		        BufferedReader br = new BufferedReader(new InputStreamReader(
		                (conn.getInputStream())));

		        String output;
		        System.out.println("Output from Server .... \n");
		        while ((output = br.readLine()) != null) {
		            System.out.println(output);
		        }
		        result = "Success";
			
			return result;

		} catch (Exception e) {
	        e.printStackTrace();
	        result = "Faliures";
	    }
	    System.out.println("GCM Notification is sent successfully");

	    return result;
	}

	
}
