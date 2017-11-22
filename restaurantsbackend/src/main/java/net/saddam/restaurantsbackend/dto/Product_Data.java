	package net.saddam.restaurantsbackend.dto;

	import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


	@Entity
	@Table(name = "Productlist")
	public class Product_Data implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@JsonIgnore
	    private int ID;	
		
		private String code;
		@JsonIgnore
		private String Product_Name;
		
		private String Product_ID;
		private String Shop_ID;
		@JsonIgnore
		 private int Product_Price;
		@JsonIgnore
		 private String Product_Image;
		@JsonIgnore
		 private String Product_Category;
		@JsonIgnore
		 private String Product_Type;
		@JsonIgnore
		 private boolean Availability ;
		 


		 public Product_Data() {
			 this.code = UUID.randomUUID().toString().substring(26).toUpperCase();
		 }
		 
		  
		 
		

		
			
		
		
		
		@Override
		public String toString() {
			return "Product_Data [ID=" + ID + ", code=" + code + ", Product_Name=" + Product_Name + ", Product_ID="
					+ Product_ID + ", Shop_ID=" + Shop_ID + ", Product_Price=" + Product_Price + ", Product_Image="
					+ Product_Image + ", Product_Category=" + Product_Category + ", Product_Type=" + Product_Type
					+ ", Availability=" + Availability + "]";
		}










		public Product_Data(int iD, String code, String product_Name, String product_ID, String shop_ID,
				int product_Price, String product_Image, String product_Category, String product_Type,
				boolean availability) {
			super();
			ID = iD;
			this.code = code;
			Product_Name = product_Name;
			Product_ID = product_ID;
			Shop_ID = shop_ID;
			Product_Price = product_Price;
			Product_Image = product_Image;
			Product_Category = product_Category;
			Product_Type = product_Type;
			Availability = availability;
		}




		public String getCode() {
			return code;
		}





		public void setCode(String code) {
			this.code = code;
		}







		@JsonIgnore
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


