package br.edu.ifal.commanage.view.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifal.commanage.bo.CompanyBOPurchase;
import br.edu.ifal.commanage.dao.ProductDAO;
import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.model.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LayoutPurchaseDialogController implements Initializable {
	
	@FXML
	private ComboBox<Product> comboBoxProducts;
	@FXML
	private TextField textFieldQuantityPurchase;
	@FXML
	private Button buttonConfirm;
	
	private Stage dialogStage;
	private Purchase purchase;
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
	
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
		initializeTextFields();
	}
	
	private void initializeTextFields() {
		textFieldQuantityPurchase.setText(Integer.toString(purchase.getQuantity()));
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
			purchase.setProductId(product.getId());
			purchase.setQuantity(Integer.parseInt(textFieldQuantityPurchase.getText()));
			
			CompanyBOPurchase companyBOPurchase = new CompanyBOPurchase(new Company());
			
			try {
				companyBOPurchase.validateAddPurchase(purchase);
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