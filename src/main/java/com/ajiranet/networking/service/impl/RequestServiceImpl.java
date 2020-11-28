package com.ajiranet.networking.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

import com.ajiranet.networking.constant.Constants;
import com.ajiranet.networking.constant.MessageConstants;
import com.ajiranet.networking.enums.CommandType;
import com.ajiranet.networking.model.Device;
import com.ajiranet.networking.model.Request;
import com.ajiranet.networking.service.DeviceService;
import com.ajiranet.networking.service.RequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RequestServiceImpl implements RequestService {

	@Autowired DeviceService deviceService;

	public String handleRequest(String commandText)
			throws JsonMappingException, JsonProcessingException {
		Request request = convertToRequest(commandText);
		return processRequest(request);
	}

	String processRequest(Request request) {
		String message = ""; 
		switch(request.getCommandType()) {
		case CREATE:
			UriTemplate temp = new UriTemplate(Constants.URI_CREATE_COMMAND_TEMPLATE) ;
			Map<String, String> values = temp.match(request.getCommand());
			if (Constants.DEVICES.equals(values.get(Constants.URI_COMMAND_TYPE_KEY))) {
				Device device = deviceService.createDevice(request.getBody());
				message = String.format(MessageConstants.DEVICE_CREATION_SUCCESS, device.getName());
			} else if (Constants.CONNECTIONS.equals(values.get(Constants.URI_COMMAND_TYPE_KEY))) {
				deviceService.createConnection((String)request.getBody().get("source"),
						(List<String>)request.getBody().get("targets"));
				message = MessageConstants.CONNECTION_SUCCESSFUL;
			}
			break;
		case MODIFY:
			temp = new UriTemplate(Constants.URI_DEVICE_NAME_TEMPLATE) ;
			values = temp.match(request.getCommand());
			deviceService.updateStrength(values.get(Constants.URI_DEVICE_NAME_KEY),
					request.getBody().get("value"));
			message = MessageConstants.DEVICE_STRENGTH_MODIFY_SUCCESS;
			break;
		case FETCH:
			temp = new UriTemplate("/{deviceName}?from=A4&to=A6");
			values = temp.match(request.getCommand());
			break;
		default:
			//Invalid Command Request
		}
		return message;
	}

	private Request convertToRequest(String commandText)
			throws JsonProcessingException {

		try {
			commandText = java.net.URLDecoder.decode(commandText, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner s = new Scanner(commandText);
		Request request = new Request(); 
		request.setCommandType(CommandType.valueOf(s.next()));
		request.setCommand(s.nextLine());
		s.useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));
		s.next();
        s.useDelimiter(Pattern.compile("$", Pattern.MULTILINE));
        if (s.hasNext()) {
        	Map<String, Object> body = parseJSONString(s.next());
            request.setBody(body);
        } else {
        	throw new IllegalArgumentException(MessageConstants.INVALID_COMMAND);
        }
        return request;
	}

	Map<String, Object> parseJSONString(String jsonString) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonString, Map.class);
	}
}
