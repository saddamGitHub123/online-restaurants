package net.saddam.restaurantsbackend.model;

import net.saddam.restaurantsbackend.dto.Product_Data;
import net.saddam.restaurantsbackend.dto.Response;

public class RequestProduct extends Response{


	private String Request_Type;
	private String Shop_ID;
	
	Product_Data productData;

    
	public String getShop_ID() {
		return Shop_ID;
	}


	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}


	public Product_Data getProductData() {
		return productData;
	}

	public void setProductData(Product_Data productData) {
		this.productData = productData;
	}

	public String getRequest_Type() {
		return Request_Type;
	}

	public void setRequest_Type(String request_Type) {
		Request_Type = request_Type;
	}


	public RequestProduct() {
		super();
		// TODO Auto-generated constructor stub
	}





	
	
	

}
