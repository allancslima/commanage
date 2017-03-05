package br.edu.ifal.commanage.model;

public class Manager extends Employee {

	public Manager(String name, String phone, String email) {
		super(name, phone, email);
		this.function = "Gerente";
	}	
}