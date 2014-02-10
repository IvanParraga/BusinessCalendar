package com.ivanparraga.bscal.rest.calendar;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.ivanparraga.bscal.core.JsonMissingRequiredPropertyException;
import com.ivanparraga.bscal.core.NoSuchObjectException;
import com.ivanparraga.bscal.core.calendar.CalendarLao;
import com.ivanparraga.bscal.core.calendar.CalendarTransformer;
import com.ivanparraga.bscal.core.domain.Calendar;
import com.sun.jersey.api.Responses;

@Path("calendar")
public class CalendarRest {
	private final CalendarLao lao;
	private final CalendarTransformer transformer;

	@Inject
	public CalendarRest(CalendarLao lao) {
		this.lao = lao;
		transformer = new CalendarTransformer();
	}

	/**
	 * @return The requested Calendar as JSon
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String read(@PathParam("id") String id) {
		try {
			Calendar calendar = lao.read(id);
			return transformer.serialize(calendar);
		} catch (NoSuchObjectException e) {
			throw new NotFoundException("No calendar with id \"" + id + "\"");
		}
	}


	/**
	 * Creates the new Calendar.
	 * @return The just created calendar (that can include more fields that
	 * the calendar sent).
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String create(String calendarToCreate) {
		Calendar calendar = getFromRequest(calendarToCreate);
		Calendar newCalendar = lao.create(calendar);
		return transformer.serialize(newCalendar);
	}

	/**
	 * @return An array of all the JSon calendars for which the user has access
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String read() {
		Set<Calendar> calendars = lao.read();
		return transformer.serialize(calendars);
	}

	/**
	 * @return The requested Calendar as JSon
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") String id) {
		lao.delete(id);
	}

	private Calendar getFromRequest(String calendar) {
		try {
			return transformer.deserialize(calendar);
		} catch (JsonMissingRequiredPropertyException e) {
			throw new WebApplicationException(Responses
					.clientError()
					.entity(e.getMessage())
					.build());
		}
	}
}
