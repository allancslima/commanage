package br.edu.ifal.commanage.util;

public final class FieldValidation {
	
	public static boolean isValidName(String name) {
		String pattern = "[A-Z][a-z]{1,}";
		if (name.matches(pattern))
			return true;
		return false;
	}
	
	public static boolean isValidCnpj(String cnpj) {
		String pattern = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[/][0-9]{4}[.][0-9]{2}";
		if (cnpj.matches(pattern))
			return true;
		return false;
	}
	
	public static boolean isValidPhone(String phone) {
		String pattern = "[(][0-9]{2}[)][0-9]{5}[-][0-9]{4}";
		if (phone.matches(pattern))
			return true;
		return false;
	}
}