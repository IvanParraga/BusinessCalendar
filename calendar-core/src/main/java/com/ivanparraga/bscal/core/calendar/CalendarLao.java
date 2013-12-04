package com.ivanparraga.bscal.core.calendar;

import javax.inject.Inject;

public class CalendarLao {
	@Inject private CalendarDao dao;

	public Calendar createCalendar(String name, short year) {
		Calendar calendar = new BasicCalendar(name, year);
		dao.create(calendar);
		return calendar;
	}
}
