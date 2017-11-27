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


	/**
	 * Returning all user details and order details using by shopID 
	 * ***/
	
	@Override
	public List<Ordered_List> userOrderListByShopId(String Shop_ID) {
		
		
		try{
		    log.debug("Entering userOrderListByShopId() - at OrderDAOImpl class ");	
		    
		    Ordered_List orderedList;
		    
		    int count = 0;
		    List<Ordered_List> orderAddList = new ArrayList();
		    Address address =new Address();
		    
		    //getting userID List from user Table
		    String selectUserByShopId = "from User where Shop_ID = :Shop_ID ";
			
		     List<User> userList = sessionFactory
					.getCurrentSession()
						.createQuery(selectUserByShopId, User.class)
							.setParameter("Shop_ID", Shop_ID)
								.getResultList();
		     
		     for (User entity : userList) {
		    	 
		    	 //getting userID from user table using shopID
		    	 String User_ID = userList.get(count).getUser_Id();
		    	 
		    	 //getting list of order using userID
		    	  String selectOrderByUserID = "from Order where User_ID = :User_ID ";
					
				     List<Order> orderList = sessionFactory
							.getCurrentSession()
								.createQuery(selectOrderByUserID, Order.class)
									.setParameter("User_ID", User_ID)
										.getResultList();
				     
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

					     
					     orderedList = new Ordered_List(User_ID,address,orderList);
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
