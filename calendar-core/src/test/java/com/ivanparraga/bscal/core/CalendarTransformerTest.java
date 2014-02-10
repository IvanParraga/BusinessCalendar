package com.ivanparraga.bscal.core;

import org.testng.annotations.Test;

import com.ivanparraga.bscal.core.calendar.BasicCalendar;
import com.ivanparraga.bscal.core.calendar.CalendarTransformer;
import com.ivanparraga.bscal.core.domain.Calendar;

public class CalendarTransformerTest {
	@Test
	public void deserialize() {
		Calendar calendar = new BasicCalendar("id","foo",1900);
		new CalendarTransformer().serialize(calendar);
	}

	@Test(
			expectedExceptions=JsonException.class,
			expectedExceptionsMessageRegExp="Missing required property name")
	public void nullRequiredName() {
		String calendar = "{\"year\":1979}";
		CalendarTransformer transformer = new CalendarTransformer();

		transformer.deserialize(calendar);
	}

	@Test(
			expectedExceptions=JsonException.class,
			expectedExceptionsMessageRegExp="Missing required property year")
	public void nullRequiredYear() {
		String calendar = "{\"name\":\"foo\"}";
		CalendarTransformer transformer = new CalendarTransformer();

		transformer.deserialize(calendar);
	}
}
