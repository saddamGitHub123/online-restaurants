package net.saddam.restaurantsbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.saddam.restaurantsbackend.dao.OrderDAO;
import net.saddam.restaurantsbackend.dto.Address;
import net.saddam.restaurantsbackend.dto.Order;
import net.saddam.restaurantsbackend.dto.User;
import net.saddam.restaurantsbackend.model.OrderRequest;
import net.saddam.restaurantsbackend.model.OrderRequestAdd;
import net.saddam.restaurantsbackend.model.Ordered_List;

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
	 * Add order list  from OrderControllr
	 * **/
	
	@Override
	public boolean addOrder(OrderRequest orderRequest) {
		
		try{
		    log.debug("Add all the order list ");	
		    
		    // int count = 0;

		    Order  order = new Order();
		    
		    //Get all the order list from OrderRequest class
		    List<Order> orderList = orderRequest.getOrderList();
		      
		    //Add one by one order list using loop
		    for (int count = 0 ; count < orderList.size(); count++ ) {
		    	
		    	order = orderList.get(count);
		    	//Set shopID and UserID in the order list class
		    	order.setShop_ID(orderRequest.getShop_ID());
		    	order.setUser_ID(orderRequest.getUser_ID());
		    	
		    	//timeStamp coming from user and set that timeStamp
		    	order.setCurrentTimestamp(orderRequest.getTimeStamp());
		    	//Save the order list
		    	sessionFactory.getCurrentSession().persist(order);
		    	//count++;
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
	
	
	

	/**
	 * Add order list  from OrderControllr
	 * **/
	
	@Override
	public boolean addOrderAndOrderID(OrderRequestAdd orderRequest) {
		
		try{
		    log.debug("Add all the order list ");	

		    Order  order = new Order();
		    
		    //Get all the order list from OrderRequest class
		    List<Order> orderList = orderRequest.getOrderList();
		    
		    
		    
        for (int count = 0 ; count < orderList.size(); count++ ) {
		    	
		    	order = orderList.get(count);
		    	//Set shopID and UserID in the order list class
		    	order.setShop_ID(orderRequest.getShop_ID());
		    	order.setUser_ID(orderRequest.getUser_ID());
		    	order.setOrder_ID(orderRequest.getOrder_ID() );
		    	
		    	System.out.println(orderRequest.getOrder_ID());
		    	
		    	//timeStamp coming from user and set that timeStamp
		    	order.setCurrentTimestamp(orderRequest.getTimeStamp());
		    	//Save the order list
		    	sessionFactory.getCurrentSession().persist(order);
		    }

			return true;
			
		}catch (RuntimeException re)
		{
			log.error("Save product failed", re);
			throw re;
		}
		//return false;
	}
	
	
	
	
	
	
	
	
	
	

	/**
	 * Returning all user details and order details using by shopID 
	 * ***/
	
	@Override
	public List<Ordered_List> userOrderListByShopId(String Shop_ID) {
		
		
		try{
		    log.debug("Entering userOrderListByShopId() - at OrderDAOImpl class ");	
		    
		    Ordered_List orderedList;
		    java.sql.Timestamp timeStamp ;
		    String orderID;
		    
		    int count = 0;
		    List<Ordered_List> orderAddList = new ArrayList();
		    Address address =new Address();
		    
		    //getting userID List from user Table
		    /*String selectUserByShopId = "from User where Shop_ID = :Shop_ID ";
			
		     List<User> userList = sessionFactory
					.getCurrentSession()
						.createQuery(selectUserByShopId, User.class)
							.setParameter("Shop_ID", Shop_ID)
								.getResultList();*/
		    
		   // String selectUserByShopId = "from Order where Shop_ID = :Shop_ID "; 
		    
		    //getting unique order_id from order tanle
			
		    String selectUserByShopId = "from Order where Shop_ID = :Shop_ID GROUP BY Order_ID ";
		     List<Order> userList = sessionFactory
					.getCurrentSession()
						.createQuery(selectUserByShopId, Order.class)
							.setParameter("Shop_ID", Shop_ID)
								.getResultList();
		    
		     
		     for (Order entity : userList) {
		    	 
		    	 //getting userID from user table using shopID
		    	 String User_ID = userList.get(count).getUser_ID();
		    	 String Order_ID = userList.get(count).getOrder_ID();
		    	 
		    	 //getting list of order using userID and orderID
		    	  String selectOrderByUserID = "from Order where User_ID = :User_ID AND Order_ID = :Order_ID ";
					
				     List<Order> orderList = sessionFactory
							.getCurrentSession()
								.createQuery(selectOrderByUserID, Order.class)
									.setParameter("User_ID", User_ID)
									.setParameter("Order_ID", Order_ID)
										.getResultList();
				     
				     //set timeStamp
				     if((orderList != null) && (orderList.size() > 0)) {
				      timeStamp = orderList.get(0).getCurrentTimestamp();
				      orderID = orderList.get(0).getOrder_ID();
				      
				     }
				     else {
				    	 timeStamp = null;
				    	 orderID = null;
				     }
				     
				   //getting Address using userID
			    	  String selectAddressByUserID = "from Address where User_ID = :User_ID ";
						
					     List<Address> addressList = sessionFactory
								.getCurrentSession()
									.createQuery(selectAddressByUserID, Address.class)
										.setParameter("User_ID", User_ID)
											.getResultList();
					     
					     //convert address list to object
					     if ((addressList != null) && (addressList.size() > 0)) {
					    	 address = addressList.get(0);

							}
					     else {
					    	 address = null;
					     }

					     
					     orderedList = new Ordered_List(User_ID,address,orderList,timeStamp,orderID);
					     orderAddList.add(orderedList);
					     count++;
		     }
		     
		     
    
		   // System.out.println(orderedList);
 
		    return orderAddList;
		}catch (RuntimeException re)
		{
			log.error("Returrning order list failed", re);
			throw re;
		}
		
		
		
	}

	
	
	
	
	
	
	
}
