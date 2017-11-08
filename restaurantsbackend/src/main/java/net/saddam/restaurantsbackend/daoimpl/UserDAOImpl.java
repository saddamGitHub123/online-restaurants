package net.saddam.restaurantsbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.saddam.restaurantsbackend.dao.UserDAO;
import net.saddam.restaurantsbackend.dto.Product;
import net.saddam.restaurantsbackend.dto.User;

/**
 * 
 * @author saddam
 *
 */
@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

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




}