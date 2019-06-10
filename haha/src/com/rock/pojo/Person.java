package com.rock.pojo;

import java.util.List;

public class Person {
	private String personid;
	private String personname;
	private int age;
	private String carid;
	private String brand;
	
//	1:1
	private Car car;
//	1:N
	private List<Car> cars;
	
	
	
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCarid() {
		return carid;
	}
	public void setCarid(String carid) {
		this.carid = carid;
	}
	
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public Person() {
		super();
	}
	public Person(String personid, String personname, int age, String carid, String brand) {
		super();
		this.personid = personid;
		this.personname = personname;
		this.age = age;
		this.carid = carid;
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "Person [personid=" + personid + ", personname=" + personname + ", age=" + age + ", carid=" + carid
				+ ", brand=" + brand + ", car=" + car + ", cars=" + cars + "]";
	}


	
	
}
