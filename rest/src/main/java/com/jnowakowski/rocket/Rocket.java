package com.jnowakowski.rocket;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
public class Rocket extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public MyApplication() {
        register(RequestContextFilter.class);
        register(JerseyResource.class);
        register(SpringSingletonResource.class);
        register(SpringRequestResource.class);
        register(CustomExceptionMapper.class);
    }
}
}
