package com.ivanparraga.bscal.core;

import org.testng.annotations.Test;

import com.ivanparraga.bscal.core.calendar.BasicCalendar;
import com.ivanparraga.bscal.core.domain.Calendar;

public class CalendarTransformerTest {
	@Test
	public void deserialize() {
		Calendar calendar = new BasicCalendar("id","foo",1900);
		String str = new CalendarTransformer().serialize(calendar);
	}
}
