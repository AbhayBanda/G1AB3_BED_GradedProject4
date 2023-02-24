package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoleList();
    Role getRoleById(Integer id);
    Role createNewRole(Role role);
    Role updateRole(Integer id, Role role);
    String deleteRole(Integer id);
}
