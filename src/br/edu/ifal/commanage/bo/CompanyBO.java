package br.edu.ifal.commanage.bo;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.util.FieldValidation;

public class CompanyBO {
	
	private Company company;
	
	public CompanyBO(Company company) {
		this.company = company;
	}
	
	public String getName() {
		return company.getName();
	}
	
	public void validateSetCompanyName(String name) throws Exception {
		if (FieldValidation.isValidName(name))
			company.setName(name);
		else
			throw new Exception("Invalid name");
	}
	
	public String getCnpj() {
		return company.getCnpj();
	}
	
	public void validateSetCompanyCnpj(String cnpj) throws Exception {
		if (FieldValidation.isValidCnpj(cnpj))
			company.setCnpj(cnpj);
		else
			throw new Exception("Invalid cnpj");
	}
	
	public String getPhone() {
		return company.getPhone();
	}
	
	public void validateSetCompanyPhone(String phone) throws Exception {
		if (FieldValidation.isValidPhone(phone))
			company.setPhone(phone);
		else
			throw new Exception("Invalid phone");
	}
	
	public void validateAddProduct(Product product) throws Exception {
		if(product.getBuyprice()>0 && product.getSellprice()>0){
			company.addProduct(product);
		} else {
			throw new Exception("Invalid product");
		}
	}
	
	public boolean removeProduct(Product product){
		return company.removeProduct(product);
	}
}