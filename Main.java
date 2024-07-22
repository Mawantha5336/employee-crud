package com.jiat.employeecrud2.app;

import com.jiat.employeecrud2.app.config.AppConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context context = tomcat.addContext("/employeecrud2", new File(".").getAbsolutePath());

        Tomcat.addServlet(context, "home", new ServletContainer(new AppConfig()));

        context.addServletMappingDecoded("/*", "home");


        tomcat.start();
    }
}
