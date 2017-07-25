package com.jnowakowski.rocket.cookbook;

import com.jnowakowski.rocket.cookbook.model.Meal;
import com.jnowakowski.rocket.cookbook.model.Message;
import com.jnowakowski.rocket.cookbook.model.Recipe;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.*;

import static java.lang.String.format;
import static java.util.Arrays.asList;

public class CookbookServiceIntegrationTest extends JerseyTestNg.ContainerPerClassTest {
    private static final String RESOURCE = "cookbook";
    String recipeJson2 = "{\"name\":\"recipe name 002\",\"meal\":[\"BREAKFAST\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
                             + "\"description\":\"description\",\"link\":\"www.jusiagotuje.pl\"}";
    private String recipeJson1 =
        "{\"name\":\"recipe name 001\",\"meal\":[\"DINNER\",\"LUNCH\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
            + "\"description\":\"some description\",\"link\":\"www.kwestiasmaku.pl\"}";

    // @Test
    public void testAddRecipe() throws ParseException {
        cleanUp();

        //Given
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("mieso", "100g");
        expectedMap.put("warzywa", "400g");

        validateCookbookSize(0);

        //When
        Response responseAdd = addRecipe(recipeJson1);

        //Then
        Assert.assertEquals(responseAdd.getStatus(), Response.Status.CREATED.getStatusCode());

        Recipe recipe = responseAdd.readEntity(Recipe.class);
        Assert.assertTrue(recipe.getId().length() > 0);
        Assert.assertEquals(recipe.getVersion(), new Long(0));
        Assert.assertEquals(recipe.getMeal(), asList(Meal.DINNER, Meal.LUNCH));
        Assert.assertEquals(recipe.getIngredients(), expectedMap);
        Assert.assertEquals(recipe.getDescription(), "some description");
        Assert.assertEquals(recipe.getLink(), "www.kwestiasmaku.pl");
        Assert.assertTrue(recipe.getCreateDate().before(new Date()));
        Assert.assertEquals(recipe.getUpdateDate(), null);

        validateCookbookSize(1);
    }

    // @Test
    public void testGetByIdPositive() {
        cleanUp();

        // Given
        Response responseAdd = addRecipe(recipeJson1);
        Recipe recipeExpected = responseAdd.readEntity(Recipe.class);
        String id = recipeExpected.getId();

        // When
        Response responseGet = target(RESOURCE).path(id).request().get();

        Recipe recipeActual = responseGet.readEntity(Recipe.class);

        Assert.assertEquals(recipeActual.toString(), recipeExpected.toString());
    }

    // @Test
    public void testGetByIdNegative() {
        cleanUp();
        // Given
        String id = "8";

        // When
        Response responseGet = target(RESOURCE).path(id).request().get();

        // Then
        Assert.assertEquals(responseGet.readEntity(Message.class).getMessage(), format("Recipe with id: '%s' not found", id));
    }

    // @Test
    public void testDeleteAll() {
        cleanUp();

        // Given
        addRecipe(recipeJson1);
        addRecipe(recipeJson2);
        validateCookbookSize(2);

        // When
        Response response = target(RESOURCE).request().delete();

        // Then
        validateCookbookSize(0);
        Assert.assertEquals(response.readEntity(Message.class).getMessage(), "Removed all recipes");
        Assert.assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }

    // @Test
    public void testDeleteByIdPositive() {
        cleanUp();

        // Given
        String id = addRecipe(recipeJson1).readEntity(Recipe.class).getId();
        validateCookbookSize(1);

        //When
        Response responseDelete = target(RESOURCE).path(id).request().delete();

        // Then
        Assert.assertEquals(responseDelete.readEntity(Message.class).getMessage(), "Removed recipe with id: " + id);
        Assert.assertEquals(responseDelete.getStatus(), Response.Status.OK.getStatusCode());

        validateCookbookSize(0);
    }

    // @Test
    public void testDeleteByIdNegative() {
        cleanUp();

        // Given
        String id = "8";

        // When
        Response responseDelete = target(RESOURCE).path(id).request().delete();

        // Then
        Assert.assertEquals(responseDelete.readEntity(Message.class).getMessage(), format("Recipe with id: '%s' not found", id));
    }

    // @Test
    public void testAddRecipeDuplicateName() {
        cleanUp();
        // Given
        String recipeJsonDuplicate =
            "{\"name\":\"recipe name 001\",\"meal\":[\"BREAKFAST\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
                + "\"description\":\"description\",\"link\":\"www.jusiagotuje.pl\"}";

        //When
        Response response = addRecipe(recipeJson1);
        Response response2 = addRecipe(recipeJsonDuplicate);

        //Then
        Assert.assertEquals(response.readEntity(Recipe.class).getName(), "recipe name 001");
        Assert.assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());

        Assert.assertEquals(response2.readEntity(Message.class).getMessage(), "Recipe with name: 'recipe name 001' already exists!");
        Assert.assertEquals(response2.getStatus(), Response.Status.CONFLICT.getStatusCode());

        validateCookbookSize(1);
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(CookbookService.class);
    }

    private Response addRecipe(String json) {
        return target(RESOURCE).request().post(Entity.json(json));
    }

    private void cleanUp() {
        target(RESOURCE).request().delete();
    }

    private void validateCookbookSize(int expectedSize) {
        Response responseGet = target(RESOURCE).request().get();

        Assert.assertEquals(responseGet.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(responseGet.readEntity(List.class).size(), expectedSize);
    }
}
