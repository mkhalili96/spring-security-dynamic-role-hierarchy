package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security;


import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class UnauthorizedException extends ExceptionsTemplate {


    public UnauthorizedException() {
        super("Unauthorized Access");
    }
    @Override
    public int getStatus() {
        return ErrorMap.Unauthorized.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.Unauthorized;
    }
}
