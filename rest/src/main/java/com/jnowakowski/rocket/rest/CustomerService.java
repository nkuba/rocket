package com.jnowakowski.rocket.rest;

import com.jnowakowski.rocket.model.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("customers")
@Produces(MediaType.TEXT_PLAIN)
public class CustomerService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        Customer customer1 = new Customer("Justin", "Bron");
        Customer customer2 = new Customer("An", "Leno");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        return Response.status(200).entity(customers).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        String result = "Customer created with ID: " + customer.getId();

        return Response.status(201).entity(result).build();
    }
}
