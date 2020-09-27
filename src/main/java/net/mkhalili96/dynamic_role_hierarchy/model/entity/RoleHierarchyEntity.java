package net.mkhalili96.dynamic_role_hierarchy.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.view.View;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class RoleHierarchyEntity {

    @JsonView(View.UserView.externalView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RoleHierarchy_ID")
    private Long id;

    @JsonView(View.UserView.externalView.class)
    @Column
    private String role;

    @JsonView(View.UserView.externalView.class)
    @ElementCollection
    private List<String> roleHierarchyList;

    @JsonView(View.UserView.externalView.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate = new Date();

    @JsonView(View.UserView.externalView.class)
    @Column
    private Boolean isMainRole = false;

    @PreUpdate
    public void preUpdate() {
       this.lastUpdate = new Date();
    }
}
