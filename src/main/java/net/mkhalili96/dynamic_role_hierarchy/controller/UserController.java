package net.mkhalili96.dynamic_role_hierarchy.controller;


import com.fasterxml.jackson.annotation.JsonView;
import net.mkhalili96.dynamic_role_hierarchy.controller.to.UserRoleTo;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.view.View;
import net.mkhalili96.dynamic_role_hierarchy.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/sina/users")
public class UserController {
    @Autowired
    UserService userService;

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity save(@RequestBody User user) throws Exception {
            return ResponseEntity.ok(userService.save(user));
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping()
    public ResponseEntity<User> update(@RequestBody User user) throws Exception {
            return ResponseEntity.ok(userService.update(user));
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(value = "password")
    public ResponseEntity<User> changePassword(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.changePassword(user));
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map> delete(@PathVariable Long id) throws Exception {
        userService.delete(id);
        Map map = new HashMap();
        map.put("message", "account deleted successfully");
        map.put("status", 200);
        return ResponseEntity.ok(map);
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity find(@RequestParam(required = false) String username, @RequestParam(required = false) Long id) throws Exception {

        if (id != null) {
            return ResponseEntity.ok(userService.getUser(id));
        } else if (username != null) {
            return ResponseEntity.ok(userService.getUserByUsername(username));
        } else {
            return ResponseEntity.ok(userService.getAllUsers());
        }
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(value = "/active")
    public ResponseEntity<Map> activeAction(@RequestBody UserRoleTo to) throws Exception {
        userService.active(to.getUserId(), to.getActive());
        Map map = new HashMap();
        String message = to.getActive() ? "account activated successfully .." : "account deactivated successfully ..";
        map.put("message", message);
        map.put("status", 200);
        return ResponseEntity.ok(map);
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/active")
    public ResponseEntity<List<User>> getAllActiveUsers() throws Exception {
        return ResponseEntity.ok(userService.getAllActiveUsers());
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/deactive")
    public ResponseEntity<List<User>> getAllDeActiveUsers() throws Exception {
        return ResponseEntity.ok(userService.getAllDeActiveUsers());
    }

}
