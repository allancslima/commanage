package br.edu.ifal.commanage.bo;

import br.edu.ifal.commanage.model.Company;

public class CompanyBO {
	
	private Company company;
	
	public CompanyBO(Company company) {
		this.company = company;
	}
	
	public String getName() {
		return company.getName();
	}
	
	public void validateSetCompanyName(String name) throws Exception {
		String pattern = "[A-Z][a-z]{1,}";
		if (name.matches(pattern)) {
			company.setName(name);
		} else {
			throw new Exception("Invalid name");
		}
	}
	
	public String getCnpj() {
		return company.getCnpj();
	}
	
	public void validateSetCompanyCnpj(String cnpj) throws Exception {
		String pattern = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[/][0-9]{4}[.][0-9]{2}";
		if (cnpj.matches(pattern)) {
			company.setCnpj(cnpj);
		} else {
			throw new Exception("Invalid cnpj");
		}
	}
	
	public String getPhone() {
		return company.getPhone();
	}
	
	public void validateSetCompanyPhone(String phone) throws Exception {
		String pattern = "[0-9]{5}[-][0-9]{4}";
		if (phone.matches(pattern)) {
			company.setPhone(phone);
		} else {
			throw new Exception("Invalid phone");
		}
	}
}