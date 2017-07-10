package br.edu.ifal.commanage.view.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import br.edu.ifal.commanage.dao.StockDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LayoutStockController implements Initializable {
	
	@FXML
	private TableView<StockItem> tableViewStock;
	@FXML
	private TableColumn<StockItem, Integer> tableColumnProductName;
	@FXML
	private TableColumn<StockItem, Integer> tableColumnProductQuantity;
	
	private StockDAO stockDAO = new StockDAO();
	private Map<Integer, Integer> mapProducts = new HashMap<>();
	private List<StockItem> stockItems;
	private ObservableList<StockItem> observableListStockItems;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadTableViewStock();
	}
	
	public void loadTableViewStock() {
		tableColumnProductName.setCellValueFactory(new PropertyValueFactory<>("productId"));
		tableColumnProductQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		try {
			stockItems = new ArrayList<>();
			mapProducts = stockDAO.read();
			
			for (Map.Entry<Integer, Integer> item: mapProducts.entrySet()) {
				StockItem stockItem = new StockItem(item.getKey(), item.getValue());
				stockItems.add(stockItem);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		observableListStockItems = FXCollections.observableArrayList(stockItems);
		tableViewStock.setItems(observableListStockItems);
	}
	
	public class StockItem {
		private int productId;
		private int quantity;
		
		StockItem(int productId, int quantity) {
			this.productId = productId;
			this.quantity = quantity;
		}
		
		public int getProductId() {
			return productId;
		}
		
		public int getQuantity() {
			return quantity;
		}
	}
}