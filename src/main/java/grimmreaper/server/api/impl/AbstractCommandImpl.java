package grimmreaper.server.api.impl;

import grimmreaper.server.api.BasicServiceCommand;
import grimmreaper.server.api.CommandContainer;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public abstract class AbstractCommandImpl  implements BasicServiceCommand{

	protected String JAVA_HOME = null;
	protected String APP_HOME = null;
	protected String ANT = null;
	
	// TODO Fix this shite
	@Inject
	public AbstractCommandImpl(@Named("JAVA_HOME") final String JAVA_HOME, @Named("APP_HOME") final String APP_HOME) {
		this.JAVA_HOME = JAVA_HOME;
		this.APP_HOME = APP_HOME;
		this.ANT = APP_HOME + "build/ant";
	}

	@Override
	public abstract String start();

	@Override
	public abstract String stop();

	protected CommandContainer execSync(String cmd) {
		return execSync(cmd, null);
	}
	
	protected CommandContainer execSync(String cmd, String []args) {
		CommandContainer command = exec(cmd, args);
		DefaultExecuteResultHandler resultHandler = command.getHandler();
		
		try {
			resultHandler.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return command;
	}
	
	protected CommandContainer exec(String cmd) {
		return exec(cmd, null);
	}
	
	protected CommandContainer exec(String cmd, String []args) {
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		CommandLine cmdLine = new CommandLine(cmd);

		if(args != null) {
			for(String arg : args) {
				cmdLine.addArgument(arg);
			}
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PumpStreamHandler stream = new PumpStreamHandler(out);
		
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		ExecuteWatchdog watchDog = new ExecuteWatchdog(120000);
		executor.setWatchdog(watchDog);
		executor.setStreamHandler(stream);
		
		try {
			executor.execute(cmdLine, resultHandler);
		} catch (ExecuteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return new CommandContainer(resultHandler, out);
	}

}