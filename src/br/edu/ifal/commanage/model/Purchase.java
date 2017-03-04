package br.edu.ifal.commanage.model;

public class Purchase {
	
	private int id;
	private int productId; 
	private int quantity;
	
	public Purchase (int productId, int quantity){
		setProductId(productId);
		setQuantity(quantity);
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public int getProductId(){
		return productId;
	}
	
	public void setProductId (int productId) {
		this.productId = productId;
	}
	
	public int getQuantity () {
		return quantity;
	}
 	
	public void setQuantity (int quantity) {
		this.quantity = quantity;
	}
}