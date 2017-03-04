package br.edu.ifal.commanage.bo;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.util.FieldValidation;
import br.edu.ifal.commanage.util.exception.CompanyBOException;

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
}