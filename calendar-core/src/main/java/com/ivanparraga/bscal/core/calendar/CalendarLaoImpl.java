package com.ivanparraga.bscal.core.calendar;

import java.util.UUID;

import javax.inject.Inject;

import com.ivanparraga.bscal.core.domain.Calendar;

class CalendarLaoImpl implements CalendarLao {
	private final CalendarDao dao;

	@Inject
	CalendarLaoImpl(CalendarDao dao) {
		this.dao = dao;
	}

	@Override
	public String create(Calendar calendar) {
		String id = computeNewId();
		calendar.setId(id);
		dao.create(calendar);
		return id;
	}

	private String computeNewId() {
		return UUID.randomUUID().toString();
	}
}
