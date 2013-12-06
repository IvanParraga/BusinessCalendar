package com.ivanparraga.bscal.rest.calendar;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ivanparraga.bscal.core.calendar.BasicCalendar;
import com.ivanparraga.bscal.core.domain.Calendar;

@Path("calendar")
public class CalendarRest {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Calendar read() {
		return new BasicCalendar("test", (short)2004);
	}
}
