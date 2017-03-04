package br.edu.ifal.commanage.view.controller;

import br.edu.ifal.commanage.bo.CompanyBOPurchase;
import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.model.Purchase;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LayoutPurchasesDialogController {
	
	@FXML
	private ComboBox<Product> comboBoxProducts;
	@FXML
	private TextField textFieldQuantityPurchase;
	
	private Stage dialogStage;
	private Purchase purchase;
	private boolean buttonConfirmClicked = false;
	
	public Stage getDialogStage () {
		return dialogStage;
	}
	
	public void setDialogStage (Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setPurchase (Purchase purchase) {
		this.purchase = purchase;
		initializeTextFields();
	}
	
	private void initializeTextFields () {
		textFieldQuantityPurchase.setText(Integer.toString(purchase.getQuantity()));
	}
	
	public boolean isButtonConfirmClicked () {
		return buttonConfirmClicked;
	}
	
	@FXML
	public void handleButtonConfirm () {
		purchase.setProductId(1);
		purchase.setQuantity(Integer.parseInt(textFieldQuantityPurchase.getText()));
		
		CompanyBOPurchase companyBOPurchase = new CompanyBOPurchase(new Company());
		
		try {
			if (isNewPurchase(purchase)) {
				companyBOPurchase.validateAddPurchase(purchase);
			} else {
				companyBOPurchase.validateUpdatePurchase(purchase);
			}
			buttonConfirmClicked = true;
			dialogStage.close();
		} catch (Exception e) {
			System.out.println("Exceção: " + e.getMessage());
		}
	}
	
	@FXML
	public void handleButtonCancel () {
		dialogStage.close();
	}
	
	public boolean isNewPurchase (Purchase purchase) {
		if (purchase.getId() == 0)
			return true;
		return false;
	}
}