package com.ivanparraga.bscal.core.calendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.testng.annotations.Test;

import com.ivanparraga.bscal.core.domain.Calendar;

public class CalendarLaoImplTest {
	@Test
	public void createCalendar() {
		CalendarDao dao = mock(CalendarDao.class);
		CalendarLao lao = new CalendarLaoImpl(null);
		String calendarName = "mine";
		short year = 2014;
		Calendar calendar = new BasicCalendar(calendarName, year);

		lao.createCalendar(calendar);

		verify(dao).create(calendar);
	}
}
