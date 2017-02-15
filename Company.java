package br.edu.ifal.commanage.model;

import java.util.ArrayList;

public class Company {
	
	private String name;
	private String cnpj;
	private String phone;
	private ArrayList<Employee> employess = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void addEmployee(Employee employee) {
		employess.add(employee);
	}
	
	public boolean removeEmployee(Employee employee) {
		return employess.remove(employee);
	}
}