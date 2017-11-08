package net.saddam.restaurantsbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.saddam.restaurantsbackend.dao.ProductDAO;
import net.saddam.restaurantsbackend.dto.Product;


/**
 * 
 * @author saddam
 *
 */

public class ProductTestCase {

	
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init(){
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.saddam.restaurantsbackend");
		context.refresh();
		productDAO =(ProductDAO) context.getBean("productDAO");
	}
	
	@Test
	public void testAddProduct(){
		
		product  = new Product();
		
		product.setShop_ID("shop004");
		product.setProduct_ID("product006");
		product.setProduct_Name("apple4");
		product.setProduct_Price(30);
		product.setProduct_Image("image02");
		product.setProduct_Category("Grocery");
		product.setProduct_Type("l_001");
		product.setAvailability(true);

		
	//	assertEquals("Successfully assed a category inside the table",true,productDAO.addProduct(product));
		
		
	}
}
