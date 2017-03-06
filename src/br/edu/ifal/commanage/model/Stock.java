package br.edu.ifal.commanage.model;

import java.util.Map;
import java.sql.SQLException;
import java.util.HashMap;
import br.edu.ifal.commanage.dao.StockDAO;

public class Stock {
	
	private Map<Integer, Integer> mapProducts = new HashMap<>();
	private StockDAO stockDAO = new StockDAO();
	
	private void loadMapProducts () throws SQLException {
		mapProducts = stockDAO.read();
	}
	
	public int getProductQuantity (int productId) {
		if (mapProducts.get(productId) == null)
			return 0;
		return mapProducts.get(productId);
	}
	
	public void updateStockForAddedProccess (Purchase purchase) throws SQLException {
		loadMapProducts();
		
		int productId = purchase.getProductId();
		int quantityToAdd = purchase.getQuantity();
		
		if (getProductQuantity(productId) == 0) {
			stockDAO.create(productId, quantityToAdd);
		}
		int productQuantity = getProductQuantity(productId);
		stockDAO.update(productId, productQuantity + quantityToAdd);
	}
	
	public void updateStockForRemovedProcess (Purchase purchase) throws SQLException {
		loadMapProducts();
		
		int productId = purchase.getProductId();
		int quantityToRemove = purchase.getQuantity();
		
		int productQuantity = getProductQuantity(productId);
		stockDAO.update(productId, productQuantity - quantityToRemove);
	}
	
	public void updateStockForAddedProccess (Sale sale) throws Exception, SQLException {
		loadMapProducts();
		
		int productId = sale.getProductId();
		int quantityToRemove = sale.getQuantity();
		
		if (quantityToRemove <= getProductQuantity(productId)) {
			int productQuantity = getProductQuantity(productId);
			stockDAO.update(productId, productQuantity - quantityToRemove);
		} else {
			throw new Exception("Insufficient stock");
		}
	}
	
	public void updateStockForRemovedProcess (Sale sale) throws SQLException {
		loadMapProducts();
		
		int productId = sale.getProductId();
		int quantityToAdd = sale.getQuantity();
		
		int productQuantity = getProductQuantity(productId);
		stockDAO.update(productId, productQuantity + quantityToAdd);
	}
}