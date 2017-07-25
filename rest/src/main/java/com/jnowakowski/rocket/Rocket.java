package com.jnowakowski.rocket;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rocket extends ResourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(Rocket.class);

    /**
     * Register JAX-RS application components.
     */
    public Rocket() {
        LOG.info("Rocket app started");
    }
}
