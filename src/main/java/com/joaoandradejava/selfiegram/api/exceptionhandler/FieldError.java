package com.joaoandradejava.selfiegram.api.exceptionhandler;

public class FieldError {
	private final String field;
	private final String userMessage;

	public FieldError(String field, String userMessage) {
		this.field = field;
		this.userMessage = userMessage;
	}

	public String getField() {
		return field;
	}

	public String getUserMessage() {
		return userMessage;
	}

}
