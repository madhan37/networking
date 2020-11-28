package com.ajiranet.networking.service;

import java.util.List;
import java.util.Map;

import com.ajiranet.networking.model.Device;

public interface DeviceService {

	Device createDevice(Map<String, Object> deviceInformation);

	Device updateStrength(String name, Object strength);

	List<String> createConnection(String source, List<String> targets);
}