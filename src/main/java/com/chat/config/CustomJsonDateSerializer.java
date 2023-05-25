package com.chat.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.chat.constant.AppConstant;

/**
 * 
 * @author manish
 *
 */
public class CustomJsonDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		if (value != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstant.CUSTOM_DATE_AND_TIME_SERIALIZE_DATE_FORMAT);
			String dateString = dateFormat.format(value);
			jgen.writeString(dateString);
		}
	}
}