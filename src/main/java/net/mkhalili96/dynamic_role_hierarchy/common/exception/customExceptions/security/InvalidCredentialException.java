package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class InvalidCredentialException extends ExceptionsTemplate {


    public InvalidCredentialException() {
        super("username or password is incorrect ..");
    }


    @Override
    public int getStatus() {
        return ErrorMap.INVALID_CREDENTIALS.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.INVALID_CREDENTIALS;
    }
}
