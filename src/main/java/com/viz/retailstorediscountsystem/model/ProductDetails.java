package com.viz.retailstorediscountsystem.model;

/**
 * This is ProductDetails class a model for products
 *
 */
public class ProductDetails {

	private int prdId;
	private String name;
	private boolean isGrocery;
	private double price;

	public ProductDetails() {

	}

	/**
	 * ProductDetails class constructor
	 * 
	 * @param prdId     as int
	 * @param name      as String
	 * @param isGrocery as boolean
	 * @param price     as int
	 */
	public ProductDetails(int prdId, String name, boolean isGrocery, int price) {
		this.prdId = prdId;
		this.name = name;
		this.isGrocery = isGrocery;
		this.price = price;
	}

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
