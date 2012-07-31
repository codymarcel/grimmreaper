package grimmreaper.server.api;

public interface DestructiveServiceCommand extends BasicServiceCommand{
	String suspend();
	String resume();
	String kill();
}
