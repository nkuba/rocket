package com.jnowakowski.rocket.cookbook;

import com.jnowakowski.rocket.cookbook.model.Recipe;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CookbookRepoTest extends AbstractTestNGSpringContextTests {

    // TODO: TEST IT ON STUB DATABASE

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CookbookRepo cookbookRepo;

    @BeforeMethod
    public void beforeTest() {
        cookbookRepo.deleteAll();
    }

    @Test
    public void testAddRecipeDuplicateName() {
        // //Given
        String body1 = "{\"name\":\"recipe name 001\",\"meal\":[\"DINNER\",\"LUNCH\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
                           + "\"description\":\"description\",\"link\":\"www.kwestiasmaku.pl\"}";
        String body2 = "{\"name\":\"recipe name 001\",\"meal\":[\"BREAKFAST\"],\"ingredients\":{\"mieso\":\"100g\",\"warzywa\":\"400g\"},"
                           + "\"description\":\"description\",\"link\":\"www.jusiagotuje.pl\"}";

        Recipe recipe1 = mongoTemplate.getConverter().read(Recipe.class, (DBObject) JSON.parse(body1));
        Recipe recipe2 = mongoTemplate.getConverter().read(Recipe.class, (DBObject) JSON.parse(body2));

        Recipe result1 = cookbookRepo.save(recipe1);
        Recipe result2 = cookbookRepo.save(recipe2);
        System.out.println(result1.getId());
        System.out.println(result2.getId());
    }

}
