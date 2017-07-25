package com.jnowakowski.rocket.cookbook;

import com.jnowakowski.rocket.Rocket;
import com.jnowakowski.rocket.cookbook.model.Message;
import com.jnowakowski.rocket.cookbook.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.lang.String.format;
import static javax.ws.rs.core.Response.Status.*;

@Component
@Path("/cookbook")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CookbookService {
    private static final Logger LOG = LoggerFactory.getLogger(Rocket.class);

    private CookbookRepo cookbookRepo;

    @Autowired
    public CookbookService(CookbookRepo cookbookRepo) {
        this.cookbookRepo = cookbookRepo;
    }

    @GET
    public Response getCookbook() {
        LOG.debug("Get cookbook");
        return Response.ok(cookbookRepo.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getRecipe(@PathParam("id") String id) {
        LOG.debug("Get recipe with id: {}", id);
        Recipe recipe = cookbookRepo.findOne(id);

        if (recipe == null) {
            return Response.status(NOT_FOUND).entity(new Message(format("Recipe with id: '%s' not found", id))).build();
        } else {
            return Response.ok(recipe).build();
        }
    }

    @POST
    public Response addRecipe(Recipe recipe) {
        LOG.debug("Add recipe {}", recipe);
        Response response;

        if (cookbookRepo.findByName(recipe.getName()).size() > 0) {
            response = Response.status(CONFLICT).entity(new Message(format("Recipe with name: '%s' already exists!", recipe.getName()))).build();
        } else {
            Recipe result = cookbookRepo.save(recipe);
            response = Response.status(CREATED).entity(result).build();
        }
        return response;
    }

    @DELETE
    public Response deleteAll() {
        LOG.debug("deleteAll");
        cookbookRepo.deleteAll();
        return Response.ok(new Message("Removed all recipes")).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRecipe(@PathParam("id") String id) {
        LOG.debug("Delete recipe with id: {}", id);
        Recipe recipe = cookbookRepo.findOne(id);

        if (recipe == null) {
            return Response.status(NOT_FOUND).entity(new Message(format("Recipe with id: '%s' not found", id))).build();
        } else {
            cookbookRepo.delete(id);
            return Response.ok(new Message(format("Removed recipe with id: %s", id))).build();
        }
    }
}
