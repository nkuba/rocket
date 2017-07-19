package com.jnowakowski.rocket.cookbook;

import com.jnowakowski.rocket.cookbook.model.Recipe;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
@Repository
public class CookbookDAOMongoDB implements CookbookDAO {
    private static final String DB_HOST = "192.168.8.22";
    private static final int DB_PORT = 27017;
    private static final String DB_NAME = "rocketDB";
    private static CookbookDAOMongoDB instance = null;
    private final Datastore datastore;

    private CookbookDAOMongoDB() {
        final Morphia morphia = new Morphia();
        morphia.mapPackageFromClass(Recipe.class);

        datastore = null;
        // datastore = morphia.createDatastore(new MongoClient(DB_HOST, DB_PORT), DB_NAME);
        // datastore.ensureIndexes();
    }

    // @Override
    // public List<Recipe> getAll() {
    //     return datastore.createQuery(Recipe.class).asList();
    // }
    //
    // @Override
    // public Recipe getRecipeById(int id) {
    //     return null;
    // }
    //
    // @Override
    // public Key<Recipe> addRecipe(Recipe recipe) {
    //     return datastore.save(recipe);
    // }
    //
    // @Override
    // public WriteResult deleteRecipe(Recipe recipe) {
    //     return datastore.delete(recipe);
    // }
    //
    // @Override
    // public UpdateResults updateRecipe(Recipe recipe) {
    //     throw new RuntimeException("UPDATE NOT SUPPORTED");
    // }

    public void deleteAllRecipes() {
        datastore.findAndDelete(datastore.createQuery(Recipe.class));
    }

    private List<Recipe> getByName(String name) {
        return datastore.createQuery(Recipe.class).filter("name", name).asList();
    }
}
