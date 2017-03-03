package br.edu.ifal.commanage.view.controller;

import java.net.URL;
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
import java.util.List;
import br.edu.ifal.commanage.dao.EmployeeDAO;
import br.edu.ifal.commanage.model.Employee;

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
	@FXML
	private Button buttonCreate;
	@FXML
	private Button buttonUpdate;
	@FXML
	private Button buttonDelete;
	
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	private List<Employee> employees;
	private ObservableList<Employee> observableListEmployees;
	
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
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadTableViewEmployees();
		
		tableViewEmployees.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> selectItemTableViewEmployees(newValue));
	}
}