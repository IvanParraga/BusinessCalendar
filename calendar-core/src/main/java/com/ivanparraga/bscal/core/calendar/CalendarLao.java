package com.ivanparraga.bscal.core.calendar;

import com.ivanparraga.bscal.core.PersistenceException;
import com.ivanparraga.bscal.core.domain.Calendar;

public interface CalendarLao {
	/**
	 * Creates a Calendar. Id must not be set.
	 * @return The new created Calendar that is a new instance.
	 */
	Calendar create(Calendar calendar) throws PersistenceException;

	Calendar read(String id) throws PersistenceException;
}