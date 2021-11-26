package com.example.util;

import org.springframework.stereotype.Component;

import com.example.entity.Quote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtil {

	public String toJson(Quote quote) {
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(quote);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}