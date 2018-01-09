package net.saddam.restaurantsbackend.model;

import java.util.List;

public class ShopkeeperOrderResponse extends Response{
	
   List<Ordered_List> orderList;
   int  orderSize;

public int getOrderSize() {
	return orderSize;
}

public void setOrderSize(int orderSize) {
	this.orderSize = orderSize;
}

public List<Ordered_List> getOrderList() {
	return orderList;
}

public void setOrderList(List<Ordered_List> orderList) {
	this.orderList = orderList;
}

public ShopkeeperOrderResponse(List<Ordered_List> orderList) {
	super();
	this.orderList = orderList;
}
   
   

}
