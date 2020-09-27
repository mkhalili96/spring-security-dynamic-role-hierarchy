package net.mkhalili96.dynamic_role_hierarchy.model.repository;

import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

    List<User> findAllByIsEnabled(Boolean enable);
}
