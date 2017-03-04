package br.edu.ifal.commanage.view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import br.edu.ifal.commanage.bo.CompanyBOEmployee;
import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LayoutEmployeesDialogController implements Initializable {
	
	@FXML
	private TextField textFieldNameEmployee;
	@FXML
	private TextField textFieldPhoneEmployee;
	@FXML
	private TextField textFieldEmailEmployee;
	@FXML
	private Button buttonConfirm;
	@FXML
	public Button buttonCancel;
	
	private Stage dialogStage;
	private Employee employee;
	private boolean buttonConfirmClicked = false;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	public Stage getDialogStage () {
		return dialogStage;
	}
	
	public void setDialogStage (Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setEmployee (Employee employee) {
		this.employee = employee;
		initializeTextFields();
	}
	
	public void initializeTextFields () {
		textFieldNameEmployee.setText(employee.getName());
		textFieldPhoneEmployee.setText(employee.getPhone());
		textFieldEmailEmployee.setText(employee.getEmail());
	}
	
	public boolean isButtonConfirmClicked () {
		return buttonConfirmClicked;
	}
	
	@FXML
	public void handleButtonConfirm () {
		employee.setName(textFieldNameEmployee.getText());
		employee.setPhone(textFieldPhoneEmployee.getText());
		employee.setEmail(textFieldEmailEmployee.getText());
		
		CompanyBOEmployee companyBOEmployee = new CompanyBOEmployee(new Company());
		
		try {
			if (isNewEmployee(employee)) {
				companyBOEmployee.validateAddEmployee(employee);
			} else {
				companyBOEmployee.validateUpdateEmployee(employee);
			}
			buttonConfirmClicked = true;
			dialogStage.close();
		} catch (Exception e) {
			System.out.println("Exceção: " + e.getMessage());
		}
	}
	
	public boolean isNewEmployee (Employee employee) {
		if (employee.getId() == 0)
			return true;
		return false;
	}
	
	@FXML
	public void handleButtonCancel () {
		dialogStage.close();
	}
}