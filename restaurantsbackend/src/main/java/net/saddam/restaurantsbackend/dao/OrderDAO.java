package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.model.OrderRequest;
import net.saddam.restaurantsbackend.model.OrderRequestAdd;
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
	List<Ordered_List> userOrderListByShopId(String Shop_ID);

}
