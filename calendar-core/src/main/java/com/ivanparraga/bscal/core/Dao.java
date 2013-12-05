package com.ivanparraga.bscal.core;

import com.ivanparraga.bscal.core.domain.DomainObject;

/**
 * Basic CRUD operations.
 */
public interface Dao<T extends DomainObject> {
	/**
	 * Creates an object on the persistence system. It must contain an id.
	 */
	void create(T t) throws PersistenceException;

	/**
	 * @throws NotSuchObjectException if the requested domain object is not
	 * 	stored
	 */
	T read(String id) throws PersistenceException;

	void delete(String id) throws PersistenceException;
}
