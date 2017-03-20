package br.edu.ifal.commanage.bo;

import java.sql.SQLException;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Purchase;
import br.edu.ifal.commanage.util.exception.CompanyBOException;

public class CompanyBOPurchase {
	
	private Company company;
	
	public CompanyBOPurchase (Company company) {
		this.company = company;
	}
	
	public void validateAddPurchase (Purchase purchase) throws CompanyBOException, SQLException {
		if (purchase.getProductId() != 0 && purchase.getQuantity() > 0)
			company.addPurchase(purchase);
		else
			throw new CompanyBOException("Invalid purchase");
	}
	
	public void removePurchase (Purchase purchase) throws SQLException {
		company.removePurchase(purchase);
	}
}