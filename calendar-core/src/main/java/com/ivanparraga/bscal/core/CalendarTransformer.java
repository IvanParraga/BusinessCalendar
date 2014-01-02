package com.ivanparraga.bscal.core;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import com.ivanparraga.bscal.core.calendar.BasicCalendar;
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
			Map<String,Object> map = mapper.readValue(calendar, Map.class);
			return new BasicCalendar((String)map.get("name"), (int)map.get("year"));
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
