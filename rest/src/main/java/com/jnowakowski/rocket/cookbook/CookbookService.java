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

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
@Component
@Path("/cookbook")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CookbookService {
    private static final Logger LOG = LoggerFactory.getLogger(Rocket.class);

    @Autowired
    private CookbookRepo cookbookRepo;

    @GET
    public Response getCookbook() {
        LOG.debug("Get cookbook");
        return Response.ok(cookbookRepo.findAll()).build();
    }

    @DELETE
    public Response deleteAll() {
        LOG.debug("deleteAll");
        cookbookRepo.deleteAll();
        return Response.ok(new Message("Removed all Recipes")).build();
    }

    @POST
    @Path("add-recipe")
    public Response addRecipe(Recipe recipe) {
        LOG.debug("Add recipe {}", recipe);
        Response response;

        if (cookbookRepo.findByName(recipe.getName()).size() > 0) {
            response = Response.status(Response.Status.CONFLICT)
                               .entity(new Message(String.format("Recipe with name: '%s' already exists!", recipe.getName())))
                               .build();
        } else {
            Recipe result = cookbookRepo.save(recipe);
            response = Response.status(Response.Status.CREATED).entity(result).build();
        }

        LOG.debug("Response: " + response);
        return response;
    }
}
