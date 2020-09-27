package net.mkhalili96.dynamic_role_hierarchy.model.service.userdetailservice;

import lombok.extern.slf4j.Slf4j;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.Principal.SinaUserPrincipal;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.Role;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;
import net.mkhalili96.dynamic_role_hierarchy.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class SinaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getUserRolesSet().stream().map(Role::getRoleName).toArray(String[]::new);
        log.info("User roles are (getAuthorities) : {}", userRoles);
        return AuthorityUtils.createAuthorityList(userRoles);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        log.info("User roles (findByUsername): {}", user.getUserRolesSet());

        return new SinaUserPrincipal(user);
    }
}
