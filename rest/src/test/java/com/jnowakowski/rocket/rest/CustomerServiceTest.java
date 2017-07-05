package com.jnowakowski.rocket.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class CustomerServiceTest extends JerseyTestNg.ContainerPerClassTest {
    private static final String RESOURCE = "customers";

    @Test
    public void testGetList() {
        //Given
        String expectedBody = "[{\"firstName\":\"Justin\",\"lastName\":\"Bron\",\"id\":6},{\"firstName\":\"An\","
                                  + "\"lastName\":\"Leno\",\"id\":2}]";

        int expectedCode = 200;

        //When
        Response response = target(RESOURCE).request().get();

        //Then
        Assert.assertEquals(response.readEntity(String.class), expectedBody);
        Assert.assertEquals(response.getStatus(), expectedCode);
    }

    @Test
    public void testCreateCustomer() {
        //Given
        String body = "{\"firstName\" : \"John\",\"lastName\" : \"Doe\"}";
        String expectedBody = "Customer created with ID: 4";
        int expectedCode = 201;

        //When
        Response response = target(RESOURCE).path("create").request().post(Entity.json(body));

        //Then
        Assert.assertEquals(response.readEntity(String.class), expectedBody);
        Assert.assertEquals(response.getStatus(), expectedCode);
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(CustomerService.class);
    }
}
