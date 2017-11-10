package net.saddam.restaurantsbackend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.saddam.restaurantsbackend.dao.LoginDAO;
import net.saddam.restaurantsbackend.dto.LoginUser;
import net.saddam.restaurantsbackend.dto.Product;
import net.saddam.restaurantsbackend.dto.ShopKeeper;
import net.saddam.restaurantsbackend.dto.User;
/**
 * 
 * @author saddam
 *
 */
@Repository("loginDAO")
@Transactional
public class LoginDAOImpl implements LoginDAO {

    private static final Logger log = LoggerFactory.getLogger(LoginDAOImpl.class);

	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	/**
	 * 
	 * login dao implement class for user only
	 * **/
	
	@Override
	public LoginUser checkLogin(String Username, String Password) {
		
		
		System.out.println("In Check login");
		log.debug("LoginDAOImple() -- getting User name and password for user");
		
		try
		{
			LoginUser loginUser = null;
		String selectActiveCategory = "FROM LoginUser WHERE Username = ? AND Password = ?";
			Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		
		query.setParameter(0,Username);
		query.setParameter(1,Password);
		List <LoginUser> list= query.getResultList();

		if ((list != null) && (list.size() > 0)) {
			//userFound= true;
			log.debug("get successful,User Name and Password found");
			loginUser = list.get(0);
			return loginUser;
		}
		else {
			log.debug("get successful,No User Name and Password found ");
			
		 }

		//session.close();
		return loginUser; 
		}
		catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
		/*finally
		{
			if (sessionFactory != null)
			{
				sessionFactory.close();
			}
		}
*/		
	}
	
	@Override
	public ShopKeeper shopKeeperModel(String Shop_ID) {
		
		ShopKeeper shopKeeper = null;

		try{
			
			String selectProductsByShopId = "FROM ShopKeeper WHERE Shop_ID = :Shop_ID AND active = :active";
			
			List<ShopKeeper> list=  sessionFactory
					.getCurrentSession()
						.createQuery(selectProductsByShopId, ShopKeeper.class)
							.setParameter("Shop_ID", Shop_ID)
							.setParameter("active", true)
								.getResultList();
			
			if ((list != null) && (list.size() > 0)) {
				//userFound= true;
				log.debug("get successful,User Name and Password found");
				shopKeeper = list.get(0);
				return shopKeeper;
			}
			else {
				log.debug("get successful,No User Name and Password found ");
				
			 }
			 
			 
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * login dao implement class for user and shopkeeper
	 * **/

	@Override
	public List<User> checkLoginBoth(String username, String password) {
	
		
		System.out.println("In Check login");
		log.debug("LoginDAOImple() -- getting User name and password for user");
		
		try
		{
		String selectActiveCategory = "FROM User WHERE Username = ? AND Password = ?";
			Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		
		query.setParameter(0,username);
		query.setParameter(1,password);
		List <User> list= query.getResultList();

		if ((list != null) && (list.size() > 0)) {
			//userFound= true;
			log.debug("get successful,User Name and Password found");
			return list;
		}
		else {
			log.debug("get successful,No User Name and Password found ");
			
		 }

		//session.close();
		return list; 
		}
		catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
		finally
		{
			if (sessionFactory != null)
			{
				sessionFactory.close();
			}
		
	}

}
	
	
}
	
