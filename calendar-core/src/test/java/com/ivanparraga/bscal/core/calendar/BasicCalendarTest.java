package com.ivanparraga.bscal.core.calendar;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

public class BasicCalendarTest {
	@Test
	public void testClone() {
		BasicCalendar cal1 = new BasicCalendar("foo", "fooName", 1900);
		BasicCalendar cal2 = cal1.newClon();
		assertEquals(cal1, cal2);
		assertFalse(cal1 == cal2);
		assertEquals(cal1.getId(), cal2.getId());
		assertEquals(cal1.getName(), cal2.getName());
		assertEquals(cal1.getYear(), cal2.getYear());
	}
}
