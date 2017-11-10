package net.saddam.restaurantsbackend.dto;

public class LoginUserResponse extends Response{

	   
	  // this is child class as a list
	
	
	  private  LoginUser loginUser;
	  
	  private ShopKeeper shopKeeper;
	  
	  



	public ShopKeeper getShopKeeper() {
		return shopKeeper;
	}

	public void setShopKeeper(ShopKeeper shopKeeper) {
		this.shopKeeper = shopKeeper;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	public LoginUserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	  
	  
}
