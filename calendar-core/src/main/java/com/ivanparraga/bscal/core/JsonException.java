package com.ivanparraga.bscal.core;

public class JsonException extends RuntimeException {

	public JsonException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public JsonException(String arg0) {
		super(arg0);
	}
}
