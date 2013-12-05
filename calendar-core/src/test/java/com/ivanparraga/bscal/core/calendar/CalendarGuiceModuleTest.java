package com.ivanparraga.bscal.core.calendar;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class CalendarGuiceModuleTest {
	private Injector injector;

	@BeforeTest
	public void init() {
		CalendarGuiceModule module = new CalendarGuiceModule();
		injector = Guice.createInjector(module);
	}

	@Test
	public void configureCalendarLaoBinding() {
		Class<? extends CalendarLao> actualClass =
				injector.getInstance(CalendarLao.class).getClass();

		assertEquals(actualClass, CalendarLaoImpl.class);
	}

	@Test
	public void configureCalendarDaoBinding() {
		Class<? extends CalendarDao> actualClass =
				injector.getInstance(CalendarDao.class).getClass();

		assertEquals(actualClass, CalendarDaoMemoryImpl.class);
	}
}
