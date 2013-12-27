package com.ivanparraga.bscal.core.calendar;

import com.ivanparraga.bscal.core.domain.Calendar;

public class BasicCalendar implements Calendar {
	private String id;
	private final String name;
	private final int year;

	public BasicCalendar(String name, int year) {
		this.name = name;
		this.year = year;
	}

	public BasicCalendar(String id, String name, int year) {
		this(name, year);
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public BasicCalendar newClon() {
		return new BasicCalendar(id, name, year);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasicCalendar [id=" + id + ", name=" + name + ", year=" + year
				+ "]";
	}
}
