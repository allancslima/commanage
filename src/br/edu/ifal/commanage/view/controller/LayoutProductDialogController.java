package br.edu.ifal.commanage.view.controller;

import br.edu.ifal.commanage.bo.CompanyBOProduct;
import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LayoutProductDialogController {
	
	@FXML
	private TextField textFieldNameProduct;
	@FXML
	private TextField textFieldCategoryProduct;
	@FXML
	private TextField textFieldPurchasePriceProduct;
	@FXML
	private TextField textFieldSalePriceProduct;
	
	private Stage dialogStage;
	private Product product;
	private boolean buttonConfirmClicked = false;
	
	public Stage getDialogStage() {
		return dialogStage;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setProduct(Product product) {
		this.product = product;
		initializeTextFields();
	}
	
	public void initializeTextFields() {
		textFieldNameProduct.setText(product.getName());
		textFieldCategoryProduct.setText(product.getCategory());
		textFieldPurchasePriceProduct.setText(String.valueOf(product.getPurchasePrice()));
		textFieldSalePriceProduct.setText(String.valueOf(product.getSalePrice()));
	}
	
	public boolean isButtonConfirmClicked() {
		return buttonConfirmClicked;
	}
	
	@FXML
	public void handleButtonConfirm() {
		product.setName(textFieldNameProduct.getText());
		product.setCategory(textFieldCategoryProduct.getText());
		product.setPurchasePrice(Double.parseDouble(textFieldPurchasePriceProduct.getText()));
		product.setSalePrice(Double.parseDouble(textFieldSalePriceProduct.getText()));
		
		CompanyBOProduct companyBOProduct = new CompanyBOProduct(new Company());
		
		try {
			if (isNewProduct(product)) {
				companyBOProduct.validateAddProduct(product);
			} else {
				companyBOProduct.validateUpdateProduct(product);
			}
			buttonConfirmClicked = true;
			dialogStage.close();
		} catch (Exception e) {
			System.out.println("Exceção: " + e.getMessage());
		}
	}
	
	public boolean isNewProduct(Product product) {
		return (product.getId() == 0) ? true : false;
	}
	
	@FXML
	public void handleButtonCancel() {
		dialogStage.close();
	}
}