package grimmreaper.server.rest;

import grimmreaper.server.api.BasicServiceCommand;

import javax.ws.rs.Path;
 
import com.google.inject.Inject;
import com.google.inject.name.Named;

@Path("/hadoop")
public class HadoopResource extends ServiceCommandResource {

	@Inject
	public HadoopResource(@Named("Hadoop") BasicServiceCommand service) {
		super(service);
	}
}
