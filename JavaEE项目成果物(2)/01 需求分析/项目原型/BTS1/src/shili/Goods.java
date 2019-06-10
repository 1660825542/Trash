package shili;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Goods implements Serializable{
	private Long id;
	private String name;
	private String goods_number;
	private double price;
	private String image;
	private Set shopping=new HashSet(0);
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getGoods_number() {
		return goods_number;
	}
	public void setGoods_number(String goods_number) {
		this.goods_number = goods_number;
	}
	public Set getShopping() {
		return shopping;
	}
	public void setShopping(Set shopping) {
		this.shopping = shopping;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

}
