package br.edu.ifal.commanage.view.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifal.commanage.bo.CompanyBOSale;
import br.edu.ifal.commanage.dao.ProductDAO;
import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.model.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LayoutSaleDialogController implements Initializable {
	
	@FXML
	private ComboBox<Product> comboBoxProducts;
	@FXML
	private TextField textFieldQuantitySale;
	@FXML
	private Button buttonConfirm;
	
	private Stage dialogStage;
	private Sale sale;
	private List<Product> products;
	Product product;
	private ObservableList<Product> observableListProducts;
	private boolean buttonConfirmClicked = false;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			ProductDAO productDAO = new ProductDAO();
			products = productDAO.read();
			
			observableListProducts = FXCollections.observableArrayList(products);
			comboBoxProducts.setItems(observableListProducts);
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
		}
	}
	
	public Stage getDialogStage() {
		return dialogStage;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setSale(Sale sale) {
		this.sale = sale;
		initializeTextFields();
	}
	
	private void initializeTextFields() {
		textFieldQuantitySale.setText(Integer.toString(sale.getQuantity()));
	}
	
	public boolean isButtonConfirmClicked() {
		return buttonConfirmClicked;
	}
	
	@FXML
	public void selectItemComboBoxProducts() {
		product = comboBoxProducts.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	public void handleButtonConfirm() {
		if (product != null) {
			sale.setProductId(product.getId());
			sale.setQuantity(Integer.parseInt(textFieldQuantitySale.getText()));
			
			CompanyBOSale companyBOSale = new CompanyBOSale(new Company());
			
			try {
				companyBOSale.validateAddSale(sale);
				buttonConfirmClicked = true;
				dialogStage.close();
			} catch (Exception e) {
				System.out.println("Exceção: " + e.getMessage());
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Não há produto selecionado!");
			alert.show();
		}
	}
	
	@FXML
	public void handleButtonCancel() {
		dialogStage.close();
	}
}