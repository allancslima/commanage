package br.edu.ifal.commanage.model;

public interface IAuthenticable {
	
	boolean authenticate (String password);
}