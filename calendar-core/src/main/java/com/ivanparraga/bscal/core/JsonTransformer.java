package com.ivanparraga.bscal.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanparraga.bscal.core.domain.Entity;

public abstract class JsonTransformer<T extends Entity<?>> {
	protected final ObjectMapper mapper;

	public JsonTransformer() {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}


	public abstract String serialize(T toSerialize);

	public abstract T deserialize(String json);
}