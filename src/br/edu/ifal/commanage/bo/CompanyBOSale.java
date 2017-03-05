package br.edu.ifal.commanage.bo;

import java.sql.SQLException;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Sale;
import br.edu.ifal.commanage.util.exception.CompanyBOException;

public class CompanyBOSale {
	
	private Company company;
	
	public CompanyBOSale (Company company) {
		this.company = company;
	}
	
	/* public int getSaleQuantity(){
		return company.getSaleQuantity();
	} */
	
	public void validateAddSale (Sale sale) throws CompanyBOException, SQLException {
		if (sale.getQuantity() > 0)
			company.addSale(sale);
		else
			throw new CompanyBOException("Invalid sale");
	}
	
	/* public boolean removeSale (Sale sale) throws CompanyBOException {
		return company.removeSale(sale);
	} */
}