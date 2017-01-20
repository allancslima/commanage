package br.edu.ifal.commanage.model;

public class Product {
	
	private String Name;
	private String SellPrice;
	private String BuyPrice;
	
	public String getName() {
		return Name;	
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public String getSellPrice() {
		return SellPrice;
	}
	
	public void setPhone(String SellPrice) {
		this.SellPrice = SellPrice;
	}
	
	public String getBuyPrice() {
		return BuyPrice;
	}
	
	public void setEmail(String BuyPrice) {
		this.BuyPrice = BuyPrice;
	}

}
