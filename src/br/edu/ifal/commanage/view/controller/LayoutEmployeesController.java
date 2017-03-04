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
import br.edu.ifal.commanage.dao.EmployeeDAO;
import br.edu.ifal.commanage.model.Employee;
import java.io.IOException;
import java.sql.SQLException;

public class LayoutEmployeesController implements Initializable {
	
	@FXML
	private TableView<Employee> tableViewEmployees;
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
	private ObservableList<Employee> observableListEmployees;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadTableViewEmployees();
		
		tableViewEmployees.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectItemTableViewEmployees(newValue));
	}
	
	public void loadTableViewEmployees ()  {
		tableColumnNameEmployee.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnFunctionEmployee.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		try {
			employees = employeeDAO.read();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		observableListEmployees = FXCollections.observableArrayList(employees);
		tableViewEmployees.setItems(observableListEmployees);
	}

	public void selectItemTableViewEmployees (Employee employee) {
		if (employee != null) {
			labelEmployeeID.setText(String.valueOf(employee.getId()));
			labelNameEmployee.setText(employee.getName());
			//labelFunctionEmployee.setText(employee.getFunction());
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
	
	public boolean showLayoutEmployeesDialog (Employee employee) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(LayoutEmployeesDialogController.class.getResource("/br/edu/ifal/commanage/view/layout/LayoutEmployeesDialog.fxml"));
		
		AnchorPane page = loader.load();
		Scene scene = new Scene(page);
		
		Stage dialogStage = new Stage();
		dialogStage.setScene(scene);
		
		LayoutEmployeesDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setEmployee(employee);
		
		controller.getDialogStage().showAndWait();
		
		return controller.isButtonConfirmClicked();
	}
	
	@FXML
	public void handleButtonCreate () throws IOException {
		Employee employee = new Employee("", "", "");
		boolean isButtonConfirmClicked = showLayoutEmployeesDialog(employee);
		
		if (isButtonConfirmClicked) loadTableViewEmployees();
	}
	
	@FXML
	public void handleButtonUpdate () throws IOException {
		Employee employee = tableViewEmployees.getSelectionModel().getSelectedItem();
		
		if (isNullEmployee(employee)) {
			alert();
		} else {
			boolean isButtonConfirmClicked = showLayoutEmployeesDialog(employee);
			if (isButtonConfirmClicked) loadTableViewEmployees();
		}
	}
	
	@FXML
	public void handleButtonDelete () throws SQLException {
		Employee employee = tableViewEmployees.getSelectionModel().getSelectedItem();
		
		if (isNullEmployee(employee)) {
			alert();
		} else {
			EmployeeDAO employeDAO = new EmployeeDAO();
			employeDAO.delete(employee.getId());
			loadTableViewEmployees();
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