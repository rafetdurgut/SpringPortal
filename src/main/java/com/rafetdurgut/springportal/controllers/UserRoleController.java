package com.rafetdurgut.springportal.controllers;

import com.rafetdurgut.springportal.models.UserRole;
import com.rafetdurgut.springportal.services.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;
    @Secured("ROLE_ADMIN")
    @PostMapping()
    public ResponseEntity<UserRole> saveRole(UserRole userRole)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/roles").toUriString());
        return ResponseEntity.created(uri).body(userRoleService.saveRole(userRole));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping()
    public ResponseEntity<List<UserRole>> getRoles()
    {
        return ResponseEntity.ok(userRoleService.getAll());
    }
}
