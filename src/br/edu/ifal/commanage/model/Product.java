package br.edu.ifal.commanage.model;

public class Product {
	
	private int id;
	private String name;
	private String category;
	private double purchasePrice;
	private double salePrice;

	public Product(String name, String category, double purchasePrice, double salePrice) {
		this.name = name;
		this.category = category;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public int getId() {
		return id;
	}
 	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;	
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public double getPurchasePrice() {
		return purchasePrice;
	}
	
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public double getSalePrice() {
		return salePrice;
	}
	
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
}