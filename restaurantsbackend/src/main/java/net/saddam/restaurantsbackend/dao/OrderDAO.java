package net.saddam.restaurantsbackend.dao;

import net.saddam.restaurantsbackend.model.OrderRequest;

/**
 * 
 * @author saddam
 *
 */
public interface OrderDAO {
	
	
	//Add the order list using shopID and userID
	
	boolean addOrder(OrderRequest orderRequest);

}
