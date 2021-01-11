package com.app.util;

import com.app.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
	public static String convertObjctToJsonFormat(Object object) throws JsonProcessingException {
		String response = "";
		ObjectMapper objectMapper = new ObjectMapper();
		if (object instanceof User) {
			User user = (User) object;
			response = objectMapper.writeValueAsString(user);
		}
		return response;
	}
}
