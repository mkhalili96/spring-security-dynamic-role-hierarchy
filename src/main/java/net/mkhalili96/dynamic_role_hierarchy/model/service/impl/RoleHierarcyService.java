package net.mkhalili96.dynamic_role_hierarchy.model.service.impl;

import net.mkhalili96.dynamic_role_hierarchy.common.config.RoleHierarchyProvider;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security.hirerarchyExceptions.*;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.RoleHierarchyEntity;
import net.mkhalili96.dynamic_role_hierarchy.model.repository.RoleHierarchyRepository;
import net.mkhalili96.dynamic_role_hierarchy.model.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;


@Service
public class RoleHierarcyService {

    @Autowired
    RoleHierarchyRepository roleHierarchyRepository;

    @Autowired
    RoleHierarchyProvider roleHierarchyProvider;

    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public RoleHierarchyEntity save(RoleHierarchyEntity roleHierarchyEntity) throws Exception {
        if (roleHierarchyRepository.existsByRole(roleHierarchyEntity.getRole()))
            throw new RoleAlreadyExistException(roleHierarchyEntity.getRole());
        if (roleRepository.existsByRoleName(roleHierarchyEntity.getRole()))
            roleHierarchyEntity.setIsMainRole(true);
        RoleHierarchyEntity result = roleHierarchyRepository.save(roleHierarchyEntity);
        updateRoleHierarchyProvider();
        return result;
    }

    @Transactional
    public RoleHierarchyEntity update(RoleHierarchyEntity roleHierarchyEntity) throws Exception {

        if (roleHierarchyEntity.getId() != null) {
            RoleHierarchyEntity role = roleHierarchyRepository.findById(roleHierarchyEntity.getId()).orElseThrow(() -> new RoleHierarchyIdNotFoundException(roleHierarchyEntity.getId()));
            if (!role.getRole().equals(roleHierarchyEntity.getRole()))
                throw new RoleCantChangeException();
            if (roleRepository.existsByRoleName(roleHierarchyEntity.getRole()))
                role.setIsMainRole(true);
            role.setRoleHierarchyList(roleHierarchyEntity.getRoleHierarchyList());
            RoleHierarchyEntity result = roleHierarchyRepository.save(roleHierarchyEntity);
            updateRoleHierarchyProvider();
            return result;
        } else {
            throw new RoleHierarchyIdNotFoundException(new Long(0));
        }
    }

    @Transactional
    public void delete(Long roleId) throws Exception {
        RoleHierarchyEntity roleHierarchyEntity = roleHierarchyRepository.findById(roleId).orElseThrow(() -> new RoleHierarchyIdNotFoundException(roleId));
        roleHierarchyRepository.delete(roleHierarchyEntity);
        updateRoleHierarchyProvider();
    }

    public RoleHierarchyEntity getRole(Long roleId) throws Exception {
        return roleHierarchyRepository.findById(roleId).orElseThrow(() -> new RoleHierarchyIdNotFoundException(roleId));
    }

    public RoleHierarchyEntity getRoleByName(String role) throws Exception {
        return Optional.of(roleHierarchyRepository.findByRole(role)).orElseThrow(() -> new RoleHierarchyIdNotFoundException());
    }

    public List<RoleHierarchyEntity> getAllHierarchyRoles() throws Exception {
        return (List<RoleHierarchyEntity>) roleHierarchyRepository.findAll();
    }


    private void updateRoleHierarchyProvider(){
        Map<String, List<String>> roleHierarchyMap = new TreeMap<String, List<String>>();
        roleHierarchyRepository.findAll().forEach(role -> roleHierarchyMap.put(role.getRole(),role.getRoleHierarchyList()));
        roleHierarchyProvider.reInit(RoleHierarchyUtils.roleHierarchyFromMap(roleHierarchyMap));
    }

}
