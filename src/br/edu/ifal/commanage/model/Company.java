package br.edu.ifal.commanage.model;

import java.util.ArrayList;

public class Company {

	private String name;
	private String cnpj;
	private String phone;
	private ArrayList<Product> products = new ArrayList<>();
	
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
	
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public boolean removeProduct(Product product) {
		return products.remove(product);
	}
	public int getProductQuantity(){
		return products.size();
	}
}