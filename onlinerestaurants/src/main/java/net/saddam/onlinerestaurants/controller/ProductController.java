package net.saddam.onlinerestaurants.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.saddam.restaurantsbackend.common.ApiErrors;
import net.saddam.restaurantsbackend.common.JsonResponse;
import net.saddam.restaurantsbackend.dao.ProductDAO;
import net.saddam.restaurantsbackend.dao.UserDAO;
import net.saddam.restaurantsbackend.dto.AllProduct;
import net.saddam.restaurantsbackend.dto.AllProduct_Data;
import net.saddam.restaurantsbackend.dto.Product;
import net.saddam.restaurantsbackend.dto.Product_Data;

/**
 * 
 * @author sk saddam hosan
 *
 */
@Controller
@EnableWebMvc
@RequestMapping("/product")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	
	
	@Autowired
	public UserDAO userDAO;
	
	@Autowired
	public ProductDAO productDAO;
	
	
	
	 /**
	  * Using shopID to get all product list
	  * 
	  * **
	  */

	    @RequestMapping(value = "/list/shop/{shopId}/user/{userId}", method = RequestMethod.GET)
	    public @ResponseBody
	    AllProduct listOfProductByShopId(HttpServletRequest request,@PathVariable("shopId")String shopId,
	    		                                                   @PathVariable("userId")String userId )
	            								 
	    {
	        logger.info("Entered listOfProductByShopId()  - Get a Product list from shopId");	        
	        AllProduct allProduct = new AllProduct();
	        

	        try
			{
	        	
	        	
				
				if (userId == null) {
				    //** no products exist, error message *//
				    allProduct.setStatus_Code(JsonResponse.CODE__EMPTY);
				    allProduct.setStatus_Message(JsonResponse.CODE__ERROR);
				    logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
				    return allProduct;
				}
				
				List<Product> listOfSimpleEntities = userDAO.productsByShopId(shopId);
				allProduct.setProduct(listOfSimpleEntities);
				 
				if (listOfSimpleEntities == null || listOfSimpleEntities.size()== 0 ) {
				    //** no products exist, error message *//
				    allProduct.setStatus_Code(JsonResponse.CODE__EMPTY);
				    allProduct.setStatus_Message(ApiErrors.ERROR__NO_PRODUCTS_EXIST);
				    logger.error(ApiErrors.ERROR__NO_PRODUCTS_EXIST);
				    return allProduct;
				}
				
				
				
				//** set status OK *//*
				allProduct.setStatus_Code(JsonResponse.CODE__OK);
				allProduct.setStatus_Message("Successfully Authenticated");
				
			}
			catch (Exception e)
			{
				logger.error("listOfProductByShopId(): Error - " + e);
				allProduct.setStatus_Code(JsonResponse.CODE__UNKNOWN_ERROR);
				allProduct.setStatus_Message(JsonResponse.CODE__UNKNOWN_ERROR);
	            return allProduct;
			}
	        logger.info("Returning listOfProductByShopId()");

	        return allProduct;
	    }
	    
	    
	    
	    @RequestMapping(value = "/list/shop/{shopId}", method = RequestMethod.GET)
	    public @ResponseBody
	    AllProduct_Data addAllProduct(HttpServletRequest request,@PathVariable("shopId")String shopId ) {
	    	
	    	 logger.info("Entered addAllProduct()  - Save all the product ");	   								 
	    	
	    	 //Product_Data product = new Product_Data();
	    	 AllProduct_Data allProduct = new AllProduct_Data();
	    	 
	    	 List<Product_Data> productList =new ArrayList();
	    	 
	    	 Product_Data product = new Product_Data();
	    	
	    	 String productName = "Product_";
	    	 int count = 0;
	    	
	    	try
			{
	    		if (shopId == null) {
				    //** no products exist, error message *//
					//product.setStatus_Code(JsonResponse.CODE__EMPTY);
					//product.setStatus_Message(JsonResponse.CODE__ERROR);
				    logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
				    return allProduct;
				}
	    		
	    		List<Product_Data> listOfProduct = productDAO.addProductByShopId(shopId);
	    		
	    		
	    		/*When new shop id will come*/
	    		if (listOfProduct == null || listOfProduct.size()== 0 ) {
				    //** no products exist, Add new product *//
	    			 int i=0;
	    			 product .setProduct_Name("Apple");
	    			 product.setShop_ID(shopId);
	    			 product.setProduct_Price(200);
	    			 product.setProduct_Image("image_1");
	    			 product.setProduct_Category("Grocery");
	    			 product.setProduct_Type("L_01");
	    			 product.setAvailability(true);
	    			 
	    			 product.setProduct_ID(productName+i);	
	    			 
	    			 productList.add(product);
	    			 allProduct.setStatus_Code(JsonResponse.CODE__OK);
	    			 allProduct.setStatus_Message("Successfully Authenticated");
	    			 allProduct.setRequest_Type("Add-New_Product");
	    			 allProduct.setProductData(productList);
	    			 productDAO.addProduct(product);
				    return allProduct;
				}
	    		
	    		//Add new product of existing product
	    		
	    	 product .setProduct_Name("Apple");
   			 product.setShop_ID(shopId);
   			 product.setProduct_Price(200);
   			 product.setProduct_Image("image_1");
   			 product.setProduct_Category("Grocery");
   			 product.setProduct_Type("L_01");
   			 product.setAvailability(true);
   			 
   			 
   			for (Product_Data entity : listOfProduct) {
	            // do something useful with entity;
	        	
	        	System.out.println("The value of database: "+ entity.getProduct_ID());
	        	count++;
	        	
	        }
   			 product.setProduct_ID(productName+count);
   			 
   			productList.add(product);
			 allProduct.setStatus_Code(JsonResponse.CODE__OK);
			 allProduct.setStatus_Message("Successfully Authenticated");
			 allProduct.setRequest_Type("Update_Product");
			 allProduct.setProductData(productList);
   			 
   			 productDAO.addProduct(product);
			
				//** set status OK *//*
				//allProduct.setStatus_Code(JsonResponse.CODE__OK);
				//allProduct.setStatus_Message("Successfully Authenticated");
				
			}
			catch (Exception e)
			{
				logger.error("listOfProductByShopId(): Error - " + e);
				allProduct.setStatus_Code(JsonResponse.CODE__UNKNOWN_ERROR);
				allProduct.setStatus_Message(JsonResponse.CODE__UNKNOWN_ERROR);
	            return allProduct;
			}
	        logger.info("Returning listOfProductByShopId()");
	    	
	    	return allProduct;
	    	
	    }
	    

}