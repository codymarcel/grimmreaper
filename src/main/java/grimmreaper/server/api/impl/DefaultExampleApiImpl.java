package grimmreaper.server.api.impl;

import grimmreaper.server.api.ExampleApi;

public class DefaultExampleApiImpl implements ExampleApi{

	public String get() {
		return DefaultExampleApiImpl.class.getName();
	}
}
