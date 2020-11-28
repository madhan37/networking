package com.ajiranet.networking.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface RequestService {

	String handleRequest(String commandText)
			throws JsonMappingException, JsonProcessingException;
}
