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
	
	@Test
	public void testAdd(){
		user = new User();
		user.setUsername("sa");;
		user.setPassword("12345");
		user.setFirstname("sk");;
		user.setLastname("Hos");;
		user.setEmail("sa@fg");
		user.setAddress("Marathalla");
		user.setPhone(123456);
		
		//add the user
		
		assertEquals("Faild to add user!!",true,userDAO.register(user));
	}

}
