package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.Role;

public interface RoleService {

    Role loadRoleByRoleName(String roleName);
}
