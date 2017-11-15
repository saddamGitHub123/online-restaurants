package net.saddam.restaurantsbackend.model;

import java.util.List;

import net.saddam.restaurantsbackend.dto.Product;
import net.saddam.restaurantsbackend.dto.Response;

public class AllProduct extends Response{


	
	List<Product> product;

	public AllProduct() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
	
	
	
}
