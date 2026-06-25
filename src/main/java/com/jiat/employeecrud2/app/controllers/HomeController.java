package com.jiat.employeecrud2.app.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class HomeController {
    @GET
    public String index() {
        return "Hello World!";
    }
}
