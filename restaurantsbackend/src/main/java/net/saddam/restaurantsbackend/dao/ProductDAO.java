package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.Product;
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
	
/**
 *  Get all product list using shopId
 * **/
	
	List<Product> productsByShopId(String Shop_ID);

Product_Data addProductUsingShopId(String shop_ID);

}
