package com.ajiranet.networking.enums;

public enum DeviceType {

	COMPUTER("COMPUTER"), REPEATER("REPEATER");

	private String deviceType;
	private DeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
}
