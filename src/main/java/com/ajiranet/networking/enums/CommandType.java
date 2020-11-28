package com.ajiranet.networking.enums;

public enum CommandType {
	CREATE("CREATE"), FETCH("FETCH"), MODIFY("MODIFY");
    private String commandType;

    private CommandType(String commandType) {
    	this.commandType = commandType;
    }
}