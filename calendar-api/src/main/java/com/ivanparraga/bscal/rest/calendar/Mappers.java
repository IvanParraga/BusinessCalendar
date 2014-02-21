package com.ivanparraga.bscal.rest.calendar;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ivanparraga.bscal.core.JsonMissingRequiredPropertyException;

public class Mappers {
	@Provider
	public static class GenericExceptionMapper implements
			ExceptionMapper<Exception> {
		private static final String ERROR_MESSAGE = "Internal server error";

		@Override
		public Response toResponse(Exception exception) {
			return Response.status(500).entity(ERROR_MESSAGE).type("text/plain").build();
		}
	}

	@Provider
	public static class JsonMissingRequiredPropertyExceptionMapper implements
			ExceptionMapper<JsonMissingRequiredPropertyException> {

		@Override
		public Response toResponse(
				JsonMissingRequiredPropertyException exception) {
			return Response.status(400).entity(exception.getMessage())
					.type("text/plain").build();
		}
	}
}
