package grimmreaper.server.api.impl;

import grimmreaper.server.api.CommandContainer;

import org.apache.commons.exec.DefaultExecuteResultHandler;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class HadoopCommandImpl extends AbstractCommandImpl{
	@Inject
	public HadoopCommandImpl(@Named("JAVA_HOME") String JAVA_HOME, @Named("APP_HOME") String APP_HOME) {
		super(JAVA_HOME,APP_HOME);
	}

	public String start() {
		String []args = {"startHadoop"};
		CommandContainer command = exec(ANT, args);
		DefaultExecuteResultHandler resultHandler = command.getHandler();
		
		try {
			resultHandler.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Starting Hadoop...";
	}
	
	public String stop() {
		String []args = {"stopHadoop"};
		CommandContainer command = exec(ANT, args);
		DefaultExecuteResultHandler resultHandler = command.getHandler();
		
		try {
			resultHandler.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Stopping Hadoop...";		
	}
}
