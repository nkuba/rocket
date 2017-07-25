package com.jnowakowski.rocket.cookbook;

import com.jnowakowski.rocket.cookbook.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
public interface CookbookRepo extends MongoRepository<Recipe, String> {

    List<Recipe> findByName(String name);
}
