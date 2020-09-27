package net.mkhalili96.dynamic_role_hierarchy.model.repository;

import net.mkhalili96.dynamic_role_hierarchy.model.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    public Boolean existsByRoleName(String role);
    public Role findByRoleName(String role);
}
