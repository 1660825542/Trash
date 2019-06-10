package com.rock.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Car {
	private String carid;
	private String brand;
	private String price;
	private int maxspeed;
	private Date smarkdate;
	
	public Car() {
		super();
	}
	public Car(String carid, String brand, String price, int maxspeed, Date smarkdate) {
		super();
		this.carid = carid;
		this.brand = brand;
		this.price = price;
		this.maxspeed = maxspeed;
		this.smarkdate = smarkdate;
	}
	
	
	public String getCarid() {
		return carid;
	}
	public void setCarid(String carid) {
		this.carid = carid;
	}
	public Date getSmarkdate() {
		return smarkdate;
	}
	public void setSmarkdate(Date smarkdate) {
		this.smarkdate = smarkdate;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getMaxspeed() {
		return maxspeed;
	}
	public void setMaxspeed(int maxspeed) {
		this.maxspeed = maxspeed;
	}
	@Override
	public String toString() {
		return "Car [carid=" + carid + ", brand=" + brand + ", price=" + price + ", maxspeed=" + maxspeed
				+ ", smarkdate=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(smarkdate) + "]";
	}
	
	
}
