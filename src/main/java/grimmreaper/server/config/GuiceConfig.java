package grimmreaper.server.config;

import grimmreaper.server.api.BasicServiceCommand;
import grimmreaper.server.api.DestructiveServiceCommand;
import grimmreaper.server.api.impl.ClusterCommandImpl;
import grimmreaper.server.api.impl.DataNodeCommandImpl;
import grimmreaper.server.api.impl.HBaseCommandImpl;
import grimmreaper.server.api.impl.HadoopCommandImpl;
import grimmreaper.server.api.impl.RegionServerCommandImpl;
import grimmreaper.server.rest.ClusterResource;
import grimmreaper.server.rest.DataNodeResource;
import grimmreaper.server.rest.HBaseResource;
import grimmreaper.server.rest.HadoopResource;
import grimmreaper.server.rest.RegionServerResource;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceConfig extends GuiceServletContextListener {

	/**
	 * Loads a properties file from disk
	 * 
	 * @param fileName
	 *            - File must exist on the classpath
	 * @return Properties object
	 */
	public Properties getProperties(String fileName) {
		Properties props = new Properties();
		ClassLoader loader = GuiceConfig.class.getClassLoader();
		URL url = loader.getResource(fileName);
		try {
			props.load(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new JerseyServletModule() {

			@Override
			protected void configureServlets() {

				// Bind properties from defaults
				Properties props = getProperties("default.properties");
				Names.bindProperties(binder(), props);

				// Bind the resources classes
				bind(HBaseResource.class);
				bind(HadoopResource.class);
				bind(ClusterResource.class);
				bind(DataNodeResource.class);
				bind(RegionServerResource.class);
				
				// Bind REST implementations to the Resources 
				bind(DestructiveServiceCommand.class).annotatedWith(
						Names.named("HBase")).to(HBaseCommandImpl.class);
				bind(BasicServiceCommand.class).annotatedWith(
						Names.named("Hadoop")).to(HadoopCommandImpl.class);
				bind(BasicServiceCommand.class).annotatedWith(
						Names.named("RegionServer")).to(
						RegionServerCommandImpl.class);
				bind(BasicServiceCommand.class).annotatedWith(
						Names.named("DataNode")).to(DataNodeCommandImpl.class);
				bind(BasicServiceCommand.class).annotatedWith(
						Names.named("Cluster")).to(ClusterCommandImpl.class);

				serve("/*").with(GuiceContainer.class);
			}
		});

	}
}
