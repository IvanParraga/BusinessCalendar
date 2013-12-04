package com.ivanparraga.bscal.core.calendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class CalendarLaoTest {
	private Injector injector;

	@BeforeMethod
	public void init() {
		injector = Guice.createInjector(new TestLaoModule());
	}


	@Test
	public void createCalendar() {
		CalendarLao lao = injector.getInstance(CalendarLao.class);
		String calendarName = "mine";
		short year = 2014;

		Calendar calendar = lao.createCalendar(calendarName, year);

		assertEquals(calendar.getName(), calendarName);
		assertEquals(calendar.getYear(), year);

		Calendar expectedCalendar = mock(Calendar.class);
		when(expectedCalendar.getName()).thenReturn(calendarName);
		when(expectedCalendar.getYear()).thenReturn(year);
		assertEquals(calendar, expectedCalendar);
	}

	@Test
	public void createCalendar_verifyDaoCall() {
//		CalendarLao lao = new CalendarLao
	}

	private static class TestLaoModule extends AbstractModule {
		@Override
		protected void configure() {
			bind(CalendarDao.class).to(mock(CalendarDao.class).getClass());
		}
	}
}
