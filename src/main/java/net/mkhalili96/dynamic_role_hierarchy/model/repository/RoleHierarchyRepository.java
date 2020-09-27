package net.mkhalili96.dynamic_role_hierarchy.model.repository;

import net.mkhalili96.dynamic_role_hierarchy.model.entity.RoleHierarchyEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleHierarchyRepository extends CrudRepository<RoleHierarchyEntity,Long> {
    public Boolean existsByRole(String role);
    public RoleHierarchyEntity findByRole(String role);
}
