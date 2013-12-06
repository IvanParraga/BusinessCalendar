package com.ivanparraga.bscal.rest.calendar;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ivanparraga.bscal.core.calendar.BasicCalendar;
import com.ivanparraga.bscal.core.calendar.CalendarLao;
import com.ivanparraga.bscal.core.domain.Calendar;

@Path("calendar")
public class CalendarRest {
	private final CalendarLao lao;

	@Inject
	public CalendarRest(CalendarLao lao) {
		this.lao = lao;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Calendar read() {
		Calendar c = new BasicCalendar("test", (short)2004);
		lao.create(c);
		return c;
	}
}
