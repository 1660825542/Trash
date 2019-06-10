package com.icss.oa.car.pojo;

import java.util.Arrays;

public class Car {

    private Integer carId;

    private String carType;

    private String license;

    private byte[] picture;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

	public Car() {
		super();
	}

	public Car(Integer carId, String carType, String license, byte[] picture) {
		super();
		this.carId = carId;
		this.carType = carType;
		this.license = license;
		this.picture = picture;
	}

	public Car(String carType, String license, byte[] picture) {
		super();
		this.carType = carType;
		this.license = license;
		this.picture = picture;
	}

	public Car(Integer carId, String carType, String license) {
		super();
		this.carId = carId;
		this.carType = carType;
		this.license = license;
	}

	public Car(String carType, String license) {
		super();
		this.carType = carType;
		this.license = license;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carType=" + carType + ", license="
				+ license + ", picture=" + Arrays.toString(picture) + "]";
	}  
    
}