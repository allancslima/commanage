package br.edu.ifal.commanage.bo;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Employee;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.util.FieldValidation;

public class CompanyBO {
	
	private Company company;
	
	public CompanyBO (Company company) {
		this.company = company;
	}
	
	public String getName () {
		return company.getName();
	}
	
	public void validateSetCompanyName (String name) throws Exception {
		if (FieldValidation.isValidName(name))
			company.setName(name);
		else
			throw new Exception("Invalid name");
	}
	
	public String getCnpj () {
		return company.getCnpj();
	}
	
	public void validateSetCompanyCnpj (String cnpj) throws Exception {
		if (FieldValidation.isValidCnpj(cnpj))
			company.setCnpj(cnpj);
		else
			throw new Exception("Invalid cnpj");
	}
	
	public String getPhone () {
		return company.getPhone();
	}
	
	public void validateSetCompanyPhone (String phone) throws Exception {
		if (FieldValidation.isValidPhone(phone))
			company.setPhone(phone);
		else
			throw new Exception("Invalid phone");
	}
	
	public void validateAddEmployee(Employee employee) throws Exception {
		if (FieldValidation.isValidName(employee.getName()) && FieldValidation.isValidPhone(employee.getPhone()) && FieldValidation.isValidEmail(employee.getEmail()))
		   company.addEmployee(employee);
		else
		  throw new Exception("Invalid employee");
		}
	public boolean removeEmployee(Employee employee){
		return company.removeEmployee(employee);
	}
	
	public int getEmployeeQuantity() { 
		return company.getEmployeeQuantity(); 
				}
	
	public void validateAddProduct (Product product) throws Exception {
		if (product.getSellPrice() > 0 && product.getBuyPrice() > 0)
			company.addProduct(product);
		else
			throw new Exception("Invalid product");
	}
	
	public boolean removeProduct (Product product) {
		return company.removeProduct(product);
	}
	
	public int getProductQuantity(){
		return company.getProductQuantity();
	}
}