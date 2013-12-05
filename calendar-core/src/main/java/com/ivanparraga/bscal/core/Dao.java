package com.ivanparraga.bscal.core;

import com.ivanparraga.bscal.core.domain.Entity;

/**
 * Basic CRUD operations.
 */
public interface Dao<T extends Entity> {
	/**
	 * Creates an entity on the persistence system. It must contain an id.
	 */
	void create(T t) throws PersistenceException;

	/**
	 * @throws NotSuchObjectException if the requested entity is not
	 * 	stored
	 */
	T read(String id) throws PersistenceException;

	/**
	 * Deletes the specified entity.
	 */
	void delete(String id) throws PersistenceException;
}
