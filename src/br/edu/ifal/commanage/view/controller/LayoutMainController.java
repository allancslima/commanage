package br.edu.ifal.commanage.view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class LayoutMainController implements Initializable {
	
	@FXML
	private MenuItem menuItemRegistrationEmployees;
	@FXML
	private MenuItem menuItemRegistrationProducts;
	@FXML
	private MenuItem menuItemRegistrationProviders;
	@FXML
	private MenuItem menuItemRegistrationClients;
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	public void handleMenuItemRegistrationEmployees () throws IOException {
		AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/edu/ifal/commanage/view/layout/LayoutEmployees.fxml"));
		anchorPane.getChildren().setAll(a);
	}
	
	@FXML
	public void handleMenuItemRegistrationProducts () throws IOException {
		AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/br/edu/ifal/commanage/view/layout/LayoutProducts.fxml"));
		anchorPane.getChildren().setAll(a);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
}