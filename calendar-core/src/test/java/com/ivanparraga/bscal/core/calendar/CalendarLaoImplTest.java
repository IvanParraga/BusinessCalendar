package com.ivanparraga.bscal.core.calendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.ivanparraga.bscal.core.domain.Calendar;

public class CalendarLaoImplTest {
	@Test
	public void createCalendar() {
		CalendarDao dao = mock(CalendarDao.class);
		CalendarLao lao = new CalendarLaoImpl(dao);
		String calendarName = "mine";
		short year = 2014;
		Calendar calendar = new BasicCalendar(calendarName, year);

		String id = lao.create(calendar);

		verify(dao).create(calendar);
		assertNotNull(calendar.getId());
		assertEquals(id, calendar.getId());
	}
}
