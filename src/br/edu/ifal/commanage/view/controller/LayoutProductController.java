package br.edu.ifal.commanage.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifal.commanage.bo.CompanyBOProduct;
import br.edu.ifal.commanage.dao.ProductDAO;
import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
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

public class LayoutProductController implements Initializable {
	
	@FXML
	private TableView<Product> tableViewProduct;
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
	private ObservableList<Product> observableListProduct;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadTableViewProduct();
		
		tableViewProduct.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectItemTableViewProduct(newValue));
	}
	
	public void loadTableViewProduct() {
		tableColumnNameProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnCategoryProduct.setCellValueFactory(new PropertyValueFactory<>("category"));
		
		try {
			products = productDAO.read();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		observableListProduct = FXCollections.observableArrayList(products);
		tableViewProduct.setItems(observableListProduct);
	}
	
	public void selectItemTableViewProduct(Product product) {
		if (product != null) {
			labelProductID.setText(String.valueOf(product.getId()));
			labelNameProduct.setText(product.getName());
			labelCategoryProduct.setText(product.getCategory());
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
	
	public boolean showLayoutProductDialog(Product product) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(LayoutProductDialogController.class.getResource("/br/edu/ifal/commanage/view/layout/LayoutProductDialog.fxml"));
		
		AnchorPane page = loader.load();
		Scene scene = new Scene(page);
		
		Stage dialogStage = new Stage();
		dialogStage.setScene(scene);
		
		LayoutProductDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setProduct(product);
		
		controller.getDialogStage().showAndWait();
		
		return controller.isButtonConfirmClicked();
	}
	
	@FXML
	public void handleButtonCreate() throws IOException {
		Product product = new Product("", "", 0, 0);
		boolean isButtonConfirmClicked = showLayoutProductDialog(product);
		
		if (isButtonConfirmClicked) loadTableViewProduct();
	}
	
	@FXML
	public void handleButtonUpdate() throws IOException {
		Product product = tableViewProduct.getSelectionModel().getSelectedItem();
		
		if (isNullProduct(product)) {
			alert();
		} else {
			boolean isButtonConfirmClicked = showLayoutProductDialog(product);
			if (isButtonConfirmClicked) loadTableViewProduct();
		}
	}
	
	@FXML
	public void handleButtonDelete() throws SQLException {
		Product product = tableViewProduct.getSelectionModel().getSelectedItem();
		
		if (isNullProduct(product)) {
			alert();
		} else {
			CompanyBOProduct companyBOProduct = new CompanyBOProduct(new Company());
			companyBOProduct.removeProduct(product);
			loadTableViewProduct();
		}
	}
	
	public boolean isNullProduct(Product product) {
		return (product == null) ? true : false;
	}
	
	public void alert() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText("Não há produto selecionado!");
		alert.show();
	}
}