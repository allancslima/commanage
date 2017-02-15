package br.edu.ifal.commanage.model;

public class Product {
	
	private String name;
	private double sellprice;
	private double buyprice;

	public Product(String name, double sellprice, double buyprice) {
		this.name = name;
		this.sellprice = sellprice;
		this.buyprice = buyprice;
	}
	
	public String getName() {
		return name;	
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getSellprice() {
		return sellprice;
	}
	
	public void setSellprice(double sellprice) {
		this.sellprice = sellprice;
	}
	
	public double getBuyprice() {
		return buyprice;
	}
	
	public void setBuyprice(double buyprice) {
		this.buyprice = buyprice;
	}
}