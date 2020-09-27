package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class UserDisabledException extends ExceptionsTemplate {


    public UserDisabledException() {
        super("the user account is currently disabled ..");
    }


    @Override
    public int getStatus() {
        return ErrorMap.USER_DISABLED.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.USER_DISABLED;
    }
}
