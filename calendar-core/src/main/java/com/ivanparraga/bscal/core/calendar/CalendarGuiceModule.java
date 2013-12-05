package com.ivanparraga.bscal.core.calendar;

import com.google.inject.AbstractModule;

public class CalendarGuiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(CalendarLao.class).to(CalendarLaoImpl.class);
		bind(CalendarDao.class).to(CalendarDaoMemoryImpl.class);
	}
}
