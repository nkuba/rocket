package com.jnowakowski.rocket.customer.rest;

import com.google.gson.Gson;
import com.jnowakowski.rocket.cookbook.model.Recipe;
import com.jnowakowski.rocket.customer.model.Meal;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
public class ObjectGenerator {
    @Test
    public void generateJson() {
        Map map = new HashMap<String, String>();
        map.put("mieso", "100g");
        map.put("warzywa", "400g");

        Recipe recipe = new Recipe("recipe name 001", Arrays.asList(Meal.DINNER, Meal.LUNCH), map, "description", "www.kwestiasmaku.pl");

        Gson gson = new Gson();
        String result = gson.toJson(recipe);
        System.out.println(result);

    }


}