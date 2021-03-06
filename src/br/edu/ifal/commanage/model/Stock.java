package br.edu.ifal.commanage.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.edu.ifal.commanage.dao.StockDAO;
import br.edu.ifal.commanage.util.exception.StockException;

public class Stock {
	
	private Map<Integer, Integer> mapProducts = new HashMap<>();
	private StockDAO stockDAO = new StockDAO();
	
	private void loadMapProducts() throws SQLException {
		mapProducts = stockDAO.read();
	}
	
	public boolean isOutOfStock(int productId) {
		return (mapProducts.get(productId) == null) ? true : false;
	}
	
	public int getProductQuantity(int productId) {
		return isOutOfStock(productId) ? 0 : mapProducts.get(productId);
	}
	
	public void updateStockForAddedProccess(Purchase purchase) throws SQLException {
		loadMapProducts();
		
		int productId = purchase.getProductId();
		int quantityToAdd = purchase.getQuantity();
		
		if (isOutOfStock(purchase.getProductId())) {
			stockDAO.create(productId, quantityToAdd);
		}
		int productQuantity = getProductQuantity(productId);
		stockDAO.update(productId, productQuantity + quantityToAdd);
	}
	
	public void updateStockForRemovedProcess(Purchase purchase) throws SQLException {
		loadMapProducts();
		
		int productId = purchase.getProductId();
		int quantityToRemove = purchase.getQuantity();
		
		int productQuantity = getProductQuantity(productId);
		stockDAO.update(productId, productQuantity - quantityToRemove);
	}
	
	public void updateStockForAddedProccess(Sale sale) throws StockException, SQLException {
		loadMapProducts();
		
		int productId = sale.getProductId();
		int quantityToRemove = sale.getQuantity();
		
		if (quantityToRemove <= getProductQuantity(productId)) {
			int productQuantity = getProductQuantity(productId);
			stockDAO.update(productId, productQuantity - quantityToRemove);
		} else {
			throw new StockException("Insufficient stock");
		}
	}
	
	public void updateStockForRemovedProcess(Sale sale) throws SQLException {
		loadMapProducts();
		
		int productId = sale.getProductId();
		int quantityToAdd = sale.getQuantity();
		
		int productQuantity = getProductQuantity(productId);
		stockDAO.update(productId, productQuantity + quantityToAdd);
	}
	
	public void updateStockForRemovedProduct(int productId) throws SQLException {
		stockDAO.delete(productId);
	}
}