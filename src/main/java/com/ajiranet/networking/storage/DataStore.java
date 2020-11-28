package com.ajiranet.networking.storage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ajiranet.networking.model.Device;

public class DataStore {

	public static Set<Device> devices = new HashSet<Device> ();
	public static Map<Device, Set<Device>> connections = new HashMap<Device, Set<Device>> (); 
}
