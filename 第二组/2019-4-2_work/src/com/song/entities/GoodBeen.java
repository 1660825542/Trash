package com.song.entities;

public class GoodBeen {
	private String number;
	private String name;
	private String price;
	private String picture;
	private int stock;
	private int attri;
	
	public int getAttri() {
		return attri;
	}
	
	public void setAttri(int attri) {
		this.attri = attri;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	public GoodBeen(String number, String name, String price, String picture, int stock, int attri) {
		super();
		this.number = number;
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.stock = stock;
		this.attri = attri;
	}

	public GoodBeen() {
		super();
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
