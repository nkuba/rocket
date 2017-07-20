package com.jnowakowski.rocket.cookbook;

import com.jnowakowski.rocket.cookbook.model.Recipe;
import com.mongodb.WriteResult;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateResults;

import java.util.List;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
public interface CookbookDAO {
    List<Recipe> getAll();

    Recipe getRecipeById(int id);

    Key<Recipe> addRecipe(Recipe recipe);

    WriteResult deleteRecipe(Recipe recipe);

    UpdateResults updateRecipe(Recipe recipe);

    void deleteAllRecipes();
}
