package br.edu.ifal.commanage.bo;

import java.sql.SQLException;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.util.FieldValidation;
import br.edu.ifal.commanage.util.exception.CompanyBOException;

public class CompanyBOProduct {
	
	private Company company;
	
	public CompanyBOProduct (Company company) {
		this.company = company;
	}
	
	public void validateAddProduct (Product product) throws CompanyBOException, SQLException {
		if (FieldValidation.isValidName(product.getName()) && 
			FieldValidation.isValidName(product.getCategory()) &&
			product.getPurchasePrice() > 0 && product.getSalePrice() > 0)
				company.addProduct(product);
		else
			throw new CompanyBOException("Invalid product");
	}
	
	public void validateUpdateProduct(Product product) throws CompanyBOException, SQLException {
		if (FieldValidation.isValidName(product.getName()) && 
			FieldValidation.isValidName(product.getCategory()) && 
			product.getPurchasePrice() > 0 && product.getSalePrice() > 0)
				company.updateProduct(product);
		else
			throw new CompanyBOException("Invalid product");
	}
	
	public void removeProduct (Product product) throws SQLException {
		company.removeProduct(product.getId());
	}
}