package br.edu.ifal.commanage.view.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifal.commanage.bo.CompanyBOEmployee;
import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Employee;
import br.edu.ifal.commanage.model.Manager;
import br.edu.ifal.commanage.model.Salesperson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LayoutEmployeeDialogController implements Initializable {
	
	@FXML
	private TextField textFieldNameEmployee;
	@FXML
	private ComboBox<String> comboBoxEmployeeFunctions;
	@FXML
	private TextField textFieldPhoneEmployee;
	@FXML
	private TextField textFieldEmailEmployee;
	
	private Stage dialogStage;
	private List<String> employeeFunctions;
	private ObservableList<String> observableListEmployeeFunctions; 
	private Employee employee;
	private boolean buttonConfirmClicked = false;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		employeeFunctions = new ArrayList<>();
		employeeFunctions.add("Gerente");
		employeeFunctions.add("Vendedor");
		
		observableListEmployeeFunctions = FXCollections.observableArrayList(employeeFunctions);
		comboBoxEmployeeFunctions.setItems(observableListEmployeeFunctions);
	}
	
	public Stage getDialogStage() {
		return dialogStage;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setEmployee(Employee employee) {
		if (employee != null) {
			this.employee = employee;
			initializeTextFields();
		}
	}
	
	public void initializeTextFields() {
		textFieldNameEmployee.setText(employee.getName());
		comboBoxEmployeeFunctions.setValue(employee.getFunction());
		textFieldPhoneEmployee.setText(employee.getPhone());
		textFieldEmailEmployee.setText(employee.getEmail());
	}
	
	public boolean isButtonConfirmClicked() {
		return buttonConfirmClicked;
	}

	@FXML
	public void selectItemEmployeeFunction() {
		if (comboBoxEmployeeFunctions.getSelectionModel().getSelectedItem() == "Gerente") {
			employee = new Manager("", "", "");
		} else {
			employee = new Salesperson("", "", "");
		}
	}
	
	@FXML
	public void handleButtonConfirm() {
		CompanyBOEmployee companyBOEmployee = new CompanyBOEmployee(new Company());
		
		try {
			if (!comboBoxEmployeeFunctions.getSelectionModel().isEmpty()) {
				employee.setName(textFieldNameEmployee.getText());
				employee.setPhone(textFieldPhoneEmployee.getText());
				employee.setEmail(textFieldEmailEmployee.getText());
				
				if (isNewEmployee(employee)) {
					companyBOEmployee.validateAddEmployee(employee);
				} else {
					companyBOEmployee.validateUpdateEmployee(employee);
				}
				
				buttonConfirmClicked = true;
				dialogStage.close();
			} else {
				alert();
			}
		} catch (Exception e) {
			System.out.println("Exceção: " + e.getMessage());
		}
	}
	
	public boolean isNewEmployee(Employee employee) {
		return (employee.getId() == 0) ? true : false;
	}
	
	@FXML
	public void handleButtonCancel() {
		dialogStage.close();
	}
	
	public void alert() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText("Não há função selecionada!");
		alert.show();
	}
}