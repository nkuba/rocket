package com.jnowakowski.rocket.cookbook.rest;

import com.jnowakowski.rocket.cookbook.CookbookDAO;
import com.jnowakowski.rocket.cookbook.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private CookbookDAO cookBookDAO;

    @GET
    public Response getCookbook() {
        // return Response.status(200).entity(cookBookDAO.findAll()).build();
        return Response.status(200).entity("OK").build();
    }

    @POST
    @Path("add-recipe")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRecipe(Recipe recipe) {
        // Recipe result = cookBookDAO.save(recipe);
        // return Response.status(201).entity("Recipe added with ID: " + result.getId()).build();
        return Response.status(201).entity("Recipe added with ID: " + 111111).build();
    }
}
