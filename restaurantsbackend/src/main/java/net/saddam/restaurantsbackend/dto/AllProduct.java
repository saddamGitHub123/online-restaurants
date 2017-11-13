package net.saddam.restaurantsbackend.dto;

import java.util.List;

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
