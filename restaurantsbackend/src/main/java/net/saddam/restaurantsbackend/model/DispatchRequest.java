package net.saddam.restaurantsbackend.model;

public class DispatchRequest {
	
	
	 private String Shop_ID;
	 private String Order_ID;
	 
	 
	 
	 
	@Override
	public String toString() {
		return "DispatchRequest [Shop_ID=" + Shop_ID + ", Order_ID=" + Order_ID + "]";
	}
	
	
	
	public String getShop_ID() {
		return Shop_ID;
	}
	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}
	public String getOrder_ID() {
		return Order_ID;
	}
	public void setOrder_ID(String order_ID) {
		Order_ID = order_ID;
	}
	 
	 

}
