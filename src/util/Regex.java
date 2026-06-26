package util;

import java.util.regex.Pattern;

public class Regex {
	private static final String regexNome = "^[A-Za-zÀ-ÿ ]+$";
	private static final String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	private static final String regexTelefone = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$";
	
	public static boolean validaNome(String nome) {
		if(nome == null) {
			return false;
		}
		return Pattern.matches(regexNome, nome.trim());
	}
	
	public static boolean validaEmail(String email) {
		if(email == null) {
			return false;
		}
		return Pattern.matches(regexEmail, email.trim());
	}
	
	public static boolean validaTelefone(String telefone) {
		if(telefone == null) {
			return false;
		}
		return Pattern.matches(regexTelefone, telefone.trim());
	}
	
	
}
