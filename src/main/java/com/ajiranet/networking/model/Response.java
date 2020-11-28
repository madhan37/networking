package com.ajiranet.networking.model;

public class Response {
	String msg = "";

	public Response(String message) {
		this.msg = message;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
