package com.jnowakowski.rocket.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("weather")
@Produces(MediaType.TEXT_PLAIN)
public class Weather {

    @GET
    public String get() {
        return "get weather fake response";
    }
}
