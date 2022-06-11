package com.todo.exception;

import lombok.Data;

@Data
public class NoSuchElementException extends RuntimeException{

	private ErrorCodes errorCode;

	public NoSuchElementException(String message) {
		super(message);
	}
	
	public NoSuchElementException(String message , Throwable cause) {
		super(message ,cause);
	}
	
	public NoSuchElementException(String message , Throwable cause , ErrorCodes errorCode) {
		super(message , cause);
		this.errorCode = errorCode;
	}
	
	public NoSuchElementException(String message , ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

}
