package net.saddam.restaurantsbackend.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Productlist")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;	
	
	private String Product_Name;
	private String Product_ID;
	private String Shop_ID;
	 private int Product_Price;
	 private String Product_Image;
	 private String Product_Category;
	 private String Product_Type;
	 private boolean Availability ;
	 
	 
	  
	 
	
	@Override
	public String toString() {
		return "Product [ID=" + ID + ", Product_Name=" + Product_Name + ", Product_ID=" + Product_ID + ", Shop_ID="
				+ Shop_ID + ", Product_Price=" + Product_Price + ", Product_Image=" + Product_Image
				+ ", Product_Category=" + Product_Category + ", Product_Type=" + Product_Type + ", Availability="
				+ Availability + "]";
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getProduct_Name() {
		return Product_Name;
	}
	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}
	public int getProduct_Price() {
		return Product_Price;
	}
	public void setProduct_Price(int product_Price) {
		Product_Price = product_Price;
	}
	public String getProduct_Image() {
		return Product_Image;
	}
	public String getProduct_ID() {
		return Product_ID;
	}
	public void setProduct_ID(String product_ID) {
		Product_ID = product_ID;
	}
	public String getShop_ID() {
		return Shop_ID;
	}
	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}
	public void setProduct_Image(String product_Image) {
		Product_Image = product_Image;
	}
	public String getProduct_Category() {
		return Product_Category;
	}
	public void setProduct_Category(String product_Category) {
		Product_Category = product_Category;
	}
	public String getProduct_Type() {
		return Product_Type;
	}
	public void setProduct_Type(String product_Type) {
		Product_Type = product_Type;
	}
	public boolean isAvailability() {
		return Availability;
	}
	public void setAvailability(boolean availability) {
		Availability = availability;
	}
	 
	 
	 
	 
	
	

}
