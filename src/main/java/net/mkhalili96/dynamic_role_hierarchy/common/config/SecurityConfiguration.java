package net.mkhalili96.dynamic_role_hierarchy.common.config;

import net.mkhalili96.dynamic_role_hierarchy.model.entity.Role;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;
import net.mkhalili96.dynamic_role_hierarchy.model.repository.RoleRepository;
import net.mkhalili96.dynamic_role_hierarchy.model.repository.UserRepository;
import net.mkhalili96.dynamic_role_hierarchy.model.service.userdetailservice.SinaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import javax.annotation.PostConstruct;
import java.util.*;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${sinarole.roles}")
    private Set<String> ROLES;


    @Autowired
    private SinaUserDetailsService jwtUserDetailsService;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    RoleHierarchyProvider roleHierarchyProvider;
//    @Autowired
//    private RoleHierarchy roleHierarchy;



//    private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
//        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler     = new DefaultWebSecurityExpressionHandler();
//        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy);
//        return defaultWebSecurityExpressionHandler;
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(9);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setPrefix("");
        authorityMapper.setDefaultAuthority("USER");
        return authorityMapper;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable().cors().and()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/authenticate").permitAll().
                // all other requests need to be authenticated
                        anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .httpBasic();
    }



    @PostConstruct
    void initalUserAndRoles() {

        for (String role : ROLES) {
            if (!roleRepository.existsByRoleName(role)){
                roleRepository.save(new Role(role));
            }
        }

        if (!userRepo.existsByUsername("root")) {
            Role role = roleRepository.findByRoleName("ADMIN");
            userRepo.save(new User("root", "P@$$w0rld", "root","", "", "", "", "", true, true, true, true, new HashSet<Role>(Arrays.asList(role))));
        }
    }
}
