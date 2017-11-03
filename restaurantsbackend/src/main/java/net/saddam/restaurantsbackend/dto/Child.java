package net.saddam.restaurantsbackend.dto;

import java.io.Serializable;

public class Child implements Serializable {
	
	private String sub;
    private String amount;
    
    
    
    
	@Override
	public String toString() {
		return "Child [sub=" + sub + ", amount=" + amount + "]";
	}
	
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
    
    
    

}
