package br.edu.ifal.commanage.bo;

import java.sql.SQLException;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Employee;
import br.edu.ifal.commanage.model.Provider;
import br.edu.ifal.commanage.util.FieldValidation;
import br.edu.ifal.commanage.util.exception.CompanyBOException;

public class CompanyBOProvider {
	
	private Company company;
	
	public CompanyBOProvider (Company company) {
		this.company = company;
	}
	
	public void validateAddProvider (Provider provider) throws CompanyBOException, SQLException {
		if (FieldValidation.isValidName(provider.getName()) &&
			FieldValidation.isValidPhone(provider.getPhone()) &&
			FieldValidation.isValidEmail(provider.getEmail()))
		   company.addProvider(provider);
		else
		  throw new CompanyBOException("Invalid provider");
	}
	
	public void validateUpdateEmployee (Employee employee) throws CompanyBOException, SQLException {
		if (FieldValidation.isValidName(employee.getName()) &&
			FieldValidation.isValidPhone(employee.getPhone()) &&
			FieldValidation.isValidEmail(employee.getEmail()))
		   company.updateEmployee(employee);
		else
		  throw new CompanyBOException("Invalid employee");
	}
	
	public void removeEmployee (Employee employee) throws SQLException {
		company.removeEmployee(employee.getId());
	}
}