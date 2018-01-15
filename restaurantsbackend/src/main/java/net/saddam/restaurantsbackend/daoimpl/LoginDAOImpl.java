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
import net.saddam.restaurantsbackend.dto.Order;
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
	 **/

	@Override
	public LoginUser checkLogin(String Username, String Password) {

		System.out.println("In Check login");
		log.debug("LoginDAOImple() -- getting User name and password for user");

		try {
			LoginUser loginUser = null;
			String selectActiveCategory = "FROM LoginUser WHERE Username = ? AND Password = ? AND is_active =:is_active";
			Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);

			query.setParameter(0, Username);
			query.setParameter(1, Password);
			query.setParameter("is_active", true);
			List<LoginUser> list = query.getResultList();

			if ((list != null) && (list.size() > 0)) {
				// userFound= true;
				log.debug("get successful,User Name and Password found");
				loginUser = list.get(0);
				return loginUser;
			} else {
				log.debug("get successful,No User Name and Password found ");

			}

			// session.close();
			return loginUser;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			/*
			 * if (sessionFactory != null) { sessionFactory.close(); }
			 */
		}

	}

	/**
	 * 
	 * login dao implement class for user and shopkeeper
	 **/

	@Override
	public List<User> checkLoginBoth(String username, String password) {

		System.out.println("In Check login");
		log.debug("LoginDAOImple() -- getting User name and password for user");

		try {
			String selectActiveCategory = "FROM User WHERE Username = ? AND Password = ?";
			Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);

			query.setParameter(0, username);
			query.setParameter(1, password);
			List<User> list = query.getResultList();

			if ((list != null) && (list.size() > 0)) {
				// userFound= true;
				log.debug("get successful,User Name and Password found");
				return list;
			} else {
				log.debug("get successful,No User Name and Password found ");

			}

			// session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			/*
			 * if (sessionFactory != null) { sessionFactory.close(); }
			 */

		}

	}

	/**
	 * Returring particular user details usign phone number (forgot password)
	 * **/
	@Override
	public LoginUser getUserDataValue(LoginUser loginuser) {
		// TODO Auto-generated method stub
		
		try {
		LoginUser loginUser= null;
		String Contact = loginuser.getContact();
		String selectUserByShopId = "from LoginUser where Contact = :Contact ";
		
		   List<LoginUser> userList = sessionFactory
					.getCurrentSession()
						.createQuery(selectUserByShopId, LoginUser.class)
							.setParameter("Contact", Contact)
								.getResultList();
		
		/*List<LoginUser> list = sessionFactory.getCurrentSession().createQuery("FROM LoginUser where Contact:=Contact ")
		.setParameter("Contact", Contact).getResultList();*/
		   if ((userList != null) && (userList.size() > 0)) {
				// userFound= true;
				log.debug("get successful,User Name and Password found");
				 loginUser = userList.get(0);
				return loginUser;
			} else {
				log.debug("get successful,No Phone number is found ");
				return loginUser;
			}

		//return loginUser;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			/*
			 * if (sessionFactory != null) { sessionFactory.close(); }
			 */

		}
	}

}
