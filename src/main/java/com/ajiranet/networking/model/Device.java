package com.ajiranet.networking.model;

import com.ajiranet.networking.constant.MessageConstants;
import com.ajiranet.networking.enums.DeviceType;

public class Device {

	private DeviceType type;
	private String name = "";
	private int strength;

	public DeviceType getType() {
		return type;
	}

	public void setType(String type) {
		try {
			this.type = DeviceType.valueOf(type);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format(
					MessageConstants.DEVICE_TYPE_NOT_SUPPORTED, type));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	@Override
	public boolean equals(Object device) {
		return (null != this.name && null != device 
				    && this.name.equals(((Device)device).getName()));
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public String toString() {
		return String.format("[name=%s, type=%s, strength=%d]", name, type, strength);
	}
}
