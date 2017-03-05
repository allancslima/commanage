package br.edu.ifal.commanage.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifal.commanage.dao.ProductDAO;
import br.edu.ifal.commanage.dao.PurchaseDAO;
import br.edu.ifal.commanage.model.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LayoutPurchasesController implements Initializable {
	
	@FXML
	private TableView<Purchase> tableViewPurchases;
	@FXML
	private TableColumn<Purchase, String> tableColumnNameProductPurchase;
	@FXML
	private TableColumn<Purchase, String> tableColumnQuantityPurchase;
	@FXML
	private Label labelPurchaseID;
	@FXML
	private Label labelNameProduct;
	@FXML
	private Label labelQuantityPurchase;
	
	private PurchaseDAO purchaseDAO = new PurchaseDAO();
	private List<Purchase> purchases;
	private ObservableList<Purchase> observableListPurchases;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadTableViewPurchases();
		
		tableViewPurchases.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectItemTableViewPurchases(newValue));
	}
	
	public void loadTableViewPurchases () {
		tableColumnNameProductPurchase.setCellValueFactory(new PropertyValueFactory<>("productId"));
		tableColumnQuantityPurchase.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		try {
			purchases = purchaseDAO.read();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		observableListPurchases = FXCollections.observableArrayList(purchases);
		tableViewPurchases.setItems(observableListPurchases);
	}
	
	public void selectItemTableViewPurchases (Purchase purchase) {
		if (purchase != null) {
			labelPurchaseID.setText(String.valueOf(purchase.getId()));
			
			try {
				ProductDAO productDAO = new ProductDAO();
				String nameProduct = productDAO.findById(purchase.getProductId()).getName();
				labelNameProduct.setText(nameProduct);
			} catch (Exception e) {
				System.out.println("Exceção: " + e.getMessage());
			}
			
			labelQuantityPurchase.setText(String.valueOf(purchase.getQuantity()));
		} else {
			labelPurchaseID.setText("");
			labelNameProduct.setText("");
			labelQuantityPurchase.setText("");
		}
	}
	
	public boolean showLayoutPurchasesDialog (Purchase purchase) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(LayoutEmployeesDialogController.class.getResource("/br/edu/ifal/commanage/view/layout/LayoutPurchasesDialog.fxml"));
		
		AnchorPane page = loader.load();
		Scene scene = new Scene(page);
		
		Stage dialogStage = new Stage();
		dialogStage.setScene(scene);
		
		LayoutPurchasesDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setPurchase(purchase);
		
		controller.getDialogStage().showAndWait();
		
		return controller.isButtonConfirmClicked();
	}
	
	@FXML
	public void handleButtonCreate () throws IOException {
		Purchase purchase = new Purchase(0, 0);
		boolean isButtonConfirmClicked = showLayoutPurchasesDialog(purchase);
		
		if (isButtonConfirmClicked) loadTableViewPurchases();
	}
	
	@FXML
	public void handleButtonDelete () throws SQLException {
		Purchase purchase = tableViewPurchases.getSelectionModel().getSelectedItem();
		
		if (isNullPurchase(purchase)) {
			alert();
		} else {
			PurchaseDAO purchaseDAO = new PurchaseDAO();
			purchaseDAO.delete(purchase.getId());
			loadTableViewPurchases();
		}
	}
	
	public boolean isNullPurchase (Purchase purchase) {
		if (purchase == null)
			return true;
		return false;
	}
	
	public void alert () {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText("Não há compra selecionado!");
		alert.show();
	}
}