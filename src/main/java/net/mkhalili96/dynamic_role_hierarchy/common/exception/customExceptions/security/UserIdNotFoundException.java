package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class UserIdNotFoundException extends ExceptionsTemplate {

    public UserIdNotFoundException() {
    }

    public UserIdNotFoundException(Long id) {
        super("Could not find User with id : " + id);
    }

    public UserIdNotFoundException(Long id, Throwable cause) {
        super("Could not find User with id : " + id, cause);
    }

    @Override
    public int getStatus() {
        return ErrorMap.USER_ID_NOT_FOUND.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.USER_ID_NOT_FOUND;
    }
}
