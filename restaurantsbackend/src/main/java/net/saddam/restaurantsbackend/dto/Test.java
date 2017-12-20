package net.saddam.restaurantsbackend.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "testing")
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer test_ID;
	private String url;
	
    @Lob
	private byte[] image;
	
	
	public Test() {
	}
	
	public Test(byte[] image) {
		super();
		this.image = image;
	}

    
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getTest_ID() {
		return test_ID;
	}


	public void setTest_ID(Integer test_ID) {
		this.test_ID = test_ID;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}
	
	

}
