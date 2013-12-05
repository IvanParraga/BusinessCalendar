package com.ivanparraga.bscal.core.calendar;

import javax.inject.Inject;

class CalendarLaoImpl implements CalendarLao {
	private final CalendarDao dao;

	@Inject
	CalendarLaoImpl(CalendarDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.ivanparraga.bscal.core.calendar.CalendarLao#createCalendar(java.lang.String, short)
	 */
	@Override
	public Calendar createCalendar(String name, short year) {
		Calendar calendar = new BasicCalendar(name, year);
		dao.create(calendar);
		return calendar;
	}
}
