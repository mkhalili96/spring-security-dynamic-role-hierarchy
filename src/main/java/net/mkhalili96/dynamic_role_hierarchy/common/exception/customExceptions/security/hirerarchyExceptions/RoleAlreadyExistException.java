package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security.hirerarchyExceptions;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class RoleAlreadyExistException extends ExceptionsTemplate {


    public RoleAlreadyExistException() {
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public ErrorMap getError() {
        return null;
    }

    public RoleAlreadyExistException(String role) {
        super("RoleHierarchy database already have this role : " + role);
    }
}