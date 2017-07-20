package com.jnowakowski.rocket;

import com.jnowakowski.rocket.cookbook.rest.CookbookService;
import com.jnowakowski.rocket.customer.rest.CustomerService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
public class Rocket extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public Rocket() {
        register(RequestContextFilter.class);
        register(CookbookService.class);
        register(CustomerService.class);
    }
}
