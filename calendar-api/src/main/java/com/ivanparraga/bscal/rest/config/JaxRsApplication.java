package com.ivanparraga.bscal.rest.config;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import com.ivanparraga.bscal.rest.calendar.CalendarRest;

public class JaxRsApplication extends ResourceConfig {
	@Inject
	public JaxRsApplication(ServiceLocator serviceLocator) {
		packages(CalendarRest.class.getPackage().getName());
		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

		GuiceIntoHK2Bridge guiceBridge = serviceLocator
				.getService(GuiceIntoHK2Bridge.class);
		guiceBridge.bridgeGuiceInjector(GuiceServletConfig.injector);
	}
}
