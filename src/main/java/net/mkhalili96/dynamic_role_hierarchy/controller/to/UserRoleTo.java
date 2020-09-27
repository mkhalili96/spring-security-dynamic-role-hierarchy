package net.mkhalili96.dynamic_role_hierarchy.controller.to;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleTo implements Serializable {
    private Long userId;
    private Long roleId;
    private Long duration;
    private Boolean active;
}
