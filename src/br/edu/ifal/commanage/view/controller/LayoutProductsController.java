package br.edu.ifal.commanage.view.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import br.edu.ifal.commanage.dao.ProductDAO;
import br.edu.ifal.commanage.model.Product;

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
	@FXML
	private Button buttonCreate;
	@FXML
	private Button buttonUpdate;
	@FXML
	private Button buttonDelete;
	
	private ProductDAO productDAO = new ProductDAO();
	private List<Product> products;
	private ObservableList<Product> observableListProducts;
	
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
			labelPurchasePriceProduct.setText(String.valueOf(product.getPurchasePrice()));
			labelSalePriceProduct.setText(String.valueOf(product.getSalePrice()));
		} else {
			labelProductID.setText("");
			labelNameProduct.setText("");
			labelCategoryProduct.setText("");
			labelPurchasePriceProduct.setText("");
			labelSalePriceProduct.setText("");
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadTableViewProducts();
		
		tableViewProducts.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectItemTableViewProducts(newValue));
	}
}