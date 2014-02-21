package com.ivanparraga.bscal.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.servlet.GuiceFilter;
import com.ivanparraga.bscal.rest.config.GuiceServletConfig;

public class EmbeddedServer {
	private static Logger logger = LoggerFactory.getLogger(EmbeddedServer.class);

	public static final int PORT = 999;

	private Server server;

	public void start() throws Exception {
		// Create the server.
		server = new Server(PORT);

		// Create a servlet context and add the jersey servlet.
		ServletContextHandler sch = new ServletContextHandler(server, "/");

		// Add our Guice listener that includes our bindings
		sch.addEventListener(new GuiceServletConfig());

		// Then add GuiceFilter and configure the server to
		// reroute all requests through this filter.
		sch.addFilter(GuiceFilter.class, "/*", null);

		// Must add DefaultServlet for embedded Jetty.
		// Failing to do this will cause 404 errors.
		// This is not needed if web.xml is used instead.
		sch.addServlet(DefaultServlet.class, "/");

		// Start the server
		server.start();

		logger.debug("Server started");
	}

	public void stop() throws Exception {
		server.stop();

		logger.debug("Server stopped");
	}
}
