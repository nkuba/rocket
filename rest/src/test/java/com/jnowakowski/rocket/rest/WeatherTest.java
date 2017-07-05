package com.jnowakowski.rocket.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class WeatherTest extends JerseyTestNg.ContainerPerClassTest {
    private static final String RESOURCE = "weather";

    @Test
    public void test() {
        Response response = target(RESOURCE).request().get();
        Assert.assertEquals(response.getStatus(), 200);
        Assert.assertEquals(response.readEntity(String.class), "get weather fake response");
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(Weather.class);
    }
}
