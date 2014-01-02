package com.ivanparraga.bscal.core.calendar;

import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import com.ivanparraga.bscal.core.PersistenceException;
import com.ivanparraga.bscal.core.domain.Calendar;

class CalendarLaoImpl implements CalendarLao {
	private final CalendarDao dao;

	@Inject
	CalendarLaoImpl(CalendarDao dao) {
		this.dao = dao;
	}

	@Override
	public Calendar create(Calendar calendar) {
		Calendar newCalendar = calendar.newClon();
		String id = computeNewId();
		newCalendar.setId(id);

		dao.create(newCalendar);
		return newCalendar;
	}

	private String computeNewId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public Calendar read(String id) throws PersistenceException {
		return dao.read(id);
	}

	@Override
	public Set<Calendar> read() throws PersistenceException {
		return dao.read();
	}
}
