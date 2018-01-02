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
import net.saddam.restaurantsbackend.dto.Price;
import net.saddam.restaurantsbackend.model.DispatchRequest;
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
		    
		    boolean dispatchValue=true;
		    
		    //Get all the order list from OrderRequest class
		    List<Order> orderList = orderRequest.getOrderList();
		    
		    
		    
        for (int count = 0 ; count < orderList.size(); count++ ) {
		    	
		    	order = orderList.get(count);
		    	//Set shopID and UserID in the order list class
		    	order.setShop_ID(orderRequest.getShop_ID());
		    	order.setUser_ID(orderRequest.getUser_ID());
		    	order.setOrder_ID(orderRequest.getOrder_ID() );
		    	order.setDispatch(dispatchValue);
		    	
		    	System.out.println(orderRequest.getOrder_ID());
		    	
		    	//timeStamp coming from user and set that timeStamp
		    	order.setCurrentTimestamp(orderRequest.getTimeStamp());
		    	//Save the order list
		    	sessionFactory.getCurrentSession().persist(order);
		    	
		    	log.debug("Returing from the addOrderAndOrderID  method");
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
		    
		   // int count = 0;
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
		    
		    //getting unique order_id from order table
			
		    String selectUserByShopId = "from Order where Shop_ID = :Shop_ID GROUP BY Order_ID ";
		     List<Order> userList = sessionFactory
					.getCurrentSession()
						.createQuery(selectUserByShopId, Order.class)
							.setParameter("Shop_ID", Shop_ID)
								.getResultList();
		    
		     
		    // for (Order entity : userList) {
		    

			     for (int count = 0 ; count<userList.size();count++) {
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
				     
				     //set timeStamp and OrderID 
				     if((orderList != null) && (orderList.size() > 0)) {
				      timeStamp = orderList.get(0).getCurrentTimestamp();
				      orderID = orderList.get(0).getOrder_ID();
				      
				     }
				     else {
				    	 timeStamp = null;
				    	 orderID = null;
				     }
				     
				   //getting Address using userID
			    	  String selectAddressByUserID = "from Address where User_ID = :User_ID AND Shop_ID = :Shop_ID";
						
					     List<Address> addressList = sessionFactory
								.getCurrentSession()
									.createQuery(selectAddressByUserID, Address.class)
										.setParameter("User_ID", User_ID)
										.setParameter("Shop_ID", Shop_ID)
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
					    // count++;
		     }
		     
		     log.error("Returrning order list");  
    
		   // System.out.println(orderedList);
 
		    return orderAddList;
		}catch (RuntimeException re)
		{
			log.error("Returrning order list failed", re);
			throw re;
		}
		
		
		
	}

   
	/*
	 * Order Dispatch method for dispatch the orderList
	 * **/


	@Override
	public boolean orderDispatchOrderID(DispatchRequest dispatchRequest) {
		
		String Order_ID = dispatchRequest.getOrder_ID();
		String Shop_ID = dispatchRequest.getShop_ID();
	
			
  	try{
	    log.debug("Entering orderDispatchOrderID() - at OrderDAOImpl class ");	
	    
	
		
		//getting list of order using shopID and orderID
  	  String selectOrderByOrderID = "from Order where Shop_ID = :Shop_ID AND Order_ID = :Order_ID ";
	    
		     List<Order> orderList = sessionFactory
					.getCurrentSession()
						.createQuery(selectOrderByOrderID, Order.class)
							.setParameter("Shop_ID", Shop_ID)
							.setParameter("Order_ID", Order_ID)
								.getResultList();
		     
		  //get one by one orderlist
		     
		     System.out.println(orderList.size());
		     for(int i = 0 ;i<orderList.size();i++) {
		    	 Price pri; 
		    	 //Set list of value 
		    	 // Shop_ID = orderList.get(i).getShop_ID();
		    	  String Product_ID = orderList.get(i).getProduct_ID();
		    	  String Price = orderList.get(i).getPrice();
		    	  String qty = orderList.get(i).getQty();
		    	  String unit = orderList.get(i).getUnits();
		    	  //String Stock = qty * unit;
		    	  int integerNumber = Integer.parseInt(qty);
		    	  int integerNumber1 = Integer.parseInt(unit);
		    	  int stock1 = integerNumber*integerNumber1;
		    	  
		    	  //list of price using shopID and price and productID 
		    	  
		    	  String selectOrderByPrice = "from Price where Shop_ID = :Shop_ID AND Product_ID =:Product_ID AND Price =:Price ";
		  	    
		    	  //Getting single price ,quty and stock 
				     List<Price> priceList = sessionFactory
							.getCurrentSession()
								.createQuery(selectOrderByPrice, Price.class)
									.setParameter("Shop_ID", Shop_ID)
									.setParameter("Product_ID", Product_ID)
									.setParameter("Price", Price)
										.getResultList();
				     
				     //getting that particular qty_price and id
				     String Qty_Price = priceList.get(0).getQty_Price();
				     int ID = priceList.get(0).getID();
				     //convert stock price string to integer
				     int Stock2 =Integer.parseInt(priceList.get(0).getStock());
				     
				     //Check stock is empty or less from order
		    	    if(Stock2> stock1 ) {
		    	    	int Stock3 = (Integer.parseInt(priceList.get(0).getStock()) - stock1) ;
					     String Stock = String.valueOf(Stock3);
					     
					     //If qty_price is empty or not
		    	    	if( Qty_Price != null) {
						     
		    	    		//Qty_Price not empty
						  // update particular column using ID and productID and shopID
								String updateSingleValu = "UPDATE Price SET Price = :Price , Qty_Price =:Qty_Price , Stock =:Stock WHERE ID = :ID AND Product_ID = :Product_ID";
								
								//set the data base value usign hibernat query
								int updatedEntities = sessionFactory.getCurrentSession()
										 .createQuery( updateSingleValu )
										 .setParameter( "ID", ID )
								        .setParameter( "Price", Price )
								        .setParameter( "Qty_Price", Qty_Price )
								        .setParameter( "Stock", Stock )
								        .setParameter( "Product_ID", Product_ID )
								        .executeUpdate();
		    	    	}else {
		    	    	//pri = new Price(Product_ID,Shop_ID,Price, Stock);
					     System.out.println(Stock);
					     
					     //If qty_price is  empty
					  // update particular column using ID and productID and shopID
					     String updateSingleValu = "UPDATE Price SET Price = :Price, Stock =:Stock WHERE ID = :ID AND Product_ID = :Product_ID";
							
							//set the data base value usign hibernat query
							int updatedEntities = sessionFactory.getCurrentSession()
									 .createQuery( updateSingleValu )
									 .setParameter( "ID", ID )
							        .setParameter( "Price", Price )
							        //.setParameter( "Qty_Price", Qty_Price )
							        .setParameter( "Stock", Stock )
							        .setParameter( "Product_ID", Product_ID )
							        .executeUpdate();

		    	    	}
		    	    	
		    	    	//return true;
		    	    }
		    	    else {
		    	    	System.out.println("Stock is not avalible");
		    	    	return false;
		    	    }
				     
		    	  
		     }		     
		
		return true;
	   }catch (RuntimeException re)
	   {
		log.error("Returrning order list failed", re);
		throw re;
	   }

 }
	
	
	
	
	
	
}
