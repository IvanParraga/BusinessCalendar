package com.ivanparraga.bscal.core.calendar;

public class BasicCalendar implements Calendar {
	private final String name;
	private final short year;

	public BasicCalendar(String name, short year) {
		super();
		this.name = name;
		this.year = year;
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
