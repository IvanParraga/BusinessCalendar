package com.ivanparraga.bscal.core.calendar;

import javax.inject.Inject;

class CalendarLaoImpl implements CalendarLao {
	private final CalendarDao dao;

	@Inject
	CalendarLaoImpl(CalendarDao dao) {
		this.dao = dao;
	}

	@Override
	public Calendar createCalendar(Calendar calendar) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.ivanparraga.bscal.core.calendar.CalendarLao#createCalendar(java.lang.String, short)
	 */
//	@Override
//	public Calendar createCalendar(Calendar calendar) {
//		Calendar calendar = new BasicCalendar(name, year);
//		dao.create(calendar);
//		return calendar;
//	}


}
