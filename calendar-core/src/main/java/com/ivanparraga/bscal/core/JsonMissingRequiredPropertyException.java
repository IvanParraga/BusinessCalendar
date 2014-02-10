package com.ivanparraga.bscal.core;

public class JsonMissingRequiredPropertyException extends JsonException {
	private static final String MESSAGE = "Missing required property ";

	private String property;

	public JsonMissingRequiredPropertyException(Throwable cause, String property) {
		super(getMessage(property), cause);
	}

	public JsonMissingRequiredPropertyException(String property) {
		super(getMessage(property));
	}

	public String getProperty() {
		return property;
	}

	private static String getMessage(String property) {
		return MESSAGE + property;
	}
}
