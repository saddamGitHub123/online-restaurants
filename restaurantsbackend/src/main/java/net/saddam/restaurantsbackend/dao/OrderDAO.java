package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.Order;
import net.saddam.restaurantsbackend.dto.User;
import net.saddam.restaurantsbackend.model.DispatchRequest;
import net.saddam.restaurantsbackend.model.OrderRequest;
import net.saddam.restaurantsbackend.model.OrderRequestAdd;
import net.saddam.restaurantsbackend.model.OrderSizeModel;
import net.saddam.restaurantsbackend.model.OrderSizeRequest;
import net.saddam.restaurantsbackend.model.Ordered_List;

/**
 * 
 * @author saddam
 *
 */
public interface OrderDAO {

	// Add the order list using shopID and userID

	boolean addOrder(OrderRequest orderRequest);
	
	
	// Add the order list using shopID and userID

		boolean addOrderAndOrderID(OrderRequestAdd orderRequest);

	// order list and user details by shopID
	List<Ordered_List> userOrderListByShopId(String Shop_ID,String User_ID,boolean Dispatch);
	
	
  // Order Dispatch method
	
	boolean orderDispatchOrderID(DispatchRequest dispatchRequest);
	
	
	
	//getting order size and orderid list 
	
	List<OrderSizeModel> orderSizeList(OrderSizeRequest orderSizeRequest);
	
	
	//orderList for mobile app using orderid
	
	List<Order> orderList(OrderSizeRequest orderSizeReques);
	
	
	//getting phone number for dispatch controller sms intrgation 
	
	List<User> userPhoneNumber(DispatchRequest dispatchRequest);
	


}
