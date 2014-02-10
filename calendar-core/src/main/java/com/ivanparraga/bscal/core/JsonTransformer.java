package com.ivanparraga.bscal.core;

import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanparraga.bscal.core.domain.Entity;

public abstract class JsonTransformer<T extends Entity<?>> {
	protected final ObjectMapper mapper;

	public JsonTransformer() {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@SuppressWarnings("unchecked")
	protected <X> X getRequiredProperty(
			Class<X> clazz, Map<String, Object> properties, String propertyName) {
		X property = null;
		try {
			property = (X)properties.get(propertyName);
		} catch (Exception e) {
			throw new JsonException(
				"Problem reading required property \"" + propertyName + "\"");
		}

		if (property == null) {
			throw new JsonMissingRequiredPropertyException(propertyName);
		}
		return property;
	}

	public abstract String serialize(T toSerialize);

	public abstract T deserialize(String json);
}