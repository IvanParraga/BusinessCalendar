package com.ivanparraga.bscal.rest.calendar;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ivanparraga.bscal.core.JsonMissingRequiredPropertyException;

@Provider
public class JsonMissingRequiredPropertyExceptionMapper implements
		ExceptionMapper<JsonMissingRequiredPropertyException> {

	@Override
	public Response toResponse(JsonMissingRequiredPropertyException exception) {
		return Response.status(400)
				.entity(exception.getMessage())
				.type("text/plain")
				.build();
	}
}
