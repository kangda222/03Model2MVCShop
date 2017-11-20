package com.model2.mvc.service.domain;

import java.sql.Date;

public class Cart {
	
	private String userId;
	private int prodNo;
	private String prodName;
	private int price;
	private Date regDate;
	
	public Cart() {		
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Cart [userId=" + userId + ", prodNo=" + prodNo + ", prodName=" + prodName + ", price=" + price
				+ ", regDate=" + regDate + "]";
	}

}
