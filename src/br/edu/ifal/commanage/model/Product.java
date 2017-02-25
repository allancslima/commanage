package br.edu.ifal.commanage.model;

public class Product {
	
	private String name;
	private double purchasePrice;
	private double salePrice;

	public Product (String name, double purchasePrice, double salePrice) {
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
	}
	
	public String getName () {
		return name;	
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public double getPurchasePrice () {
		return purchasePrice;
	}
	
	public void setPurchasePrice (double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public double getSalePrice () {
		return salePrice;
	}
	
	public void setSalePrice (double salePrice) {
		this.salePrice = salePrice;
	}
}