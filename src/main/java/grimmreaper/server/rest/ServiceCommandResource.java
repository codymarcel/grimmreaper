package grimmreaper.server.rest;

import grimmreaper.server.api.BasicServiceCommand;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


public abstract class ServiceCommandResource{
	final BasicServiceCommand service;

	public ServiceCommandResource(final BasicServiceCommand service) {
		this.service = service;
	}

	@GET
	@Produces("text/plain")
	// TODO Change to POST
	public String runCommand(@QueryParam("command") String command) {
		if(command.equals("start")) {
			return service.start();
		} else if (command.equals("stop")) {
			return service.stop();
		}else if (command.equals("cmd")) {
			return service.stop();
		} else {
			return "Unsupported Command";
		}
	}
	
}