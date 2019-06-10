package com.song.entities;

public class Cart {
    private String id;
    private String name;
    private String phonenum;
    private int num;
    private String stock;
    private float price;
    private String picture;
    
   
	public Cart() {
		super();
	}




	public Cart(String id, String name, String phonenum, int num, String stock, float price, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.phonenum = phonenum;
		this.num = num;
		this.stock = stock;
		this.price = price;
		this.picture = picture;
	}




	public String getPicture() {
		return picture;
	}

	
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhonenum() {
		return phonenum;
	}
	
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
	
	
	
	public int getNum() {
		return num;
	}




	
	public void setNum(int num) {
		this.num = num;
	}




	public String getStock() {
		return stock;
	}
	
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
    
    
    
    
	
}
