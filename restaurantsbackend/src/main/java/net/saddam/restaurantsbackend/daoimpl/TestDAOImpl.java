package net.saddam.restaurantsbackend.daoimpl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.saddam.restaurantsbackend.dao.TestDAO;


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

}
