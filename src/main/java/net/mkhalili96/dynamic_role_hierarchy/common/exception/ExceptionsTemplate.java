package net.mkhalili96.dynamic_role_hierarchy.common.exception;


import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public abstract class ExceptionsTemplate extends Exception {

    public ExceptionsTemplate() {
    }

    public ExceptionsTemplate(String message) {
        super(message);
    }

    public ExceptionsTemplate(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatus();

    public abstract ErrorMap getError();
}
