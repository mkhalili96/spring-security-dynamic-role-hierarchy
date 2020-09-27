package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class UserNameNotFoundException extends ExceptionsTemplate {

    public UserNameNotFoundException() {
    }

    public UserNameNotFoundException(String username) {
        super("Could not find User with username : " + username);
    }

    public UserNameNotFoundException(String username, Throwable cause) {
        super("Could not find User with username : " + username);
    }

    @Override
    public int getStatus() {
        return ErrorMap.USER_NAME_NOT_FOUND.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.USER_NAME_NOT_FOUND;
    }
}
