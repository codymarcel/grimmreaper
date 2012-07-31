package grimmreaper.server.rest;

import grimmreaper.server.api.BasicServiceCommand;

import javax.ws.rs.Path;
 
import com.google.inject.Inject;
import com.google.inject.name.Named;

@Path("/cluster")
public class ClusterResource extends ServiceCommandResource {

	@Inject
	public ClusterResource(@Named("Cluster") BasicServiceCommand service) {
		super(service);
	}
}
