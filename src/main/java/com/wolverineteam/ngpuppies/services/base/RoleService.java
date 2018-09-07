package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.Role;

import java.util.List;

public interface RoleService {

    Role loadRoleByRoleName(String roleName);

    List<Role> getAllRoles();
}
