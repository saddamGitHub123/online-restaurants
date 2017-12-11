package net.saddam.restaurantsbackend.model;

import java.util.List;

import net.saddam.restaurantsbackend.dto.UniqueProduct;

public class AllProduct extends Response{


	//price are not working
	//List<Product> product;
	
	//price is working
	List<UniqueProduct> uniqueProduct;
	
	public AllProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public AllProduct(List<UniqueProduct> uniqueProduct) {
		super();
		this.uniqueProduct = uniqueProduct;
	}


	public List<UniqueProduct> getUniqueProduct() {
		return uniqueProduct;
	}


	public void setUniqueProduct(List<UniqueProduct> uniqueProduct) {
		this.uniqueProduct = uniqueProduct;
	}





	
	
	/*public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}*/
	
	
	
	
	
}
