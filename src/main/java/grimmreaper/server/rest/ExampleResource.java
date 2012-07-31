package grimmreaper.server.rest;

import grimmreaper.server.api.ExampleApi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
 
import com.google.inject.Inject;

@Path("/example")
public class ExampleResource {
	private final ExampleApi handler;
	
	@Inject
	public ExampleResource(final ExampleApi handler) {
		this.handler = handler;
		System.out.println("C1");
	}
	
	@GET
	@Produces("text/plain")
	public String echo(@QueryParam("x") String x) {
		return "Command=" + handler.toString() + " :: Param=" + (x == null ? "" : x);
	}
}
