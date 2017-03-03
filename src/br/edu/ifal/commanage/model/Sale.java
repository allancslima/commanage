package br.edu.ifal.commanage.model;

public class Sale {
	
	private Product product; 
	private int quantity;
	
	public Sale (Product product, int quantity){
		setProduct(product);
		setQuantity(quantity);
	}
	
	public Product getProduct(){
		return product;
	}
	
	public void setProduct (Product product) {
		this.product = product;
	}
	
	public int getQuantity () {
		return quantity;
	}
 	
	public void setQuantity (int quantity) {
		this.quantity = quantity;
	}
}