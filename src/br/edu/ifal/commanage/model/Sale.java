package br.edu.ifal.commanage.model;

public class Sale {
	
	private int id;
	private int productId; 
	private int quantity;
	
	public Sale(int productId, int quantity){
		setProductId(productId);
		setQuantity(quantity);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProductId(){
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
 	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}