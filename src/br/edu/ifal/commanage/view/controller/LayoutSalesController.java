package br.edu.ifal.commanage.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.ifal.commanage.dao.ProductDAO;
import br.edu.ifal.commanage.dao.SaleDAO;
import br.edu.ifal.commanage.model.Sale;
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

public class LayoutSalesController implements Initializable {
	
	@FXML
	private TableView<Sale> tableViewSales;
	@FXML
	private TableColumn<Sale, String> tableColumnNameProductSale;
	@FXML
	private TableColumn<Sale, String> tableColumnQuantitySale;
	@FXML
	private Label labelSaleID;
	@FXML
	private Label labelNameProduct;
	@FXML
	private Label labelQuantitySale;
	
	private SaleDAO saleDAO = new SaleDAO();
	private List<Sale> sales;
	private ObservableList<Sale> observableListSales;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadTableViewSales();
		
		tableViewSales.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectItemTableViewSales(newValue));
	}
	
	public void loadTableViewSales () {
		tableColumnNameProductSale.setCellValueFactory(new PropertyValueFactory<>("productId"));
		tableColumnQuantitySale.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		try {
			sales = saleDAO.read();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		observableListSales = FXCollections.observableArrayList(sales);
		tableViewSales.setItems(observableListSales);
	}
	
	public void selectItemTableViewSales (Sale sale) {
		if (sale != null) {
			labelSaleID.setText(String.valueOf(sale.getId()));
			
			try {
				ProductDAO productDAO = new ProductDAO();
				String nameProduct = productDAO.findById(sale.getProductId()).getName();
				labelNameProduct.setText(nameProduct);
			} catch (Exception e) {
				System.out.println("Exceção: " + e.getMessage());
			}
			
			labelQuantitySale.setText(String.valueOf(sale.getQuantity()));
		} else {
			labelSaleID.setText("");
			labelNameProduct.setText("");
			labelQuantitySale.setText("");
		}
	}
	
	public boolean showLayoutSalesDialog (Sale sale) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(LayoutSalesDialogController.class.getResource("/br/edu/ifal/commanage/view/layout/LayoutSalesDialog.fxml"));
		
		AnchorPane page = loader.load();
		Scene scene = new Scene(page);
		
		Stage dialogStage = new Stage();
		dialogStage.setScene(scene);
		
		LayoutSalesDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setSale(sale);
		
		controller.getDialogStage().showAndWait();
		
		return controller.isButtonConfirmClicked();
	}
	
	@FXML
	public void handleButtonCreate () throws IOException {
		Sale sale = new Sale(0, 0);
		boolean isButtonConfirmClicked = showLayoutSalesDialog(sale);
		
		if (isButtonConfirmClicked) loadTableViewSales();
	}
	
	@FXML
	public void handleButtonDelete () throws SQLException {
		Sale sale = tableViewSales.getSelectionModel().getSelectedItem();
		
		if (isNullSale(sale)) {
			alert();
		} else {
			SaleDAO saleDAO = new SaleDAO();
			saleDAO.delete(sale.getId());
			loadTableViewSales();
		}
	}
	
	public boolean isNullSale (Sale sale) {
		if (sale == null)
			return true;
		return false;
	}
	
	public void alert () {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText("Não há venda selecionado!");
		alert.show();
	}
}