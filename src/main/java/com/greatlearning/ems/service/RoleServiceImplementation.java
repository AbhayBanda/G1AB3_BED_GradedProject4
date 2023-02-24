package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Role;
import com.greatlearning.ems.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplementation implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Integer id) {

        Role role = roleRepository.findById(id).get();
        return role;
    }

    @Override
    public Role createNewRole(Role role) {

        String prefix = "ROLE_";
        Role newRole = new Role();
        newRole.setName(prefix + role.getName());
        return roleRepository.save(newRole);
    }

    @Override
    public Role updateRole(Integer id, Role role) {

        Optional<Role> optionalRole = roleRepository.findById(id);
        if(!optionalRole.isPresent())
            return null;
        Role oldRole = optionalRole.get();
        if(role.getName() == null) {
            oldRole.setName(role.getName());
        }
        Role newRole = oldRole;
        roleRepository.save(newRole);
        return newRole;
    }

    @Override
    public String deleteRole(Integer id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        Role roleToDelete = optionalRole.get();
        roleRepository.delete(roleToDelete);
        return String.format("Deleted Role - %s", roleToDelete.getName());
    }
}
