package com.ivanparraga.bscal.core.domain;

public interface Entity<T> {
	String getId();
	void setId(String id);
	T newClon();
}
