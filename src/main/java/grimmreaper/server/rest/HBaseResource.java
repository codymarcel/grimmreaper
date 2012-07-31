package grimmreaper.server.rest;

import grimmreaper.server.api.DestructiveServiceCommand;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.inject.Inject;
import com.google.inject.name.Named;

@Path("/hbase")
public class HBaseResource extends ServiceCommandResource {

	@Inject
	public HBaseResource(@Named("HBase") final DestructiveServiceCommand service) {
		super(service);
	}

	@GET
	@Produces("text/plain")
	@Override
	public String runCommand(@QueryParam("command") String command) {
		if (command.equals("suspend")) {
			return ((DestructiveServiceCommand)service).suspend();
		} else if (command.equals("resume")) {
			return ((DestructiveServiceCommand)service).resume();
		} else {
			return super.runCommand(command);
		}
	}
}
