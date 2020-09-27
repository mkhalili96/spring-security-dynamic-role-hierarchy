package net.mkhalili96.dynamic_role_hierarchy.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.NonNull;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.view.View;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "S_ROLE")
@Table(name = "S_ROLE")
public class Role implements Serializable {
    private static final long serialVersionUID = 2L;

    @JsonView(View.UserView.externalView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    @JsonView(View.UserView.externalView.class)
    @NonNull
    @Column(name = "role_name", unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "userRolesSet", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> userEntities;


    public Role() {
    }

    public Role(@NonNull String roleName) {
        this.roleName = roleName;
    }

    public Role(@NonNull String roleName, List<User> userEntities) {
        this.roleName = roleName;
        this.userEntities = userEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<User> userEntities) {
        this.userEntities = userEntities;
    }
}