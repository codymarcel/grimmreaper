package grimmreaper.server.api.impl;

import grimmreaper.server.api.BasicServiceCommand;

public class RegionServerCommandImpl implements BasicServiceCommand{

	public String start() {
		return "Starting RegionServer...";
	}
	
	public String stop() {
		return "Stopping RegionServer...";		
	}
	
	public String cmd() {
		return "bla";
	}
}
