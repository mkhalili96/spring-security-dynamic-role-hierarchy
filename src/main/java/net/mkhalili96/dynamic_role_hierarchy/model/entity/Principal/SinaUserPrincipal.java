package net.mkhalili96.dynamic_role_hierarchy.model.entity.Principal;

import lombok.extern.slf4j.Slf4j;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class SinaUserPrincipal implements UserDetails {

    private Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    private User user;


    public SinaUserPrincipal(User user) {
        System.out.println(user.getUsername() + " authorities updated");
        this.user = user;
        user.getUserRolesSet().stream().map(role -> role.getRoleName()).forEach(r ->
            authorities.add(new SimpleGrantedAuthority(r))
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
