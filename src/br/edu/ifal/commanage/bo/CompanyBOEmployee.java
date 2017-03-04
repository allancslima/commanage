package br.edu.ifal.commanage.bo;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Employee;
import br.edu.ifal.commanage.util.FieldValidation;
import br.edu.ifal.commanage.util.exception.CompanyBOException;
import java.sql.SQLException;

public class CompanyBOEmployee {
	
	private Company company;
	
	public CompanyBOEmployee (Company company) {
		this.company = company;
	}
	
	/* public int getEmployeeQuantity () {
		return company.getEmployeeQuantity();
	} */
	
	public void validateAddEmployee (Employee employee) throws CompanyBOException, SQLException {
		if (FieldValidation.isValidName(employee.getName()) &&
			FieldValidation.isValidPhone(employee.getPhone()) &&
			FieldValidation.isValidEmail(employee.getEmail()))
		   company.addEmployee(employee);
		else
		  throw new CompanyBOException("Invalid employee");
	}
	
	public void validateUpdateEmployee (Employee employee) throws CompanyBOException, SQLException {
		if (FieldValidation.isValidName(employee.getName()) &&
			FieldValidation.isValidPhone(employee.getPhone()) &&
			FieldValidation.isValidEmail(employee.getEmail()))
		   company.updateEmployee(employee);
		else
		  throw new CompanyBOException("Invalid employee");
	}
	
	/* public boolean removeEmployee (Employee employee) {
		return company.removeEmployee(employee);
	} */
}