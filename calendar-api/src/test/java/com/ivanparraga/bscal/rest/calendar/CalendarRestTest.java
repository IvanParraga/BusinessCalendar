package com.ivanparraga.bscal.rest.calendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ivanparraga.bscal.core.NoSuchObjectException;
import com.ivanparraga.bscal.core.calendar.BasicCalendar;
import com.ivanparraga.bscal.core.calendar.CalendarLao;
import com.ivanparraga.bscal.core.domain.Calendar;

public class CalendarRestTest {
	private CalendarLao lao;
	private CalendarRest rest;

	@BeforeMethod
	public void init() {
		lao = mock(CalendarLao.class);
		rest = new CalendarRest(lao);
	}

	@Test
	public void readExisting() throws JSONException {
		String id = "foo";
		Calendar calendar = new BasicCalendar(id, "fooName", 2004);
		when(lao.read(id)).thenReturn(calendar);

		String actualCalendar = rest.read(id);

		String expected = "{id:\"foo\",name:\"fooName\",year:2004}";
		JSONAssert.assertEquals(expected, actualCalendar, false);
	}

	@Test(expectedExceptions = NotFoundException.class)
	public void readNotExisting() throws JSONException {
		String id = "foo";
		when(lao.read(id)).thenThrow(new NoSuchObjectException(""));

		rest.read(id);
	}

	@Test
	public void readAllExisting() throws JSONException {
		Set<Calendar> calendars = new HashSet<>();
		calendars.add(new BasicCalendar("foo", "fooName", 2004));
		calendars.add(new BasicCalendar("boo", "booName", 2005));
		when(lao.read()).thenReturn(calendars);

		String actualCalendar = rest.read();

		String expected = "["
				+ "{id:\"foo\",name:\"fooName\",year:2004},"
				+ "{id:\"boo\",name:\"booName\",year:2005}"
				+ "]";
		JSONAssert.assertEquals(expected, actualCalendar, false);
	}

	@Test
	public void create() throws JSONException {
		String id = "foo";
		String calendarName = "fooName";
		int year = 2004;
		Calendar passedCalendar = new BasicCalendar(calendarName, year);
		Calendar expectedCalendar = new BasicCalendar(id, calendarName, year);
		when(lao.create(passedCalendar)).thenReturn(expectedCalendar);

		String calendarToCreate =
				"{\"name\":\"" + calendarName + "\",\"year\":" + year + "}";

		String actualCalendar = rest.create(calendarToCreate);


		verify(lao).create(passedCalendar);


		String expected = "{id:\"" + id + "\",name:\""
				+ calendarName + "\",year:" + year + "}";

		JSONAssert.assertEquals(expected, actualCalendar, false);
	}

	@Test
	public void createNullName() throws JSONException {
		int year = 2004;

		String calendarToCreate = "{\"year\":" + year + "}";

		try {
			rest.create(calendarToCreate);
			fail();
		} catch (WebApplicationException e) {
			int expectedStatus = 400;
			int actualStatus = e.getResponse().getStatus();
			assertEquals(actualStatus, expectedStatus);
		}
	}

	@Test
	public void delete() throws JSONException {
		String id = "foo";

		rest.delete(id);

		verify(lao).delete(id);
	}
}
