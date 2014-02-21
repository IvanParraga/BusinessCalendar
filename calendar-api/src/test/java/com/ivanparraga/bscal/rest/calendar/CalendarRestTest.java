package com.ivanparraga.bscal.rest.calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.ivanparraga.bscal.core.NoSuchObjectException;
import com.ivanparraga.bscal.core.calendar.BasicCalendar;
import com.ivanparraga.bscal.core.calendar.CalendarLao;
import com.ivanparraga.bscal.core.domain.Calendar;
import com.ivanparraga.bscal.rest.EmbeddedServer;

public class CalendarRestTest {
	private static final Logger logger =
			LoggerFactory.getLogger(CalendarRestTest.class);

	private static EmbeddedServer server;

	private CalendarLao lao;
	private CalendarRest rest;

	public void startServer(CalendarLao lao) throws Exception {
		Module module = getModule(lao);
		server = new EmbeddedServer(module);
		server.start();
	}

	@After
	public void stopServer() throws Exception {
		if (server != null) {
			server.stop();
		}
	}

	@Before
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

	@Test(expected = NotFoundException.class)
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
	public void createNullName() throws Exception {
		startServer(mock(CalendarLao.class));
		int year = 2004;

		String calendarToCreate = "{\"year\":" + year + "}";
		Entity<String> userEntity = Entity.entity(calendarToCreate, MediaType.APPLICATION_JSON);

		String url = "http://localhost:" + EmbeddedServer.PORT + "/calendar";

		Response response =
				ClientBuilder.newClient()
				.target(url)
				.request()
				.post(userEntity);

		logger.debug("Response: " + response);

		assertCode(400, response);
		assertMessage("Missing required property name", response);
	}

	@Test
	public void createBadFormatted() throws Exception {
		startServer(mock(CalendarLao.class));

		String calendarToCreate = "a";
		Entity<String> userEntity = Entity.entity(
				calendarToCreate, MediaType.APPLICATION_JSON);

		String url = "http://localhost:" + EmbeddedServer.PORT + "/calendar";

		Response response =
				ClientBuilder.newClient()
				.target(url)
				.request()
				.post(userEntity);

		logger.debug("Response: " + response);

		assertCode(500, response);
		assertMessage("Internal server error", response);
	}

	private void assertCode(int expectedStatus, Response response) {
		int actualStatus = response.getStatus();
		assertEquals(expectedStatus, actualStatus);
	}

	private void assertMessage(String expectedMessage, Response response) {
		String actualMessage = response.readEntity(String.class);
		assertEquals(expectedMessage, actualMessage);
	}

	private Module getModule(final CalendarLao lao) {
		return new AbstractModule() {
			@Override
			protected void configure() {
				bind(CalendarLao.class).toInstance(lao);
			}
		};
	}

	@Test
	public void delete() throws JSONException {
		String id = "foo";

		rest.delete(id);

		verify(lao).delete(id);
	}
}
