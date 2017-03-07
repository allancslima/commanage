package br.edu.ifal.commanage.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifal.commanage.bo.CompanyBOEmployee;
import br.edu.ifal.commanage.dao.EmployeeDAO;
import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Employee;
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

public class LayoutEmployeeController implements Initializable {
	
	@FXML
	private TableView<Employee> tableViewEmployee;
	@FXML
	private TableColumn<Employee, String> tableColumnNameEmployee;
	@FXML
	private TableColumn<Employee, String> tableColumnFunctionEmployee;
	@FXML
	private Label labelEmployeeID;
	@FXML
	private Label labelNameEmployee;
	@FXML
	private Label labelFunctionEmployee;
	@FXML
	private Label labelPhoneEmployee;
	@FXML
	private Label labelEmailEmployee;
	
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	private List<Employee> employees;
	private ObservableList<Employee> observableListEmployee;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadTableViewEmployee();
		
		tableViewEmployee.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectItemTableViewEmployee(newValue));
	}
	
	public void loadTableViewEmployee ()  {
		tableColumnNameEmployee.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnFunctionEmployee.setCellValueFactory(new PropertyValueFactory<>("function"));
		
		try {
			employees = employeeDAO.read();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		observableListEmployee = FXCollections.observableArrayList(employees);
		tableViewEmployee.setItems(observableListEmployee);
	}

	public void selectItemTableViewEmployee (Employee employee) {
		if (employee != null) {
			labelEmployeeID.setText(String.valueOf(employee.getId()));
			labelNameEmployee.setText(employee.getName());
			labelFunctionEmployee.setText(employee.getFuncion());
			labelPhoneEmployee.setText(employee.getPhone());
			labelEmailEmployee.setText(employee.getEmail());
		} else {
			labelEmployeeID.setText("");
			labelNameEmployee.setText("");
			labelFunctionEmployee.setText("");
			labelPhoneEmployee.setText("");
			labelEmailEmployee.setText("");
		}
	}
	
	public boolean showLayoutEmployeeDialog (Employee employee) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(LayoutEmployeeDialogController.class.getResource("/br/edu/ifal/commanage/view/layout/LayoutEmployeeDialog.fxml"));
		
		AnchorPane page = loader.load();
		Scene scene = new Scene(page);
		
		Stage dialogStage = new Stage();
		dialogStage.setScene(scene);
		
		LayoutEmployeeDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setEmployee(employee);
		
		controller.getDialogStage().showAndWait();
		
		return controller.isButtonConfirmClicked();
	}
	
	@FXML
	public void handleButtonCreate () throws IOException {
		Employee employee = null;
		boolean isButtonConfirmClicked = showLayoutEmployeeDialog(employee);
		
		if (isButtonConfirmClicked) loadTableViewEmployee();
	}
	
	@FXML
	public void handleButtonUpdate () throws IOException {
		Employee employee = tableViewEmployee.getSelectionModel().getSelectedItem();
		
		if (isNullEmployee(employee)) {
			alert();
		} else {
			boolean isButtonConfirmClicked = showLayoutEmployeeDialog(employee);
			if (isButtonConfirmClicked) loadTableViewEmployee();
		}
	}
	
	@FXML
	public void handleButtonDelete () throws SQLException {
		Employee employee = tableViewEmployee.getSelectionModel().getSelectedItem();
		
		if (isNullEmployee(employee)) {
			alert();
		} else {
			CompanyBOEmployee companyBOEmployee = new CompanyBOEmployee(new Company());
			companyBOEmployee.removeEmployee(employee);
			loadTableViewEmployee();
		}
	}
	
	public boolean isNullEmployee (Employee employee) {
		if (employee == null)
			return true;
		return false;
	}
	
	public void alert () {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText("Não há funcionário selecionado!");
		alert.show();
	}
}