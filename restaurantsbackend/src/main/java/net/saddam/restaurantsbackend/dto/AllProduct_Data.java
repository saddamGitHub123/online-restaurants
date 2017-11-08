package net.saddam.restaurantsbackend.dto;

import java.util.List;

public class AllProduct_Data {
	

	private String Status_Code;
	private String Status_Message;
	private String Request_Type;
	
	List<Product_Data> productData;
	
	

	public String getRequest_Type() {
		return Request_Type;
	}

	public void setRequest_Type(String request_Type) {
		Request_Type = request_Type;
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

	public List<Product_Data> getProductData() {
		return productData;
	}

	public void setProductData(List<Product_Data> productData) {
		this.productData = productData;
	}

	public AllProduct_Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
