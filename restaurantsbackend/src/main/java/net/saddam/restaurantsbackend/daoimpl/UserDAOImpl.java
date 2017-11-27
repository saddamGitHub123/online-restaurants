package net.saddam.restaurantsbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.saddam.restaurantsbackend.dao.UserDAO;
import net.saddam.restaurantsbackend.dto.Address;
import net.saddam.restaurantsbackend.dto.Product;
import net.saddam.restaurantsbackend.dto.User;
import net.saddam.restaurantsbackend.dto.UserDetails;
import net.saddam.restaurantsbackend.dto.User_Data;
import net.saddam.restaurantsbackend.model.UpdateRequest;

/**
 * 
 * @author saddam
 *
 */
@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean register(User user) {
		
		try{
			
			sessionFactory.getCurrentSession().persist(user);
			return true;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	

	@Override
	public List<User> list() {
		
			return sessionFactory
					.getCurrentSession()
						.createQuery("FROM User" , User.class)
							.getResultList();
		
	}



	@Override
	public List<Product> productsByShopId(String Shop_ID) {
		// TODO Auto-generated method stub
		
			

		try{
			
			String selectProductsByShopId = "FROM Product WHERE Shop_ID = :Shop_ID AND Availability = :Availability";
			
			return sessionFactory
					.getCurrentSession()
						.createQuery(selectProductsByShopId, Product.class)
							.setParameter("Shop_ID", Shop_ID)
							.setParameter("Availability", true)
								.getResultList();
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
	}

  /**
   * List all the user using shopid it is using for add user 
   * **/

	@Override
	public List<UserDetails> listUserByShopId(String Shop_ID) {
		
		
		try{
					
		    log.debug("List all the user using shopId");	
            String selectProductsByShopId = "FROM UserDetails WHERE Shop_ID = :Shop_ID AND active = :active";
            List<UserDetails> list= sessionFactory
					.getCurrentSession()
					.createQuery(selectProductsByShopId, UserDetails.class)
						.setParameter("Shop_ID", Shop_ID)
						.setParameter("active", true)
							.getResultList();

		if ((list != null) && (list.size() > 0)) {
			//userFound= true;
			log.debug("get successful,Product list is found");
			return list;
		}
		else {
			log.debug("get successful,Product List is not found ");
			
		 }

		//session.close();
		return list; 
		
		}catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
		finally
		{
			/*if (sessionFactory != null)
			{
				sessionFactory.close();
			}*/
		
	    }

	}


	/**
	 * its add the all user details using UserDetails class
	 * ***/

	@Override
	public boolean addUser(UserDetails userDetails) {
		
	try{
		    log.debug("Add all the product");	
			sessionFactory.getCurrentSession().persist(userDetails);
			
			return true;
			
		}catch (RuntimeException re)
		{
			log.error("Save product failed", re);
			throw re;
		}
	}

  /**
   * Update user list using shopid and userid
   * **/


	@Override
	public User_Data updateUser(UpdateRequest updateRequest) {

		
			String Shop_ID = updateRequest.getShop_ID();
			String User_ID = updateRequest.getUser_ID();
			try {

			// get updated data from user
			User_Data userDataRequest = updateRequest.getUser_Data();
			
			// get updated address data from user
			Address addressRequest = userDataRequest.getUserAddress();
			Address address = new Address();

			// select the list of a user data usign shopid and user id in user table
			String selectProductsByShopId = "FROM User_Data WHERE Shop_ID = :Shop_ID AND User_ID = :User_ID";
			List<User_Data> list = sessionFactory.getCurrentSession()
					.createQuery(selectProductsByShopId, User_Data.class).setParameter("Shop_ID", Shop_ID)
					.setParameter("User_ID", User_ID).getResultList();
			

			// creating new object for the response class
			User_Data userData = new User_Data();
			// checking the null value of the user table list
			if ((list != null) && (list.size() > 0)) {
				// userFound= true;
				log.debug("get successful,User Name and Password found");

				userData = list.get(0);

				// set the updated value
				userData.setName(userDataRequest.getName());
				userData.setUsername(userDataRequest.getUsername());
				userData.setPassword(userDataRequest.getPassword());
				userData.setContact(userDataRequest.getContact());
				userData.setEmail(userDataRequest.getEmail());

				// update the database table
				sessionFactory.getCurrentSession().update(userData);

				//
				String selectAddressByuserId = "FROM Address WHERE  User_ID = :User_ID";
				List<Address> addressList = sessionFactory.getCurrentSession()
						.createQuery(selectAddressByuserId, Address.class).setParameter("User_ID", User_ID)
						.getResultList();

				if ((addressList != null) && (addressList.size() > 0)) {
					// userFound= true;
					log.debug("get successful,User Name and Password found");

					address = addressList.get(0);
					address.setArea(addressRequest.getArea());
					address.setCity(addressRequest.getCity());
					address.setHouse_No(addressRequest.getHouse_No());
					address.setLandmark(addressRequest.getLandmark());
					address.setLocality(addressRequest.getLocality());
					address.setPinCode(addressRequest.getPinCode());
					sessionFactory.getCurrentSession().update(address);
					userData.setUserAddress(address);

					return userData;
				}
			}

			return userData;
		} catch (RuntimeException re) {
			log.error("update user and address is faild", re);
			throw re;
		}
	}

	/**
	 * getting a users details using Shopid and UserID
	 * **/


@Override
public User_Data userDetailByShopIdAndUserId(UpdateRequest updateRequest) {

	
	
	
	
	try{
				
	    log.debug("showing one user details usign user id and shopid");
        
	    String Shop_ID = updateRequest.getShop_ID();
	    String User_ID = updateRequest.getUser_ID();		
		User_Data userData = new User_Data();

		
		// select the list of a user data usign shopid and user id in user table
					String selectProductsByShopId = "FROM User_Data WHERE Shop_ID = :Shop_ID AND User_ID = :User_ID";
					List<User_Data> list = sessionFactory.getCurrentSession()
							.createQuery(selectProductsByShopId, User_Data.class).setParameter("Shop_ID", Shop_ID)
							.setParameter("User_ID", User_ID).getResultList();
		
		// checking the null value of the user table list
		if ((list != null) && (list.size() > 0)) {
			// userFound= true;
			log.debug("get successful,User details is found");

			userData = list.get(0);
			
			Address address = new Address();
			String selectAddressByuserId = "FROM Address WHERE  User_ID = :User_ID";
			List<Address> addressList = sessionFactory.getCurrentSession()
					.createQuery(selectAddressByuserId, Address.class).setParameter("User_ID", User_ID)
					.getResultList();

			if ((addressList != null) && (addressList.size() > 0)) {
				// userFound= true;
				log.debug("get successful,Adress details is found");

				address = addressList.get(0);
				userData.setUserAddress(address);

				return userData;
			}
		}

		return userData;
	}catch (RuntimeException re)
	{
		log.error("get failed", re);
		throw re;
	}
	finally
	{
		/*if (sessionFactory != null)
		{
			sessionFactory.close();
		}*/
	
    }

}


/**
 * Getting all user Details in a list using shopID from user and address table 
 * **/
	@Override
	public List<User_Data> userDetailsByShopID(UpdateRequest updateRequest) {

		try{
			
		    log.debug("Entering userDAOImpl class - in userDetailsByShopID()");
	        int count = 0;
	        //getting shopID from user
		    String Shop_ID = updateRequest.getShop_ID();		
			User_Data userData = new User_Data();
			
			// User_Data list of array content all userList
			List<User_Data> userList = new ArrayList();

			
			// select the list of a user data using shopID in user table
						String selectUserDetailsByShopId = "FROM User_Data WHERE Shop_ID = :Shop_ID ";
						List<User_Data> list = sessionFactory.getCurrentSession()
								.createQuery(selectUserDetailsByShopId, User_Data.class).setParameter("Shop_ID", Shop_ID)
								.getResultList();
		
			// checking the null value of the user table list
			if ((list != null) && (list.size() > 0)) {
				log.debug("get successful,User details ShopID is found");
				
				//counting for one by one userList
				for (User_Data entity : list) {
					userData = list.get(count);
					
					String User_ID = userData.getUser_ID();
					Address address = new Address();
					String selectAddressByuserId = "FROM Address WHERE  User_ID = :User_ID";
					List<Address> addressList = sessionFactory.getCurrentSession()
							.createQuery(selectAddressByuserId, Address.class).setParameter("User_ID", User_ID)
							.getResultList();
					
					//Checking the addressList Empty or not
					if ((addressList != null) && (addressList.size() > 0)) {
						log.debug("get successful,Adress details is found");

						address = addressList.get(0);
						//Set the particular address in ther user
						userData.setUserAddress(address);
						
						//Add the all object to array list
						userList.add(userData);
					}
					count++;
				}
				
				return userList;
				
			}

			return userList;
		}catch (RuntimeException re)
		{
			log.error("All UserList failed", re);
			throw re;
		}
		finally
		{
			/*if (sessionFactory != null)
			{
				sessionFactory.close();
			}*/
		
	    }
	}

	
	/**
	 * just for testing the project
	 * **/
	
	/*@Override
	public User_Data updateUser1(UpdateRequest updateRequest) {

		try {
			String Shop_ID = updateRequest.getShop_ID();
			String User_ID = updateRequest.getUser_ID();

			// get updated data from user
			User_Data userDataRequest = updateRequest.getUser_Data();
			// get updated address data from user
			Address addressRequest = userDataRequest.getUserAddress();
			Address address = new Address();

			// select the list of a user data usign shopid and user id in user table
			String selectProductsByShopId = "FROM User_Data WHERE Shop_ID = :Shop_ID AND User_ID = :User_ID";
			List<User_Data> list = sessionFactory.getCurrentSession()
					.createQuery(selectProductsByShopId, User_Data.class).setParameter("Shop_ID", Shop_ID)
					.setParameter("User_ID", User_ID).getResultList();

			// creating new object for the response class
			User_Data userData = new User_Data();
			// checking the null value of the user table list
			if ((list != null) && (list.size() > 0)) {
				// userFound= true;
				log.debug("get successful,User Name and Password found");

				userData = list.get(0);
				
				
				updateRequest.setUser_Data(userDataRequest);
				userData = updateRequest.getUser_Data();

				// set the updated value
				userData.setName(userDataRequest.getName());
				userData.setUsername(userDataRequest.getUsername());
				userData.setPassword(userDataRequest.getPassword());
				userData.setContact(userDataRequest.getContact());
				userData.setEmail(userDataRequest.getEmail());

				// update the database table
				sessionFactory.getCurrentSession().update(userData);

				//
				String selectAddressByuserId = "FROM Address WHERE  User_ID = :User_ID";
				List<Address> addressList = sessionFactory.getCurrentSession()
						.createQuery(selectAddressByuserId, Address.class).setParameter("User_ID", User_ID)
						.getResultList();

				if ((addressList != null) && (addressList.size() > 0)) {
					// userFound= true;
					log.debug("get successful,User Name and Password found");

					address = addressList.get(0);
					address.setArea(addressRequest.getArea());
					address.setCity(addressRequest.getCity());
					address.setHouse_No(addressRequest.getHouse_No());
					address.setLandmark(addressRequest.getLandmark());
					address.setLocality(addressRequest.getLocality());
					address.setPinCode(addressRequest.getPinCode());
					sessionFactory.getCurrentSession().update(address);
					userData.setUserAddress(address);

					return userData;
				}
			}

			return userData;
		} catch (RuntimeException re) {
			log.error("update user and address is faild", re);
			throw re;
		}
	}*/

}