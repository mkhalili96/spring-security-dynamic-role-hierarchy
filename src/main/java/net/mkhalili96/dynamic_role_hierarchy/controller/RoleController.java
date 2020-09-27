package net.mkhalili96.dynamic_role_hierarchy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import net.mkhalili96.dynamic_role_hierarchy.controller.to.UserRoleTo;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.Role;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.view.View;
import net.mkhalili96.dynamic_role_hierarchy.model.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sina/roles")
public class RoleController {

    @Autowired
    RoleService roleService;
    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping()
    public ResponseEntity<User> addRole(@RequestBody UserRoleTo userRoleTo) throws Exception {
        return ResponseEntity.ok(roleService.addRole(userRoleTo.getUserId(), userRoleTo.getRoleId()));
    }
    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping()
    public ResponseEntity<User> removeRole(@RequestBody UserRoleTo userRoleTo) throws Exception {
        return ResponseEntity.ok(roleService.removeRole(userRoleTo.getUserId(), userRoleTo.getRoleId()));
    }
    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<Role>> getAllRoles() throws Exception {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
