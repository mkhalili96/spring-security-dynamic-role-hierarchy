package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class RoleIdNotFoundException extends ExceptionsTemplate {


    public RoleIdNotFoundException() {
    }

    public RoleIdNotFoundException(Long id) {
        super("Could not find Role with id : " + id);
    }

    public RoleIdNotFoundException(Long id, Throwable cause) {
        super("Could not find Role with id : " + id, cause);
    }

    @Override
    public int getStatus() {
        return ErrorMap.ROLE_ID_NOT_FOUND.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.ROLE_ID_NOT_FOUND;
    }
}
