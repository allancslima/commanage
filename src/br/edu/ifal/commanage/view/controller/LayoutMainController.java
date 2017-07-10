package br.edu.ifal.commanage.view.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class LayoutMainController {
	
	@FXML
	private MenuItem menuItemRegistrationEmployees;
	@FXML
	private MenuItem menuItemRegistrationProducts;
	@FXML
	private MenuItem menuItemRegistrationProviders;
	@FXML
	private MenuItem menuItemRegistrationClients;
	@FXML
	private MenuItem menuItemRegistrationPurchases;
	@FXML
	private MenuItem menuItemRegistrationSales;
	@FXML
	private MenuItem menuItemStock;
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	public void handleMenuItemRegistrationEmployees() throws IOException {
		AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/edu/ifal/commanage/view/layout/LayoutEmployee.fxml"));
		anchorPane.getChildren().setAll(a);
	}
	
	@FXML
	public void handleMenuItemRegistrationProducts() throws IOException {
		AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/edu/ifal/commanage/view/layout/LayoutProduct.fxml"));
		anchorPane.getChildren().setAll(a);
	}
	
	@FXML
	public void handleMenuItemRegistrationPurchases() throws IOException {
		AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/edu/ifal/commanage/view/layout/LayoutPurchase.fxml"));
		anchorPane.getChildren().setAll(a);
	}
	
	@FXML
	public void handleMenuItemRegistrationSales() throws IOException {
		AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/edu/ifal/commanage/view/layout/LayoutSale.fxml"));
		anchorPane.getChildren().setAll(a);
	}
	
	@FXML
	public void handleMenuItemStock() throws IOException {
		AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/edu/ifal/commanage/view/layout/LayoutStock.fxml"));
		anchorPane.getChildren().setAll(a);
	}
}