package com.ivanparraga.bscal.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ivanparraga.bscal.core.domain.DomainObject;

public class MemoryDaoTest {
	private Dao<DomainObject> dao;
	private DomainObject domainObject;

	@BeforeMethod
	public void init() {
		dao = new MemoryDao<>();
		domainObject = mock(DomainObject.class);
	}

	@Test
	public void createRead() {
		String id = "1234";
		when(domainObject.getId()).thenReturn(id);

		dao.create(domainObject);
		DomainObject actualObject = dao.read(id);

		assertEquals(actualObject, domainObject);
	}

	@Test(expectedExceptions = PersistenceException.class,
		expectedExceptionsMessageRegExp = "Id cannot be null")
	public void createNoId() {
		dao.create(domainObject);
	}

	@Test(expectedExceptions = DuplicateIdException.class,
			expectedExceptionsMessageRegExp =
			"Entity with id \"whatever\" already existed")
	public void createDuplicatedId() {
		when(domainObject.getId()).thenReturn("whatever");
		dao.create(domainObject);
		dao.create(domainObject);
	}

	@Test(expectedExceptions = NoSuchObjectException.class,
		expectedExceptionsMessageRegExp =
		"Object with id \"whatever\" cannot be found")
	public void readNotExisting() {
		dao.read("whatever");
	}
}
