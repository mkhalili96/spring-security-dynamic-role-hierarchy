package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security.hirerarchyExceptions;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class RoleHierarchyIdNotFoundException extends ExceptionsTemplate {


    public RoleHierarchyIdNotFoundException() {
        super("Could not find requested HierarchyRole");
    }

    public RoleHierarchyIdNotFoundException(Long id) {
        super("Could not find HierarchyRole with id : " + id);
    }


    @Override
    public int getStatus() {
        return ErrorMap.HIERARCHY_ROLE_NOT_FOUND.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.HIERARCHY_ROLE_NOT_FOUND;
    }
}
