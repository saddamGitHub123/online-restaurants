package net.saddam.restaurantsbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.saddam.restaurantsbackend.dao.UserDAO;
import net.saddam.restaurantsbackend.dto.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	
	@BeforeClass
	public static void init(){
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.saddam.restaurantsbackend");
		context.refresh();
		userDAO =(UserDAO) context.getBean("userDAO");
	}
	
/*	@Test
	public void testAddCategory(){
		
		user  = new User();
		
		user.setName("TouchCor4");
		user.setUserid("abc3");
		user.setShopid("bbc3");
		user.setUsername("newo3");
		user.setPassword(123123);
		user.setContact(234123);
		user.setEmail("sss@gmail.com");

		
		assertEquals("Successfully assed a category inside the table",true,userDAO.register(user));
		
		
	}*/
	
	
	@Test
	public void testListCategory(){
		//get the category by its id
		
		/*category = categoryDAO.get(34);
		
		category.setName("TV");*/
		assertEquals("Successfully fatch the list of category from the table",3,userDAO.list().size());
		
	}

}
