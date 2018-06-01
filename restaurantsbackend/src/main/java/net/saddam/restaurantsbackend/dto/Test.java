package net.saddam.restaurantsbackend.dto;

import javax.persistence.Column;
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
	@Column(name="url")
	private String Id;
	
    @Lob
	private byte[] image;
	
	
	public Test() {
	}
	
	public Test(byte[] image) {
		super();
		this.image = image;
	}

    


	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
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
