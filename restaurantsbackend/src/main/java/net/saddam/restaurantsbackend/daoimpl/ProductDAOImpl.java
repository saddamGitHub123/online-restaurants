package net.saddam.restaurantsbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.saddam.restaurantsbackend.dao.ProductDAO;
import net.saddam.restaurantsbackend.dto.Product_Data;
/**
 * 
 * @author saddam
 *
 */
@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {
 
	
	private static final Logger log = LoggerFactory.getLogger(ProductDAOImpl.class);

	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 
	 * Add the all product using hibernate
	 * **/
	@Override
	public boolean addProduct(Product_Data product) {
		
	try{
		    log.debug("Add all the product");	
			sessionFactory.getCurrentSession().persist(product);
			
			return true;
			
		}catch (RuntimeException re)
		{
			log.error("Save product failed", re);
			throw re;
		}
	}

	@Override
	public List<Product_Data> addProductByShopId(String Shop_ID) {
		
		
	
		try{
					
		    log.debug("Add all the product ProductDAOImpl class");	
            String selectProductsByShopId = "FROM Product_Data WHERE Shop_ID = :Shop_ID AND Availability = :Availability";
			
			return sessionFactory
					.getCurrentSession()
						.createQuery(selectProductsByShopId, Product_Data.class)
							.setParameter("Shop_ID", Shop_ID)
							.setParameter("Availability", true)
								.getResultList();
			
			
			
			
					}catch (RuntimeException re){
		
			log.error("Save product failed", re);
			throw re;
		
	       }

	}

}
