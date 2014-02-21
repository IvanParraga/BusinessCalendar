package com.ivanparraga.bscal.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.servlet.GuiceFilter;
import com.ivanparraga.bscal.rest.config.GuiceServletConfig;

public class EmbeddedServer {
	private static Logger logger = LoggerFactory
			.getLogger(EmbeddedServer.class);

	private static final String JERSEY_SERVLET_APPLICATION_INIT_PARAM = "javax.ws.rs.Application";

	public static final int PORT = 9999;
	private Server server;
	private ServletContextHandler context;

	public void start() throws Exception {
		// Create the server.
		server = new Server(PORT);

		// Create a servlet context and add the jersey servlet.
		context = new ServletContextHandler(server, "/");

		initializeGuice();
		initializeJersey();

		// Must add DefaultServlet for embedded Jetty.
		// Failing to do this will cause 404 errors.
		// This is not needed if web.xml is used instead.
		context.addServlet(DefaultServlet.class, "/");

		// Start the server
		server.start();

		logger.debug("Server started");
	}

	private void initializeGuice() {
		context.addEventListener(new GuiceServletConfig());
		context.addFilter(GuiceFilter.class, "/*", null);
	}

	private void initializeJersey() {
		// Initialize and register Jersey ServletContainer
		ServletHolder servletHolder =
				context.addServlet(ServletContainer.class, "/*");

		servletHolder.setInitParameter(JERSEY_SERVLET_APPLICATION_INIT_PARAM,
				"example.jersey.MyApplication");
	}

	public void stop() throws Exception {
		server.stop();
		logger.debug("Server stopped");
	}
}
