package com.jnowakowski.rocket.customer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
public class Customer implements Serializable {
    @JsonProperty
    private int id;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;

    @JsonCreator
    public Customer(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName) {
        this.id = firstName.length();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }
}
