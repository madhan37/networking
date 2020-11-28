package com.ajiranet.networking.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.ajiranet.networking.constant.MessageConstants;
import com.ajiranet.networking.exception.DeviceNotFoundException;
import com.ajiranet.networking.model.Device;
import com.ajiranet.networking.service.DeviceService;
import com.ajiranet.networking.storage.DataStore;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DeviceServiceImpl implements DeviceService {

	public Device createDevice(Map<String, Object> deviceInformation) {
		ObjectMapper mapper = new ObjectMapper();
        Device device = null;
		try {
			device = mapper.convertValue(deviceInformation, Device.class);
		} catch (IllegalArgumentException e) {
			throw e;
		}
		if (!DataStore.devices.add(device)) {
			throw new IllegalArgumentException(String.format(
					MessageConstants.DEVICE_ALREADY_EXISTS, device.getName()));
		}
//        for (Device deviceInfo : DataStore.devices) {
//        	System.out.println(deviceInfo.getName());
//        }
        return device;
	}

	public Device updateStrength(String name, Object value) {
		Device device = find(name);
		if (null != device) {
			try {
				int strength = (Integer) value;
				device.setStrength(strength);
			} catch (ClassCastException e) {
				throw new IllegalArgumentException(MessageConstants.INTEGER_VALUE_ERROR);
			}
		} else {
			throw new DeviceNotFoundException(MessageConstants.DEVICE_404);
		}
		return device;
	}

	public List<String> createConnection(String source, List<String> targets) {
		List<String> mappedTargets = new ArrayList<String>();
		Device sourceDevice = find(source);
		if (null == sourceDevice) {
			throw new DeviceNotFoundException(String.format(MessageConstants.NODE_NOT_FOUND, source));
		}
		Set<Device> targetDevices = DataStore.connections.get(sourceDevice);
		if (null == targetDevices) {
			targetDevices = new HashSet<Device>();
			DataStore.connections.put(sourceDevice, targetDevices);
		}
		for (String target : targets) {
			Device targetDevice = find(target);
			if (sourceDevice.equals(targetDevice)) {
				throw new IllegalArgumentException(MessageConstants.DEVICE_CONNECTION_TO_SELF_ERROR);
			}
			if (targetDevices.contains(targetDevice)) {
				throw new IllegalArgumentException(MessageConstants.DEVICES_ALREADY_CONNECTED);
			}
			if (null != targetDevice) {
				targetDevices.add(targetDevice);
			}
		}
		System.out.println(DataStore.connections);
		return mappedTargets;
	}

	private Device find(String deviceName) {
		Device device = null;
		for (Device deviceInfo : DataStore.devices) {
			if (null != deviceName && deviceName.equals(deviceInfo.getName())) {
				device = deviceInfo;
				break;
			}
		}
		return device;
	}
}
