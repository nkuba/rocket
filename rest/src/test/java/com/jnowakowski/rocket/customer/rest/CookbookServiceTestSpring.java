// package com.jnowakowski.rocket.customer.rest;
//
// import com.jnowakowski.rocket.cookbook.rest.CookbookService;
// import org.glassfish.jersey.server.ResourceConfig;
// import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
// import org.springframework.web.client.RestTemplate;
// import org.testng.Assert;
// import org.testng.annotations.Test;
//
// import javax.ws.rs.client.Entity;
// import javax.ws.rs.core.Response;
// import java.util.List;
//
// public class CookbookServiceTestSpring extends AbstractTestNGSpringContextTests {
//     private static final String RESOURCE = "cookbook";
//     private RestTemplate restTemplate = new TestRestTemplate();
//
//     // @BeforeClass
//     // public void setUp() {
//     //     CookbookDAO cookbookDAO = CookbookDAOMongoDB.getInstance();
//     //     cookbookDAO.deleteAllRecipes();
//     // }
//
//     @Test
//     public void testAddRecipe() {
//         //Given
//         String body1 = "{\"name\":\"recipe name 001\",\"meal\":[\"DINNER\",\"LUNCH\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
//                            + "\"description\":\"description\",\"link\":\"www.kwestiasmaku.pl\"}";
//         String body2 = "{\"name\":\"recipe name 002\",\"meal\":[\"BREAKFAST\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
//                            + "\"description\":\"description\",\"link\":\"www.jusiagotuje.pl\"}";
//
//         String expectedBody1 = "Recipe added with ID: 1001";
//         String expectedBody2 = "Recipe added with ID: 1002";
//         int expectedCode = 201;
//
//
//         Response response0 = target(RESOURCE).request().get();
//         Assert.assertEquals(response0.readEntity(List.class).size(), 0);
//
//         //When
//         Response response = target(RESOURCE).path("add-recipe").request().post(Entity.json(body1));
//
//         //Then
//         Assert.assertEquals(response.readEntity(String.class), expectedBody1);
//         Assert.assertEquals(response.getStatus(), expectedCode);
//
//         //When
//         Response response2 = target(RESOURCE).path("add-recipe").request().post(Entity.json(body2));
//
//         //Then
//         Assert.assertEquals(response2.readEntity(String.class), expectedBody2);
//         Assert.assertEquals(response2.getStatus(), expectedCode);
//     }
//
//     @Override
//     protected Application configure() {
//         return new ResourceConfig(CookbookService.class);
//     }
// }
