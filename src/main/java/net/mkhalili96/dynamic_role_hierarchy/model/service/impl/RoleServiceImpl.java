package net.mkhalili96.dynamic_role_hierarchy.model.service.impl;


import net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security.UserIdNotFoundException;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security.UserRoleNotFoundException;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.Role;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;
import net.mkhalili96.dynamic_role_hierarchy.model.repository.RoleRepository;
import net.mkhalili96.dynamic_role_hierarchy.model.repository.UserRepository;
import net.mkhalili96.dynamic_role_hierarchy.model.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Role> getAllRoles() throws Exception {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    @Transactional
    public User addRole(Long userId, Long roleId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserIdNotFoundException(userId));
        user.getUserRolesSet().add(roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException(roleId.toString())));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User removeRole(Long userId, Long roleId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserIdNotFoundException(userId));
        Role role = user.getUserRolesSet()
                .stream()
                .filter(r -> r.getId().equals(roleId)).findFirst()
                .orElseThrow(() -> new UserRoleNotFoundException(user.getUsername(), roleId));
        user.getUserRolesSet().remove(role);
        return userRepository.save(user);
    }

}
