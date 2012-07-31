package grimmreaper.server.api.impl;

import grimmreaper.server.api.CommandContainer;
import grimmreaper.server.api.DestructiveServiceCommand;

import org.apache.commons.exec.DefaultExecuteResultHandler;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class HBaseCommandImpl extends AbstractCommandImpl implements
		DestructiveServiceCommand {
	
	@Inject
	public HBaseCommandImpl(@Named("JAVA_HOME") final String JAVA_HOME, @Named("APP_HOME") final String APP_HOME) {
		super(JAVA_HOME,APP_HOME);
	}

	public String start() {
		String[] args = { "startHBase" };
		CommandContainer command = exec(ANT, args);
		DefaultExecuteResultHandler resultHandler = command.getHandler();

		try {
			resultHandler.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Starting Hbase...";
	}

	public String stop() {
		String[] args = { "stopHBase" };
		CommandContainer command = exec(ANT, args);
		DefaultExecuteResultHandler resultHandler = command.getHandler();

		try {
			resultHandler.waitFor();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Stopping HBase...";
	}

	@Override
	public String suspend() {
		String pid = null;
		String procName = null;
		String status = null;

		// String strCmd = JAVA_HOME + "bin/jps";
		String strCmd = "jps";
		CommandContainer command = execSync(strCmd);

		// parse the pid
		String[] procs = command.getOutputStream().toString().split("\n");
		for (String proc : procs) {
			if (proc.contains("HRegionServer") || proc.contains("HMaster")) {
				String[] procInfo = proc.split(" ");
				pid = procInfo[0];
				procName = procInfo[1];
				break;
			}
		}

		/*
		 * Make call to "kill -STOP 1234" This command will check to see if
		 * process is stopped.
		 * 
		 * ps aux|grep HMaster
		 * 
		 * Statuses: 
		 * 	D - Uninterruptible sleep (usually IO) R - Running or runnable (on run queue) 
		 * 	S - Interruptible sleep (waiting for an event
		 * 		to complete) 
		 * 	T - Stopped, either by a job control signal or because
		 * 		it is being traced. 
		 * 	W - paging (not valid since the 2.6.xx kernel) 
		 * 	X - dead (should never be seen) 
		 * 	Z - Defunct ("zombie") process,
		 * 		terminated but not reaped by its parent.
		 */
		if (pid != null) {
			strCmd = "kill";
			String []args = {"-STOP", pid};
			command = execSync(strCmd, args);
			status = "Proccess Suspended :" + procName + " : " + pid;

		} else {
			status = "Could not find a matching PID";
		}
		return status;
	}

	@Override
	public String resume() {
		String pid = null;
		String procName = null;
		String status = null;

		// String strCmd = JAVA_HOME + "bin/jps";
		String strCmd = "jps";
		CommandContainer command = execSync(strCmd);

		// parse the pid
		String[] procs = command.getOutputStream().toString().split("\n");
		for (String proc : procs) {
			if (proc.contains("HRegionServer") || proc.contains("HMaster")) {
				String[] procInfo = proc.split(" ");
				pid = procInfo[0];
				procName = procInfo[1];
				break;
			}
		}

		if (pid != null) {
			strCmd = "kill";
			String []args = {"-CONT", pid};
			command = execSync(strCmd, args);
			status = "Proccess resumed :" + procName + " : " + pid;

		} else {
			status = "Could not find a matching PID";
		}
		return status;
	}

	@Override
	public String kill() {
		return "Killing Hbase";
	}
}
