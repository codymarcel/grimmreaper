package grimmreaper.server;

import grimmreaper.server.config.GuiceConfig;

import java.util.EnumSet;

import org.eclipse.jetty.server.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.servlet.GuiceFilter;

public class GrimmReaper {
	public static void main(String[] args) throws Exception {

		System.out.println("Don't fear the reaper!");

		// TODO Config me
		int port = 9997;
		Server server = new Server(port);
		ServletContextHandler handler = new ServletContextHandler();
		handler.setContextPath("/");

		handler.addEventListener(new GuiceConfig());
		handler.addFilter(GuiceFilter.class, "/*",
				EnumSet.of(DispatcherType.REQUEST));
		handler.addServlet(DefaultServlet.class, "/*");

		server.setHandler(handler);
		server.start();
		server.join();

	}
}
