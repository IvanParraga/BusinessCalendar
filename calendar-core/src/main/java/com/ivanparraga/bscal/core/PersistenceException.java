package com.ivanparraga.bscal.core;

/**
 * Signals a problem on the persistence subsystem.
 */
public class PersistenceException extends RuntimeException {
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(String message) {
		super(message);
	}
}
