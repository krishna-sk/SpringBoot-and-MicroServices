package com.example.util;

import org.springframework.stereotype.Component;

import com.example.entity.Quote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtil {

	public Quote fromJson(String json) {
		Quote quote = null;
		try {
			quote = new ObjectMapper().readValue(json, Quote.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return quote;
	}
}