package com.ivanparraga.bscal.core.calendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.ivanparraga.bscal.core.config.New;

public class CalendarLaoTest {
	@Test
	public void createCalendar() {
		CalendarLao lao = New.getInstance(CalendarLao.class);
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
	}
}
