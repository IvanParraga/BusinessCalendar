package com.ivanparraga.bscal.core;

public class DuplicateIdException extends PersistenceException {
	public DuplicateIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateIdException(String message) {
		super(message);
	}
}
