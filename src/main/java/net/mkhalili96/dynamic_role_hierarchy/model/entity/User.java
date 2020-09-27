package net.mkhalili96.dynamic_role_hierarchy.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.view.View;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "S_USER")
@Table(name = "S_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonView(View.UserView.externalView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @JsonView(View.UserView.externalView.class)
    @Column(unique = true)
    private String username;

    @JsonView(View.UserView.internalView.class)
    private String password;

    @JsonView(View.UserView.externalView.class)
    @Column
    private String fullname;

    @JsonView(View.UserView.externalView.class)
    @Column
    private String street;

    @JsonView(View.UserView.externalView.class)
    @Column
    private String city;

    @JsonView(View.UserView.externalView.class)
    @Column
    private String state;

    @JsonView(View.UserView.externalView.class)
    @Column
    private String zip;

    @JsonView(View.UserView.externalView.class)
    @Column
    private String phoneNumber;

    @JsonView(View.UserView.internalView.class)
    @Column
    private Boolean isAccountNonExpired = true;

    @JsonView(View.UserView.internalView.class)
    @Column
    private Boolean isAccountNonLocked = true;

    @JsonView(View.UserView.internalView.class)
    @Column
    private Boolean isCredentialsNonExpired = true;

    @JsonView(View.UserView.internalView.class)
    @Column
    private Boolean isEnabled = true;

    @JsonView(View.UserView.externalView.class)
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Set<Role> userRolesSet;

    public User(String username, String password, String fullname, String street, String city, String state, String zip, String phoneNumber, Boolean isAccountNonExpired, Boolean isAccountNonLocked, Boolean isCredentialsNonExpired, Boolean isEnabled, Set<Role> userRolesSet) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.userRolesSet = userRolesSet;
    }

    public User() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Set<Role> getUserRolesSet() {
        return userRolesSet;
    }

    public void setUserRolesSet(Set<Role> userRolesSet) {
        this.userRolesSet = userRolesSet;
    }

    // todo delete P@$$W0rld and use dto
    @PrePersist
    public void prePersist() {
        if (this.password.length() <= 55) {
            this.password = new BCryptPasswordEncoder(9).encode(password);
        }
    }

    // todo delete P@$$W0rld and use dto
    @PreUpdate
    public void preUpdate() {
        if (this.password.length() <= 55) {
            this.password = new BCryptPasswordEncoder(9).encode(password);
        }
    }
}