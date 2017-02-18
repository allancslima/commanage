package br.edu.ifal.commanage.util;

public class FieldValidation {
	
	public static boolean isValidName (String name) {
		String pattern = "[A-Z][a-z]{1,}";
		return name.matches(pattern);
	}
	
	public static boolean isValidCnpj (String cnpj) {
		String pattern = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[/][0-9]{4}[.][0-9]{2}";
		return cnpj.matches(pattern);
	}
	
	public static boolean isValidEmail (String email) {
		String pattern = "[a-z|0-9|.|-]{3,}[@][a-z]{3,}[.][a-z]{2,}";
		return email.matches(pattern);
	}
	
	public static boolean isValidPhone (String phone) {
		String pattern = "[(][0-9]{2}[)][0-9]{5}[-][0-9]{4}";
		return phone.matches(pattern);
	}
}