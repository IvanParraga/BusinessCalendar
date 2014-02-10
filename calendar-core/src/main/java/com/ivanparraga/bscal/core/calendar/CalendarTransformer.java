package com.ivanparraga.bscal.core.calendar;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import com.ivanparraga.bscal.core.JsonException;
import com.ivanparraga.bscal.core.JsonTransformer;
import com.ivanparraga.bscal.core.domain.Calendar;

public class CalendarTransformer extends JsonTransformer<Calendar> {
	@Override
	public String serialize(Calendar calendar) {
		try {
			return mapper.writeValueAsString(calendar);
		} catch (IOException e) {
			throw new JsonException(
				"I couldn't convert " + calendar + " to JSon", e);
		}
	}

	@Override
	public Calendar deserialize(String calendar) {
		try {
			@SuppressWarnings("unchecked")
			Map<String,Object> properties = mapper.readValue(calendar, Map.class);
			String name = getRequiredProperty(String.class, properties, "name");
			int year = getRequiredProperty(Integer.class, properties, "year");
			return new BasicCalendar(name, year);
		} catch (IOException e) {
			throw new JsonException(
				"I cannot deserialize \"" + calendar + "\"", e);
		}
	}

	public String serialize(Set<Calendar> calendars) {
		try {
			return mapper.writeValueAsString(calendars);
		} catch (IOException e) {
			throw new JsonException(
				"I couldn't convert "
				+ Arrays.toString(calendars.toArray()) + " to JSon", e);
		}
	}
}
