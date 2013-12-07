package com.ivanparraga.bscal.core;

import java.util.Hashtable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivanparraga.bscal.core.domain.Entity;

/**
 * This is a generic on-memory DAO for prototyping.
 */
public class MemoryDao<T extends Entity<?>> implements Dao<T> {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// I use Hashtable because it is thread safe
	private final Map<String, T> content = new Hashtable<>();

	@Override
	public void create(T t) {
		String id = t.getId();
		validateCreateConditions(id);
		content.put(id, t);

		logger.debug("{} created on memory under key {}", t, id);
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
			String msg = "Object with id \"" + id + "\" cannot be found";
			logger.debug(msg);
			throw new NoSuchObjectException(msg);
		}

		logger.debug("{} read from memory", object);

		return object;
	}

	@Override
	public void delete(String id) throws PersistenceException {
		content.remove(id);

		logger.debug("{} deleted from memory", id);
	}
}
