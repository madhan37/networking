package com.ajiranet.networking.model;

import java.util.HashMap;
import java.util.Map;

import com.ajiranet.networking.enums.CommandType;

public class Request {

	private CommandType commandType;
	private String command;
	private Map<String, String> headers = new HashMap<String, String> ();
	private Map<String, Object> body = new HashMap<String, Object> ();

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}
}
