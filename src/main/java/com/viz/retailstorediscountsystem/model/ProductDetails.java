package com.viz.retailstorediscountsystem.model;

import java.io.Serializable;

/**
 * This is ProductDetails class a model for products
 *
 */
public class ProductDetails implements Serializable{

	private static final long serialVersionUID = -7825166897330076052L;
	
	private int prdId;
	private String name;
	private boolean isGrocery;
	private double price;

	public int getPrdId() {
		return prdId;
	}

	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGrocery() {
		return isGrocery;
	}

	public void setGrocery(boolean isGrocery) {
		this.isGrocery = isGrocery;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
