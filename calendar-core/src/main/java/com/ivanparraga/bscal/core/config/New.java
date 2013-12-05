package com.ivanparraga.bscal.core.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ivanparraga.bscal.core.calendar.CalendarGuiceModule;

public class New {
	private static Injector injector;

	public static <T> T getInstance(Class<T> type) {
		if (injector == null) {
			loadInjector();
		}

		return injector.getInstance(type);
	}

	private static void loadInjector() {
		CalendarGuiceModule calendarModule = new CalendarGuiceModule();
		injector = Guice.createInjector(calendarModule);
	}
}
