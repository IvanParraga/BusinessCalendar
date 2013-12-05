package com.ivanparraga.bscal.core.calendar;

import com.ivanparraga.bscal.core.domain.Calendar;

public class BasicCalendar implements Calendar {
	private final String name;
	private final short year;
	private String id;

	public BasicCalendar(String name, short year) {
		super();
		this.name = name;
		this.year = year;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public short getYear() {
		return year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicCalendar other = (BasicCalendar) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
}
