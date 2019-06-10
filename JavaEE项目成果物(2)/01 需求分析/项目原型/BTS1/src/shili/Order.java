package shili;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Order implements Serializable{
	private Long id;
	private String orderNumber;
	private User user;//Íâ¼ü
	private Set Order_Goods =new HashSet();
	
	

	public Set getOrder_Goods() {
		return Order_Goods;
	}
	public void setOrder_Goods(Set order_Goods) {
		Order_Goods = order_Goods;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
