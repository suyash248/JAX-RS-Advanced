package com.soni.bootstrap;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
Scans all the classes in classpath, And the if @Path annotation is found on any class then that class will be registered as resource.
*/
@ApplicationPath(value="v1")
public class RestBootstrap extends Application {

}
