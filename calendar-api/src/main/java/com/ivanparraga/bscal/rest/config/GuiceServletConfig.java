package com.ivanparraga.bscal.rest.config;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;
import com.ivanparraga.bscal.core.calendar.CalendarGuiceModule;
import com.ivanparraga.bscal.rest.calendar.CalendarRest;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		JerseyServletModule servletModule = new JerseyServletModule() {
			@Override
			protected void configureServlets() {
				bind(CalendarRest.class);
				install(new CalendarGuiceModule());

				bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);

				// Route all requests through GuiceContainer
				serve("/*").with(GuiceContainer.class);
			}
		};

		return  Guice.createInjector(servletModule);
	}

}
