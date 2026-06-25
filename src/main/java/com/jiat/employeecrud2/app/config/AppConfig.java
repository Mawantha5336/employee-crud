package com.jiat.employeecrud2.app.config;

import org.glassfish.jersey.server.ResourceConfig;

public class AppConfig extends ResourceConfig {
    public AppConfig() {
        packages("com.jiat.employeecrud2.app.controllers");
    }
}
