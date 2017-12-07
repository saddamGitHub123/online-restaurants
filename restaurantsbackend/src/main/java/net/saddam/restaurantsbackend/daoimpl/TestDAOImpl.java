package net.saddam.restaurantsbackend.daoimpl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.saddam.restaurantsbackend.dao.TestDAO;
import net.saddam.restaurantsbackend.dto.User;


/**
 * 
 * @author saddam
 *
 */
@Repository("testDAO")
@Transactional
public class TestDAOImpl implements TestDAO {
	
	private static final Logger log = LoggerFactory.getLogger(TestDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User saveOTPasPaswd(User user) {
		// TODO Auto-generated method stub
		
		
		String Password = user.getPassword();
		String Shop_ID = user.getShop_ID();
		String User_Id = user.getUser_Id();
		
		// update particular column using shopid and userid
		String selectProductsByShopId = "UPDATE User SET Password = :Password WHERE Shop_ID = :Shop_ID AND User_Id = :User_Id";
		
		//set the data base value
		int updatedEntities = sessionFactory.getCurrentSession()
				 .createQuery( selectProductsByShopId )
		        .setParameter( "Password", Password )
		        .setParameter( "Shop_ID", Shop_ID )
		        .setParameter( "User_Id", User_Id )
		        .executeUpdate();
		
		if(updatedEntities == 1)
		  return user;
		else 
			return user;
		
	}

}
