package com.ivanparraga.bscal.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Module;
import com.google.inject.servlet.GuiceFilter;
import com.ivanparraga.bscal.rest.config.GuiceServletConfig;
import com.ivanparraga.bscal.rest.config.JaxRsApplication;

public class EmbeddedServer {
	private static Logger logger = LoggerFactory
			.getLogger(EmbeddedServer.class);

	private static final String JERSEY_SERVLET_APPLICATION_INIT_PARAM_NAME
			= "javax.ws.rs.Application";

	private static final String JERSEY_APPLICATION_INIT_PARAM_VALUE
			= JaxRsApplication.class.getName();

	public static final int PORT = 9999;
	public static final String CONTEXT = "/";

	private Server server;
	private ServletContextHandler context;
	private final Module guiceModule;

	public EmbeddedServer(Module guiceModule) {
		this.guiceModule = guiceModule;
	}

	public void start() throws Exception {
		server = new Server(PORT);
		context = new ServletContextHandler(server, CONTEXT);

		initializeGuice();
		initializeJersey();

		server.start();
		logger.debug("Server started");
	}

	private void initializeGuice() {
		context.addEventListener(new GuiceServletConfig() {
			@Override
			protected Module getModule() {
				return guiceModule;
			}
		});
		context.addFilter(GuiceFilter.class, "/*", null);
	}

	private void initializeJersey() {
		String urlPattern = "/*";
		ServletHolder servletHolder =
				context.addServlet(ServletContainer.class, urlPattern);

		servletHolder.setInitParameter(
				JERSEY_SERVLET_APPLICATION_INIT_PARAM_NAME,
				JERSEY_APPLICATION_INIT_PARAM_VALUE);
	}

	public void stop() throws Exception {
		server.stop();
		logger.debug("Server stopped");
	}
}
