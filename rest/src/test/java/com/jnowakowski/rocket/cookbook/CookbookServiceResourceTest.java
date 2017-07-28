package com.jnowakowski.rocket.cookbook;

import com.jnowakowski.rocket.cookbook.model.Message;
import com.jnowakowski.rocket.cookbook.model.Recipe;
import com.jnowakowski.rocket.cookbook.repo.CookbookRepo;
import com.jnowakowski.rocket.cookbook.rest.CookbookService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.mockito.Mockito;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class CookbookServiceResourceTest extends JerseyTestNg.ContainerPerClassTest {
    private static final String RESOURCE = "cookbook";

    private CookbookService service;
    private CookbookRepo cookbookRepo;

    private String json = "{\"name\":\"recipe name 001\",\"meal\":[\"DINNER\",\"LUNCH\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
                              + "\"description\":\"some description\",\"link\":\"www.kwestiasmaku.pl\"}";

    @Test
    public void testCookbookGet() {
        // When
        Response actualResponse = target(RESOURCE).request().get();

        // Then
        Mockito.verify(service, Mockito.times(1)).getCookbook();
        Assert.assertEquals(actualResponse.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void testAddRecipePost() {
        //When
        Response response = target(RESOURCE).request().post(Entity.json(json));

        //Then
        Mockito.verify(service, Mockito.times(1)).addRecipe(Mockito.any(Recipe.class));
        Assert.assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());
    }

    @Test
    public void testCookbookGetById() {
        // Given
        String id = "8";
        Mockito.when(cookbookRepo.findOne(id)).thenReturn(Mockito.mock(Recipe.class));

        // When
        Response response = target(RESOURCE).path(id).request().get();

        //Then
        Mockito.verify(service, Mockito.times(1)).getRecipe(id);
        Assert.assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void testCookbookDelete() {
        // When
        Response response = target(RESOURCE).request().delete();

        // Then
        Mockito.verify(service, Mockito.times(1)).deleteAll();
        Assert.assertEquals(response.readEntity(Message.class).getMessage(), "Removed all recipes");
        Assert.assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void testCookbookDeleteById() {
        // Given
        String id = "8";
        Mockito.when(cookbookRepo.findOne(id)).thenReturn(Mockito.mock(Recipe.class));

        //When
        Response responseDelete = target(RESOURCE).path(id).request().delete();

        // Then
        Mockito.verify(service, Mockito.times(1)).deleteRecipe(id);
        Assert.assertEquals(responseDelete.readEntity(Message.class).getMessage(), "Removed recipe with id: " + id);
        Assert.assertEquals(responseDelete.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Override
    protected Application configure() {
        cookbookRepo = Mockito.mock(CookbookRepo.class);
        service = Mockito.spy(new CookbookService(cookbookRepo));

        return new ResourceConfig(CookbookService.class).register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(service).to(CookbookService.class);
                // bind(cookbookRepo).to(CookbookRepo.class);
            }
        }).property("contextConfig", new ClassPathXmlApplicationContext("classpath:testContext.xml"));
    }
}
