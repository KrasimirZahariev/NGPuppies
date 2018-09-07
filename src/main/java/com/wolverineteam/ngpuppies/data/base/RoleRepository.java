package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.models.Role;

import java.util.List;

public interface RoleRepository {

    Role loadRoleByRoleName(String roleName);

    List<Role> getAllRoles();
}
