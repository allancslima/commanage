package br.edu.ifal.commanage.bo;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.util.FieldValidation;
import br.edu.ifal.commanage.util.exception.CompanyBOException;

public class CompanyBOProduct {
	
	private Company company;
	
	public CompanyBOProduct (Company company) {
		this.company = company;
	}
	
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
}