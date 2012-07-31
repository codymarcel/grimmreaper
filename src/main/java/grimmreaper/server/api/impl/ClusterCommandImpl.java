package grimmreaper.server.api.impl;


import grimmreaper.server.api.CommandContainer;

import org.apache.commons.exec.DefaultExecuteResultHandler;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ClusterCommandImpl extends AbstractCommandImpl{
	@Inject
	public ClusterCommandImpl(@Named("JAVA_HOME") String JAVA_HOME, @Named("APP_HOME") String APP_HOME) {
		super(JAVA_HOME,APP_HOME);
	}

	public String start() {
		String []args = {"start"};
		CommandContainer command = exec(ANT, args);
		DefaultExecuteResultHandler resultHandler = command.getHandler();
		
		try {
			resultHandler.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Starting Cluster...";
	}
	
	public String stop() {
		String []args = {"stop"};
		CommandContainer command = exec(ANT, args);
		DefaultExecuteResultHandler resultHandler = command.getHandler();
		
		try {
			resultHandler.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Stopping Cluster...";		
	}
}
