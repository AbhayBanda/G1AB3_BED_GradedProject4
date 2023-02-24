package com.greatlearning.ems.controller;


import com.greatlearning.ems.entity.Role;
import com.greatlearning.ems.service.RoleServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    RoleServiceImplementation roleServiceImplementation;

    @GetMapping("/roles")
    List<Role> getAllRoles() {
        return roleServiceImplementation.getRoleList();
    }
    @GetMapping("/roles/{id}")
    Role getRoleById(@PathVariable Integer id) {
        return roleServiceImplementation.getRoleById(id);
    }

    @PostMapping("/roles/add")
    Role addRole(@RequestBody Role role) {
        return roleServiceImplementation.createNewRole(role);
    }

    @PutMapping("/roles/update/{id}")
    Role updateRole(@PathVariable Integer id, @RequestBody Role role) {
        return roleServiceImplementation.updateRole(id, role);
    }

    @DeleteMapping("/roles/delete/{id}")
    String deleteRole(@PathVariable Integer id) {
        return roleServiceImplementation.deleteRole(id);
    }

}
