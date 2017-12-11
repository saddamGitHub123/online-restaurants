package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.Product_Data;
import net.saddam.restaurantsbackend.dto.UniqueProduct;

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
	
	
	/**
	 * List all product using shopId
	 * **/
	
	List<Product_Data> addProductByShopId(String Shop_ID);
	
/**
 *  Get all product list using shopId
 * **/
	
	List<UniqueProduct> productsByShopId(String Shop_ID);
	
	

    Product_Data addProductUsingShopId(String shop_ID);
    
    /**
     * Update product using shopid
     * **/
    
    
    Product_Data updateProduct(String shop_ID,Product_Data productData);
    
    
    
    /**
     * Get all unique productList
     * ****/
    
   List< UniqueProduct> uniqueProductList();
    
    
    
    
    

}
