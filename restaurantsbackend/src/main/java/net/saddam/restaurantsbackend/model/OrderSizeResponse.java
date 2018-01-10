package net.saddam.restaurantsbackend.model;

import java.util.List;

public class OrderSizeResponse extends Response{
	
	List<OrderSizeModel> orderList;
	
	

	public OrderSizeResponse(List<OrderSizeModel> orderList) {
		super();
		this.orderList = orderList;
	}

	public List<OrderSizeModel> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderSizeModel> orderList) {
		this.orderList = orderList;
	}
	
	
	

}
