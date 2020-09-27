package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security.hirerarchyExceptions;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class RoleCantChangeException extends ExceptionsTemplate {

    public RoleCantChangeException() {
        super("role cant change");
    }

    @Override
    public int getStatus() {
        return ErrorMap.CANT_CHANGE_ROLLE.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.CANT_CHANGE_ROLLE;
    }

}
