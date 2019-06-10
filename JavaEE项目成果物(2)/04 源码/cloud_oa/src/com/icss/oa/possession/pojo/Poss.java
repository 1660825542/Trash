package com.icss.oa.possession.pojo;

public class Poss {
	
	private Integer possId;
	private String possName;
	private String possCate;
	private String possType;
	private float possPrice;
	private Integer possUse;
	private Integer possUnuse;
	
	
	public Poss(Integer possId, String possName, String possCate,
			String possType, float possPrice, Integer possUse, Integer possUnuse) {
		super();
		this.possId = possId;
		this.possName = possName;
		this.possCate = possCate;
		this.possType = possType;
		this.possPrice = possPrice;
		this.possUse = possUse;
		this.possUnuse = possUnuse;
	}
	
	public Poss(){
	}
	
	public Integer getPossId() {
		return possId;
	}
	public void setPossId(Integer possId) {
		this.possId = possId;
	}
	public String getPossName() {
		return possName;
	}
	public void setPossName(String possName) {
		this.possName = possName;
	}
	public String getPossCate() {
		return possCate;
	}
	public void setPossCate(String possCate) {
		this.possCate = possCate;
	}
	public String getPossType() {
		return possType;
	}
	public void setPossType(String possType) {
		this.possType = possType;
	}
	public float getPossPrice() {
		return possPrice;
	}
	public void setPossPrice(float possPrice) {
		this.possPrice = possPrice;
	}
	public Integer getPossUse() {
		return possUse;
	}
	public void setPossUse(Integer possUse) {
		this.possUse = possUse;
	}
	public Integer getPossUnuse() {
		return possUnuse;
	}
	public void setPossUnuse(Integer possUnuse) {
		this.possUnuse = possUnuse;
	}


	@Override
	public String toString() {
		return "Poss [possId=" + possId + ", possName=" + possName
				+ ", possCate=" + possCate + ", possType=" + possType
				+ ", possPrice=" + possPrice + ", possUse=" + possUse
				+ ", possUnuse=" + possUnuse + "]";
	}

}

