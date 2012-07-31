package grimmreaper.server.api.impl;

import grimmreaper.server.api.BasicServiceCommand;

public class DataNodeCommandImpl implements BasicServiceCommand{

	public String start() {
		return "Starting DataNode...";
	}
	
	public String stop() {
		return "Stopping DataNode...";		
	}
}
