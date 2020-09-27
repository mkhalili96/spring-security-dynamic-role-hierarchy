package net.mkhalili96.dynamic_role_hierarchy.model.service;

import net.mkhalili96.dynamic_role_hierarchy.model.entity.Role;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles() throws Exception;

    public User addRole(Long userId, Long roleId) throws Exception;

    public User removeRole(Long userId, Long roleId) throws Exception;
}
