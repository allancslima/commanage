package br.edu.ifal.commanage.model;

import java.util.HashMap;

public class ProductStock {
	
	HashMap<Integer, Integer> mapProducts = new HashMap<>();
	
	public double updateStockForAddedPurchase (Product product, int productAmountToAdd) {
		int productId = 0;
		
		if (getProductAmount(productId) == 0) {
			mapProducts.put(productId, productAmountToAdd);
		}
		int productAmount = getProductAmount(productId);
		mapProducts.put(productId, productAmount + productAmountToAdd);
		
		return product.getPurchasePrice() * productAmountToAdd;
	}
	
	public double updateStockForRemovedPurchase (Product product, int productAmountToRemove) {
		int productId = 0;
		
		int productAmount = getProductAmount(productId);
		mapProducts.put(productId, productAmount - productAmountToRemove);
		
		return product.getPurchasePrice() * productAmountToRemove;
	}
	
	public int getProductAmount (int productId) {
		if (mapProducts.get(productId) == null)
			return 0;
		return mapProducts.get(productId);
	}
}