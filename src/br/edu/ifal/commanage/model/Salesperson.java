package br.edu.ifal.commanage.model;

public class Salesperson extends Employee {

	public Salesperson(String name, String phone, String email) {
		super(name, phone, email);
		this.function = "Vendedor";
	}
}