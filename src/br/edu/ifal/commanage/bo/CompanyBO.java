package br.edu.ifal.commanage.bo;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Employee;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.model.Sale;
import br.edu.ifal.commanage.util.FieldValidation;
import br.edu.ifal.commanage.util.exception.CompanyBOException;
import java.sql.SQLException;

public class CompanyBO {
	
	private Company company;
	
	public CompanyBO (Company company) {
		this.company = company;
	}
	
	public String getName () {
		return company.getName();
	}
	
	public void validateSetCompanyName (String name) throws CompanyBOException {
		if (FieldValidation.isValidName(name))
			company.setName(name);
		else
			throw new CompanyBOException("Invalid name");
	}
	
	public String getCnpj () {
		return company.getCnpj();
	}
	
	public void validateSetCompanyCnpj (String cnpj) throws CompanyBOException {
		if (FieldValidation.isValidCnpj(cnpj))
			company.setCnpj(cnpj);
		else
			throw new CompanyBOException("Invalid cnpj");
	}
	
	public String getPhone () {
		return company.getPhone();
	}
	
	public void validateSetCompanyPhone (String phone) throws CompanyBOException {
		if (FieldValidation.isValidPhone(phone))
			company.setPhone(phone);
		else
			throw new CompanyBOException("Invalid phone");
	}
	
	/*public int getEmployeeQuantity () {
		return company.getEmployeeQuantity();
	}*/
	
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
	
	/*public boolean removeEmployee (Employee employee) {
		return company.removeEmployee(employee);
	}*/
	
	public int getProductQuantity (){
		return company.getProductQuantity();
	}
	
	public void validateAddProduct (Product product) throws CompanyBOException {
		if (FieldValidation.isValidName(product.getName()) && 
			product.getPurchasePrice() > 0 && product.getSalePrice() > 0)
			company.addProduct(product);
		else
			throw new CompanyBOException("Invalid product");
	}
	
	public boolean removeProduct (Product product) {
		return company.removeProduct(product);
	}
	
	public int getSaleQuantity(){
		return company.getSaleQuantity();
	}
	
	public void validateAddSale (Sale sale) throws CompanyBOException {
		if(sale.getQuantity() > 0)
			company.addSale(sale);
		else
			throw new CompanyBOException("Invalid sale");
	}
	
	public boolean removeSale (Sale sale) throws CompanyBOException {
		return company.removeSale(sale);
	}
}