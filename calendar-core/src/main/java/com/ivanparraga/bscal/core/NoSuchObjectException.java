package com.ivanparraga.bscal.core;

/**
 * Signals that a requested object cannot be found.
 */
public class NoSuchObjectException extends PersistenceException {
	public NoSuchObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchObjectException(String message) {
		super(message);
	}
}
