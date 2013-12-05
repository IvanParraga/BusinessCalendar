package com.ivanparraga.bscal.core.calendar;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import org.testng.annotations.Test;

public class CalendarLaoTest {
	@Test
	public void createCalendar() {
		CalendarLao lao = new CalendarLaoImpl(null);
		String calendarName = "mine";
		short year = 2014;
		Calendar calendar = new BasicCalendar(calendarName, year);

		lao.createCalendar(calendar);

		assertEquals(calendar.getName(), calendarName);
		assertEquals(calendar.getYear(), year);
		assertNotNull(calendar.getId());
	}

	@Test
	public void createCalendar_verifyDaoCall() {
	}
}
