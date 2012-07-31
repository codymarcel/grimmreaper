package grimmreaper.server.api;

import java.io.ByteArrayOutputStream;

import org.apache.commons.exec.DefaultExecuteResultHandler;

public class CommandContainer {
	ByteArrayOutputStream stream;
	DefaultExecuteResultHandler handler;
	
	public CommandContainer(DefaultExecuteResultHandler handler, ByteArrayOutputStream stream) {
		this.handler = handler;
		this.stream = stream;
	}
	
	public ByteArrayOutputStream getOutputStream() {
		return stream;
	}

	public DefaultExecuteResultHandler getHandler() {
		return handler;
	}
}
