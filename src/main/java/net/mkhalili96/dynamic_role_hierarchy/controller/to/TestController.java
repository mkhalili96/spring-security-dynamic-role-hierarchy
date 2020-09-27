package net.mkhalili96.dynamic_role_hierarchy.controller.to;

import com.fasterxml.jackson.annotation.JsonView;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.view.View;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/sina/test")
public class TestController {

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/ADMIN")
    public ResponseEntity<String> ADMIN() throws Exception {
        return ResponseEntity.ok("ADMIN");
    }
    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('MONITOR')")
    @GetMapping(value = "/MONITOR")
    public ResponseEntity<String> MONITOR() throws Exception {
        return ResponseEntity.ok("MONITOR");
    }
    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/USER")
    public ResponseEntity<String> USER() throws Exception {
        return ResponseEntity.ok("USER");
    }


    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('ONE')")
    @GetMapping(value = "/ONE")
    public ResponseEntity<String> ONE() throws Exception {
        return ResponseEntity.ok("ONE");
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('TWO')")
    @GetMapping(value = "/TWO")
    public ResponseEntity<String> TWO() throws Exception {
        return ResponseEntity.ok("TWO");
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('THREE')")
    @GetMapping(value = "/THREE")
    public ResponseEntity<String> THREE() throws Exception {
        return ResponseEntity.ok("THREE");
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('FOUR')")
    @GetMapping(value = "/FOUR")
    public ResponseEntity<String> FOUR() throws Exception {
        return ResponseEntity.ok("FOUR");
    }

    @JsonView(View.UserView.externalView.class)
    @PreAuthorize("hasAuthority('FIVE')")
    @GetMapping(value = "/FIVE")
    public ResponseEntity<String> FIVE() throws Exception {
        return ResponseEntity.ok("FIVE");
    }

}
