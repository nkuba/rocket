package com.jnowakowski.rocket;

import com.jnowakowski.rocket.cookbook.CookbookDAO;
import com.jnowakowski.rocket.cookbook.rest.CookbookService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication extends ResourceConfig{

    public RestApplication() {
        register(CookbookService.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}