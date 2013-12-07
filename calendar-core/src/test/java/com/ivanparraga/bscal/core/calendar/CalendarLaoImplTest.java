package com.ivanparraga.bscal.core.calendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.ivanparraga.bscal.core.domain.Calendar;

public class CalendarLaoImplTest {
	@Test
	public void create() {
		CalendarDao dao = mock(CalendarDao.class);
		CalendarLao lao = new CalendarLaoImpl(dao);
		String calendarName = "mine";
		short year = 2014;
		Calendar calendar = new BasicCalendar(calendarName, year);

		Calendar createdCalendar = lao.create(calendar);

		verify(dao).create(createdCalendar);
		assertNotNull(createdCalendar.getId());
		assertFalse(calendar == createdCalendar, "Instances should be different");
	}

	@Test
	public void read() {
		CalendarDao dao = mock(CalendarDao.class);
		String id = "foo";
		Calendar expectedCalendar = new BasicCalendar("fooname", (short)2004);
		when(dao.read(id)).thenReturn(expectedCalendar);
		CalendarLao lao = new CalendarLaoImpl(dao);

		Calendar actualCalendar = lao.read(id);

		verify(dao).read(id);
		assertEquals(actualCalendar, expectedCalendar);
	}
}
