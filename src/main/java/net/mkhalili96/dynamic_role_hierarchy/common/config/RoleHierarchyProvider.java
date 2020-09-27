package net.mkhalili96.dynamic_role_hierarchy.common.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
public class RoleHierarchyProvider {

    private static RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();

    @Bean
    public RoleHierarchy roleHierarchy() {
        return this.roleHierarchy;
    }

    @PostConstruct
    public void init(){
        this.roleHierarchy.setHierarchy("ADMIN > USER");
    }

    public void reInit(String rolesRelation){
        this.roleHierarchy.setHierarchy(rolesRelation);
    }
}
