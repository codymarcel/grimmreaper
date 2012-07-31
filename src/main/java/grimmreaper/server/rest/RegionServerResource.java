package grimmreaper.server.rest;

import grimmreaper.server.api.BasicServiceCommand;

import javax.ws.rs.Path;
 
import com.google.inject.Inject;
import com.google.inject.name.Named;

@Path("/regionserver")
public class RegionServerResource extends ServiceCommandResource {

	@Inject
	public RegionServerResource(@Named("RegionServer") BasicServiceCommand service) {
		super(service);
	}
}
