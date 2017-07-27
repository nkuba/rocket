package com.jnowakowski.rocket.cookbook.repo;

import com.jnowakowski.rocket.cookbook.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CookbookRepo extends MongoRepository<Recipe, String> {

    List<Recipe> findByName(String name);
}
