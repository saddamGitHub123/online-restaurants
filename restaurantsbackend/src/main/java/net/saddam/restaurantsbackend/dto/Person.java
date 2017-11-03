package net.saddam.restaurantsbackend.dto;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {
	 
	  //private static final long serialVersionUID = 1L;
	  private int id;
	  private String name;
	  
	  List<Child> list;
	    
	  
	  
	  
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Child> getList() {
		return list;
	}
	public void setList(List<Child> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	 
	//getters and setters...
	  
	  
	 
	}
