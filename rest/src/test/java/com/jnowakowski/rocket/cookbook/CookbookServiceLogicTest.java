package com.jnowakowski.rocket.cookbook;

import com.jnowakowski.rocket.cookbook.model.Meal;
import com.jnowakowski.rocket.cookbook.model.Message;
import com.jnowakowski.rocket.cookbook.model.Recipe;
import org.bson.types.ObjectId;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import javax.ws.rs.core.Response;
import java.util.*;

import static java.lang.String.format;
import static org.powermock.api.mockito.PowerMockito.when;

public class CookbookServiceLogicTest {
    private static final String RESOURCE = "cookbook";

    private CookbookRepo repo;

    private CookbookService service;

    @BeforeClass
    public void beforeClass() {
        repo = Mockito.mock(CookbookRepo.class);
        service = new CookbookService(repo);
    }

    @Test
    public void testGetCookbook() {
        // Given
        List expected = Arrays.asList(generateRecipe());
        when(repo.findAll()).thenReturn(expected);

        // When
        Response response = service.getCookbook();

        // Then
        Assert.assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(response.getEntity(), expected);
    }

    @Test
    public void testAddRecipe() {
        //Given
        Recipe recipeIn = generateRecipe();

        Recipe recipeExpected = recipeIn;
        recipeExpected.setId(ObjectId.get());
        recipeExpected.setVersion(0L);

        when(repo.findByName(recipeIn.getName())).thenReturn(Collections.emptyList());
        when(repo.save(recipeIn)).thenReturn(recipeExpected);

        //When
        Response response = service.addRecipe(recipeIn);

        //Then
        Assert.assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());

        Recipe recipeActual = (Recipe) response.getEntity();
        Assert.assertEquals(recipeActual.getId(), recipeExpected.getId());
        Assert.assertEquals(recipeActual.getVersion(), recipeExpected.getVersion());
        Assert.assertEquals(recipeActual.getName(), recipeExpected.getName());
        Assert.assertEquals(recipeActual.getMeal(), recipeExpected.getMeal());
        Assert.assertEquals(recipeActual.getIngredients(), recipeExpected.getIngredients());
        Assert.assertEquals(recipeActual.getDescription(), recipeExpected.getDescription());
        Assert.assertEquals(recipeActual.getLink(), recipeExpected.getLink());
        Assert.assertTrue(recipeActual.getCreateDate().before(new Date()));
        Assert.assertEquals(recipeActual.getUpdateDate(), recipeExpected.getUpdateDate());
    }

    @Test
    public void testGetByIdPositive() {
        // Given
        Recipe recipe = generateRecipe();
        recipe.setId(ObjectId.get());

        when(repo.findOne(recipe.getId())).thenReturn(recipe);

        // When
        Response response = service.getRecipe(recipe.getId());

        // Then
        Assert.assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(response.getEntity(), recipe);
    }

    @Test
    public void testGetByIdNegative() {
        // Given
        String id = "ID8";
        when(repo.findOne(id)).thenReturn(null);

        // When
        Response response = service.getRecipe(id);

        // Then
        Assert.assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());
        Assert.assertEquals(getResponseMessage(response), format("Recipe with id: '%s' not found", id));
    }

    @Test
    public void testDeleteAll() {
        // When
        Response response = service.deleteAll();

        // Then
        Assert.assertEquals(getResponseMessage(response), "Removed all recipes");
        Assert.assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void testDeleteByIdPositive() {
        // Given
        Recipe recipe = generateRecipe();
        recipe.setId(ObjectId.get());

        when(repo.findOne(recipe.getId())).thenReturn(recipe);

        //When
        Response response = service.deleteRecipe(recipe.getId());

        // Then
        Assert.assertEquals(getResponseMessage(response), "Removed recipe with id: " + recipe.getId());
        Assert.assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

    }

    @Test
    public void testDeleteByIdNegative() {
        // Given
        String id = "ID8";
        when(repo.findOne(id)).thenReturn(null);

        // When
        Response response = service.deleteRecipe(id);

        // Then
        Assert.assertEquals(getResponseMessage(response), format("Recipe with id: '%s' not found", id));
        Assert.assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void testAddRecipeDuplicateName() {
        // Given
        Recipe recipe = generateRecipe();

        when(repo.findByName(recipe.getName())).thenReturn(Arrays.asList(recipe));

        //When
        Response response = service.addRecipe(recipe);

        //Then
        Assert.assertEquals(getResponseMessage(response), String.format("Recipe with name: '%s' already exists!", recipe.getName()));
        Assert.assertEquals(response.getStatus(), Response.Status.CONFLICT.getStatusCode());
    }

    private Recipe generateRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Recipe Name 1");
        recipe.setDescription("desc");
        recipe.setIngredients((Map<String, String>) Maps.newHashMap().put("key", "value"));
        recipe.setLink("link 1");
        recipe.setMeal(Arrays.asList(Meal.DINNER, Meal.SNACK));
        return recipe;
    }

    private String getResponseMessage(Response response) {
        return ((Message) response.getEntity()).getMessage();
    }

}
