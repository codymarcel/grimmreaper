package grimmreaper.server.rest;

import grimmreaper.server.api.BasicServiceCommand;

import javax.ws.rs.Path;
 
import com.google.inject.Inject;
import com.google.inject.name.Named;

@Path("/datanode")
public class DataNodeResource extends ServiceCommandResource {

	@Inject
	public DataNodeResource(@Named("DataNode") BasicServiceCommand service) {
		super(service);
	}
}
