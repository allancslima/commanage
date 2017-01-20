package br.edu.ifal.commanage.model;

public class Product {
	
	private String name;
	private String sellprice;
	private String buyprice;
	
	public String getName() {
		return name;	
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSellprice() {
		return sellprice;
	}
	
	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	
	public String getBuyprice() {
		return buyprice;
	}
	
	public void setBuyprice(String buyprice) {
		this.buyprice = buyprice;
	}

}
