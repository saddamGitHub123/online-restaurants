package net.saddam.restaurantsbackend.dto;

import java.util.List;

public class AllProduct {

	
	private String Status_Code;
	private String Status_Message;
	
	List<Product> product;
	
	
	
	
	

	public AllProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStatus_Code() {
		return Status_Code;
	}

	public void setStatus_Code(String status_Code) {
		Status_Code = status_Code;
	}

	public String getStatus_Message() {
		return Status_Message;
	}

	public void setStatus_Message(String status_Message) {
		Status_Message = status_Message;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
	
	
	
}
