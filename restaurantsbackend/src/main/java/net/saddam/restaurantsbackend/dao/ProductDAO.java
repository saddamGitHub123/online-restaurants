package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.Product_Data;

/**
 * 
 * @author saddam
 *
 */

public interface ProductDAO {
	
	/*
	 * Add Product in the database
	 * **/
	boolean addProduct(Product_Data product);
	
	List<Product_Data> addProductByShopId(String Shop_ID);

}
