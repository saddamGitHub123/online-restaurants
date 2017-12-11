package net.saddam.restaurantsbackend.dto;

import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderlist")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID; 
	private String code;
    private String Shop_ID;
    private String User_ID;
    private String Product_ID;
    private String Product_Name;
    private String Units;
    private String Qty; 
    
    @Column(name="TimeStamp")
    java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    
    public Order() {
		 this.code = UUID.randomUUID().toString().substring(26).toUpperCase();
	 }


	@Override
	public String toString() {
		return "Order [ID=" + ID + ", code=" + code + ", Shop_ID=" + Shop_ID + ", User_ID=" + User_ID + ", Product_ID="
				+ Product_ID + ", Product_Name=" + Product_Name + ", Units=" + Units + ", Qty=" + Qty
				+ ", currentTimestamp=" + currentTimestamp + "]";
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShop_ID() {
		return Shop_ID;
	}

	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public String getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(String product_ID) {
		Product_ID = product_ID;
	}

	public String getProduct_Name() {
		return Product_Name;
	}

	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}

	public String getUnits() {
		return Units;
	}

	public void setUnits(String units) {
		Units = units;
	}

	public String getQty() {
		return Qty;
	}

	public void setQty(String qty) {
		Qty = qty;
	}

	public java.sql.Timestamp getCurrentTimestamp() {
		return currentTimestamp;
	}

	public void setCurrentTimestamp(java.sql.Timestamp currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}

    
    
 
}
