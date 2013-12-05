package com.ivanparraga.bscal.core.calendar;

import javax.inject.Inject;

import com.ivanparraga.bscal.core.domain.Calendar;

class CalendarLaoImpl implements CalendarLao {
	private final CalendarDao dao;

	@Inject
	CalendarLaoImpl(CalendarDao dao) {
		this.dao = dao;
	}

	@Override
	public String createCalendar(Calendar calendar) {
		dao.create(calendar);
		return null;
	}
}
