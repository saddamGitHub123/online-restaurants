package net.saddam.restaurantsbackend.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Productlist")
public class UniqueProductResponse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;	
	
	private String Product_ID;
	private String Shop_ID;
	private String Product_Name;
	 //private int Product_Price;
	 private String Product_Image;
	 private String Product_Category;
	 private String Product_Type;
	 private boolean Availability ;
     

	 
	 public UniqueProductResponse() {}
	 
	 
	 
	 
	 
	public UniqueProductResponse(int iD, String product_ID, String shop_ID, String product_Name, String product_Image,
			String product_Category, String product_Type, boolean availability) {
		super();
		ID = iD;
		Product_ID = product_ID;
		Shop_ID = shop_ID;
		Product_Name = product_Name;
		Product_Image = product_Image;
		Product_Category = product_Category;
		Product_Type = product_Type;
		Availability = availability;
	}


	@Override
	public String toString() {
		return "UniqueProductResponse [ID=" + ID + ", Product_ID=" + Product_ID + ", Shop_ID=" + Shop_ID
				+ ", Product_Name=" + Product_Name + ", Product_Image=" + Product_Image + ", Product_Category="
				+ Product_Category + ", Product_Type=" + Product_Type + ", Availability=" + Availability + "]";
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
	/*public int getProduct_Price() {
		return Product_Price;
	}
	public void setProduct_Price(int product_Price) {
		Product_Price = product_Price;
	}*/
	public String getProduct_Image() {
		return Product_Image;
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
