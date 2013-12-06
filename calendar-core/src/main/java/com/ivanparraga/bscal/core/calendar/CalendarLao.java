package com.ivanparraga.bscal.core.calendar;

import com.ivanparraga.bscal.core.PersistenceException;
import com.ivanparraga.bscal.core.domain.Calendar;

public interface CalendarLao {
	/**
	 * Creates a Calendar. Id must not be set.
	 * @return the id assigned to this calendar.
	 */
	String create(Calendar calendar) throws PersistenceException;
}