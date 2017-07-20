package com.jnowakowski.rocket.cookbook;

import com.jnowakowski.rocket.cookbook.model.Message;
import com.jnowakowski.rocket.cookbook.model.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.List;

public class CookbookServiceTest extends JerseyTestNg.ContainerPerClassTest {
    private static final String RESOURCE = "cookbook";
    static final String MSG_RECIPE_ADDED_WITH_ID = "Recipe added with ID:";

    @Test
    public void testAddRecipe() {
        target(RESOURCE).request().delete();
        //Given
        String body = "{\"name\":\"recipe name 001\",\"meal\":[\"DINNER\",\"LUNCH\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
                          + "\"description\":\"description\",\"link\":\"www.kwestiasmaku.pl\"}";

        //When
        Response responseGet = target(RESOURCE).request().get();

        //Then
        Assert.assertEquals(responseGet.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(responseGet.readEntity(List.class).size(), 0);

        //When
        Response responseAdd = target(RESOURCE).path("add-recipe").request().post(Entity.json(body));

        //Then
        Assert.assertEquals(responseAdd.getStatus(), Response.Status.CREATED.getStatusCode());
        Assert.assertEquals(responseAdd.readEntity(Recipe.class).getName(), "recipe name 001");


        //When
        Response responseGet2 = target(RESOURCE).request().get();

        //Then
        Assert.assertEquals(responseGet2.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(responseGet2.readEntity(List.class).size(), 1);
    }

    @Test
    public void testDelete() {
        //When
        Response response = target(RESOURCE).request().delete();

        //Then
        Assert.assertEquals(response.readEntity(Message.class).getMessage(), "Removed all Recipes");
        Assert.assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void testAddRecipeDuplicateName() {
        target(RESOURCE).request().delete();
        //Given
        String body1 = "{\"name\":\"recipe name 001\",\"meal\":[\"DINNER\",\"LUNCH\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
                           + "\"description\":\"description\",\"link\":\"www.kwestiasmaku.pl\"}";
        String body2 = "{\"name\":\"recipe name 001\",\"meal\":[\"BREAKFAST\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
                           + "\"description\":\"description\",\"link\":\"www.jusiagotuje.pl\"}";

        //When
        Response response = target(RESOURCE).path("add-recipe").request().post(Entity.json(body1));

        //Then
        Assert.assertEquals(response.readEntity(Recipe.class).getName(), "recipe name 001");
        Assert.assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());

        //When
        Response response2 = target(RESOURCE).path("add-recipe").request().post(Entity.json(body2));

        //Then
        Assert.assertEquals(response2.readEntity(Message.class).getMessage(), "Recipe with name: 'recipe name 001' already exists!");
        Assert.assertEquals(response2.getStatus(), Response.Status.CONFLICT.getStatusCode());
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(CookbookService.class);
    }
}
