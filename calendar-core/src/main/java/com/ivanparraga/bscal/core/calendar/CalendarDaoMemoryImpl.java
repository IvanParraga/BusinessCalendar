package com.ivanparraga.bscal.core.calendar;

import javax.inject.Singleton;

import com.ivanparraga.bscal.core.MemoryDao;
import com.ivanparraga.bscal.core.domain.Calendar;

@Singleton
class CalendarDaoMemoryImpl extends MemoryDao<Calendar> implements CalendarDao {
}
