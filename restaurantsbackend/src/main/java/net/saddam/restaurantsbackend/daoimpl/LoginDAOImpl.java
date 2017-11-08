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
	
	@Override
	public List<LoginUser> checkLogin(String Username, int Password) {
		
		
		System.out.println("In Check login");
		//Session session = sessionFactory.openSession();
		//boolean userFound = false;
		//Query using Hibernate Query Language
		//String SQL_QUERY =" from Users as o where o.userName=? and o.userPassword=?";
		//Query query = sessionFactory.createQuery(SQL_QUERY);
		
		
		String selectActiveCategory = "FROM LoginUser WHERE Username = ? AND Password = ?";
			Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		
		query.setParameter(0,Username);
		query.setParameter(1,Password);
		List <LoginUser> list= query.getResultList();

		if ((list != null) && (list.size() > 0)) {
			//userFound= true;
			return list;
		}

		//session.close();
		return list; 
		
	}

}
