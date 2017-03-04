package br.edu.ifal.commanage.view.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import br.edu.ifal.commanage.dao.ProductDAO;
import br.edu.ifal.commanage.model.Product;
import java.io.IOException;
import java.sql.SQLException;

public class LayoutProductsController implements Initializable {
	
	@FXML
	private TableView<Product> tableViewProducts;
	@FXML
	private TableColumn<Product, String> tableColumnNameProduct;
	@FXML
	private TableColumn<Product, String> tableColumnCategoryProduct;
	@FXML
	private Label labelProductID;
	@FXML
	private Label labelNameProduct;
	@FXML
	private Label labelCategoryProduct;
	@FXML
	private Label labelPurchasePriceProduct;
	@FXML
	private Label labelSalePriceProduct;
	
	private ProductDAO productDAO = new ProductDAO();
	private List<Product> products;
	private ObservableList<Product> observableListProducts;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadTableViewProducts();
		
		tableViewProducts.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectItemTableViewProducts(newValue));
	}
	
	public void loadTableViewProducts () {
		tableColumnNameProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnCategoryProduct.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		try {
			products = productDAO.read();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		observableListProducts = FXCollections.observableArrayList(products);
		tableViewProducts.setItems(observableListProducts);
	}
	
	public void selectItemTableViewProducts (Product product) {
		if (product != null) {
			labelProductID.setText(String.valueOf(product.getId()));
			labelNameProduct.setText(product.getName());
			//labelCategoryProduct.setText(product.getCategory());
			labelPurchasePriceProduct.setText("R$ " + String.valueOf(product.getPurchasePrice()));
			labelSalePriceProduct.setText("R$ " + String.valueOf(product.getSalePrice()));
		} else {
			labelProductID.setText("");
			labelNameProduct.setText("");
			labelCategoryProduct.setText("");
			labelPurchasePriceProduct.setText("");
			labelSalePriceProduct.setText("");
		}
	}
	
	public boolean showLayoutProductsDialog (Product product) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(LayoutProductsDialogController.class.getResource("/br/edu/ifal/commanage/view/layout/LayoutProductsDialog.fxml"));
		
		AnchorPane page = loader.load();
		Scene scene = new Scene(page);
		
		Stage dialogStage = new Stage();
		dialogStage.setScene(scene);
		
		LayoutProductsDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setProduct(product);
		
		controller.getDialogStage().showAndWait();
		
		return controller.isButtonConfirmClicked();
	}
	
	@FXML
	public void handleButtonCreate () throws IOException {
		Product product = new Product("", 0, 0);
		boolean isButtonConfirmClicked = showLayoutProductsDialog(product);
		
		if (isButtonConfirmClicked) loadTableViewProducts();
	}
	
	@FXML
	public void handleButtonUpdate () throws IOException {
		Product product = tableViewProducts.getSelectionModel().getSelectedItem();
		
		if (isNullProduct(product)) {
			alert();
		} else {
			boolean isButtonConfirmClicked = showLayoutProductsDialog(product);
			if (isButtonConfirmClicked) loadTableViewProducts();
		}
	}
	
	@FXML
	public void handleButtonDelete () throws SQLException {
		Product product = tableViewProducts.getSelectionModel().getSelectedItem();
		
		if (isNullProduct(product)) {
			alert();
		} else {
			ProductDAO productDAO = new ProductDAO();
			productDAO.delete(product.getId());
			loadTableViewProducts();
		}
	}
	
	public boolean isNullProduct (Product product) {
		if (product == null)
			return true;
		return false;
	}
	
	public void alert () {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText("Não há produto selecionado!");
		alert.show();
	}
}