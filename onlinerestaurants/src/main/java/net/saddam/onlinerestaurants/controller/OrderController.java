package net.saddam.onlinerestaurants.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
					return response;
		     }
		     
		     response.setStatus_code(JsonResponse.CODE__OK);
		     response.setStatus_message("Successfully Authenticated");
		     return response;

		} catch (Exception e) {
			
			logger.error("userOrderListByShopID(): Error - " + e);
			response.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			response.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return response;
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
