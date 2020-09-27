package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class UserNameAlreadyExistException extends ExceptionsTemplate {

    public UserNameAlreadyExistException() {
    }

    public UserNameAlreadyExistException(String username) {
        super("user with username : " + username + " already exist ..");
    }

    public UserNameAlreadyExistException(String username, Throwable cause) {
        super("user with username : " + username + " already exist ..");
    }

    @Override
    public int getStatus() {
        return ErrorMap.USER_NAME_ALREADY_EXIST.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.USER_NAME_ALREADY_EXIST;
    }

}
