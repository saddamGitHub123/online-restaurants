package net.saddam.restaurantsbackend.dao;

import java.util.List;

import net.saddam.restaurantsbackend.dto.Product_Data;
import net.saddam.restaurantsbackend.dto.UniqueProduct;
import net.saddam.restaurantsbackend.model.Product_Model;

/**
 * 
 * @author saddam
 *
 */

public interface ProductDAO {
	
	/*
	 * Add Product in the database
	 * **/
	//boolean addProduct(Product_Data product);
	boolean addProduct(Product_Model product);
	
	
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
