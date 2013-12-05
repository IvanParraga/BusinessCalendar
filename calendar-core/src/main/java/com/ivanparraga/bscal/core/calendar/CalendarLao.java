package com.ivanparraga.bscal.core.calendar;

import com.ivanparraga.bscal.core.domain.Calendar;

public interface CalendarLao {
	/**
	 * Creates a Calendar. If it has no id a new id is provided and set to the
	 * original calendar. If it set and acceptable for the persistence system
	 * then is used; otherwise an exception is thrown.
	 * @return the id assigned to this calendar.
	 */
	abstract String createCalendar(Calendar calendar);
}