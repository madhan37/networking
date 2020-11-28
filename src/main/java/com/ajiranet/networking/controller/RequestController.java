package com.ajiranet.networking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajiranet.networking.exception.DeviceNotFoundException;
import com.ajiranet.networking.model.Response;
import com.ajiranet.networking.service.RequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/ajiranet/process")
public class RequestController {

	@Autowired RequestService requestService;

    @PostMapping
    public ResponseEntity<Response> postRequest(@RequestBody String commandText)
    		throws JsonProcessingException {
    	String message = "";
    	HttpStatus status = HttpStatus.OK;
    	try {
    		message = requestService.handleRequest(commandText);
    	} catch (IllegalArgumentException e) {
    		message = e.getMessage();
    		status = HttpStatus.BAD_REQUEST;
    	} catch (DeviceNotFoundException e) {
    		message = e.getMessage();
    		status = HttpStatus.NOT_FOUND;
    	}
    	return ResponseEntity.status(status).body(new Response(message));
    }
}
