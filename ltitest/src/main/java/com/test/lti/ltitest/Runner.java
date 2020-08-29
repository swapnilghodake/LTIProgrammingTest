package com.test.lti.ltitest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.test.lti.ltitest.controller.Controller;
import com.test.lti.ltitest.util.configuration.InjectorConfiguration;

public class Runner {

	
	public static void main( String[] args ) {
		System.out.println("11111111111111111");
		Injector injector = Guice.createInjector(new InjectorConfiguration());
		Controller controller = injector.getInstance(Controller.class);
		
		System.out.println("2222222222222222");
    	controller.start();
	}

}
