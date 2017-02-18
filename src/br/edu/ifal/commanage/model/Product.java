package br.edu.ifal.commanage.model;

public class Product {
	
	private String name;
	private double sellPrice;
	private double buyPrice;

	public Product (String name, double sellprice, double buyprice) {
		this.name = name;
		this.sellPrice = sellprice;
		this.buyPrice = buyprice;
	}
	
	public String getName () {
		return name;	
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public double getSellPrice () {
		return sellPrice;
	}
	
	public void setSellPrice (double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public double getBuyPrice () {
		return buyPrice;
	}
	
	public void setBuyPrice (double buyPrice) {
		this.buyPrice = buyPrice;
	}
}