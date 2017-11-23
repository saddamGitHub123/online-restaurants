package net.saddam.restaurantsbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.saddam.restaurantsbackend.dao.OrderDAO;
import net.saddam.restaurantsbackend.dto.Order;
import net.saddam.restaurantsbackend.model.OrderRequest;

/**
 * 
 * @author saddam
 *
 */
@Repository("orderDAO")
@Transactional
public class OrderDAOImpl implements OrderDAO {
	
	private static final Logger log = LoggerFactory.getLogger(OrderDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	
	/**
	 * Add order list comming from OrderControllr
	 * **/
	
	@Override
	public boolean addOrder(OrderRequest orderRequest) {
		
		try{
		    log.debug("Add all the order list ");	
		    
		    int count = 0;

		    Order  order = new Order();
		    
		    //Get all the order list from OrderRequest class
		    List<Order> orderList = orderRequest.getOrderList();
		      
		    //Add one by one order list using loop
		    for (Order entity : orderList) {
		    	
		    	order = orderList.get(count);
		    	//Set shopID and UserID in the order list class
		    	order.setShop_ID(orderRequest.getShop_ID());
		    	order.setUser_ID(orderRequest.getUser_ID());		    	
		    	//Save the order list
		    	sessionFactory.getCurrentSession().persist(order);
		    	count++;
		    	//System.out.println(order);
		    }
			
			return true;
			
		}catch (RuntimeException re)
		{
			log.error("Save product failed", re);
			throw re;
		}
		//return false;
	}

	
	
	
	
	
	
	
}
