package br.edu.ifal.commanage.model;

public class Employee {
	
	private int id;
	private String name;
	protected String function;
	private String phone;
	private String email;
	
	public Employee (String name, String phone, String email) {
		setName(name);
		setPhone(phone);
		setEmail(email);
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getName () {
		return name;	
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getFuncion () {
		return function;
	}
	
	public void setFunction (String function) {
		this.setFunction(function);
	}
	
	public String getPhone () {
		return phone;
	}
	
	public void setPhone (String phone) {
		this.phone = phone;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
}