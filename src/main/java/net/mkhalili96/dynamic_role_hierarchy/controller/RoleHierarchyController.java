package net.mkhalili96.dynamic_role_hierarchy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.RoleHierarchyEntity;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.view.View;
import net.mkhalili96.dynamic_role_hierarchy.model.service.impl.RoleHierarcyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sina/roles/hierarchy")
public class RoleHierarchyController {

    @Autowired
    RoleHierarcyService service;

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<RoleHierarchyEntity> save(@RequestBody RoleHierarchyEntity entity) throws Exception {
        return ResponseEntity.ok(service.save(entity));
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping()
    public ResponseEntity<RoleHierarchyEntity> update(@RequestBody RoleHierarchyEntity entity) throws Exception {
        return ResponseEntity.ok(service.update(entity));
    }


    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        Map map = new HashMap();
        map.put("message", "hierarchy role deleted successfully");
        map.put("status", 200);
        return ResponseEntity.ok(map);
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity find(@RequestParam(required = false) String role, @RequestParam(required = false) Long id) throws Exception {

        if (id != null) {
            return ResponseEntity.ok(service.getRole(id));
        } else if (role != null) {
            return ResponseEntity.ok(service.getRoleByName(role));
        } else {
            return ResponseEntity.ok(service.getAllHierarchyRoles());
        }
    }
}
