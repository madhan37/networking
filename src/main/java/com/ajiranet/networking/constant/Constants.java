package com.ajiranet.networking.constant;

public interface Constants {
	String DEVICES = "devices";
	String CONNECTIONS = "connections";
	String URI_DEVICE_NAME_KEY = "deviceName";
	String URI_DEVICE_NAME_TEMPLATE = String.format("/devices/{%s}/strength", URI_DEVICE_NAME_KEY);
	String URI_COMMAND_TYPE_KEY = "commandType";
	String URI_CREATE_COMMAND_TEMPLATE = String.format("/{%s}", URI_COMMAND_TYPE_KEY);
}