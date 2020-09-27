package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class UserNameCantChangeException extends ExceptionsTemplate {

    public UserNameCantChangeException() {
        super("username cant change");
    }

    @Override
    public int getStatus() {
        return ErrorMap.CANT_CHANGE_USERNAME.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.CANT_CHANGE_USERNAME;
    }

}
