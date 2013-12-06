package com.ivanparraga.bscal.core;

import java.util.Hashtable;
import java.util.Map;

import com.ivanparraga.bscal.core.domain.Entity;

/**
 * This is a generic on-memory DAO for prototyping.
 */
public class MemoryDao<T extends Entity> implements Dao<T> {
	// I use Hashtable because it is thread safe
	private final Map<String, T> content = new Hashtable<>();

	@Override
	public void create(T t) {
		String id = t.getId();
		validateCreateConditions(id);
		content.put(id, t);
	}

	private void validateCreateConditions(String id) {
		if (id == null) {
			throw new PersistenceException("Id cannot be null");
		}

		if (content.get(id) != null) {
			throw new DuplicateIdException(
				"Entity with id \"" + id + "\" already existed");
		}
	}

	@Override
	public T read(String id) throws PersistenceException {
		T object = content.get(id);

		if (object == null) {
			throw new NoSuchObjectException(
				"Object with id \"" + id + "\" cannot be found");
		}

		return object;
	}

	@Override
	public void delete(String id) throws PersistenceException {
		content.remove(id);
	}
}
