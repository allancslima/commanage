package br.edu.ifal.commanage.model;

import java.util.ArrayList;

public class Company {

	private String name;
	private String cnpj;
	private String phone;
	private ArrayList<Employee> employees = new ArrayList<>();
	private ArrayList<Product> products = new ArrayList<>();
	private ArrayList<Sale> sales = new ArrayList<>();
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getCnpj () {
		return cnpj;
	}
	
	public void setCnpj (String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getPhone () {
		return phone;
	}
	
	public void setPhone (String phone) {
		this.phone = phone;
	}
	
	public int getEmployeeQuantity () { 
		return employees.size();
	}
	
	public void addEmployee (Employee employee) {
		employees.add(employee);
	}
	
	public boolean removeEmployee (Employee employee) {
		return employees.remove(employee);
	}
	
	public int getProductQuantity () { 
		return products.size();
	}
	
	public void addProduct (Product product) {
		products.add(product);
	}
	
	public boolean removeProduct (Product product) {
		return products.remove(product);
	}
	
	public int getSaleQuantity (){
		return sales.size();
	}

	public void addSale (Sale sale) {
		sales.add(sale);
	}
	
	public boolean removeSale (Sale sale) {
		return sales.remove(sale);
	}
}