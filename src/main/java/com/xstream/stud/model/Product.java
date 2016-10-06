package com.xstream.stud.model;

public class Product {

	private String name;
    private double price;
    private String description;
    private int skuCode;
    
    public Product(String name, double price, String description, int skuCode) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.skuCode = skuCode;
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(int skuCode) {
		this.skuCode = skuCode;
	}
}
