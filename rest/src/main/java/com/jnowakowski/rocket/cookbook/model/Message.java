package com.jnowakowski.rocket.cookbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
public class Message implements Serializable {
    private String message;

    public String getMessage() {
        return message;
    }

    public Message() {
    }

    public Message(String message) {

        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("message", message).toString();
    }
}
