package com.jnowakowski.rocket.cookbook.rest;

import com.jnowakowski.rocket.cookbook.CookbookDAO;
import com.jnowakowski.rocket.cookbook.model.Recipe;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
@Component
@Path("/cookbook")
@Produces(MediaType.APPLICATION_JSON)
public class CookbookService {
    @Autowired
    private CookbookDAO cookBook;

    @GET
    public Response getCookbook() {
        return Response.status(200).entity(cookBook.findAll()).build();
    }

    @POST
    @Path("add-recipe")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRecipe(Recipe recipe) {
        Recipe result = cookBook.save(recipe);
        return Response.status(201).entity("Recipe added with ID: " + result.getId()).build();
    }
}
