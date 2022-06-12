package com.todo.exception;

public enum ErrorCodes {
	
	/*TODO*/
	TODO_NOT_FOUND(1000),
	TODO_NOT_VALID(1001),
	
	/*Utilisateur*/
	UTILISATEUR_NOT_FOUND(2000),
	UTILISATEUR_NOT_VALID(2001);
	
	private int code;
	
	ErrorCodes(int code){
		this.code=code;
	}
	
	public int getCode() {
		return code;
	}

}
