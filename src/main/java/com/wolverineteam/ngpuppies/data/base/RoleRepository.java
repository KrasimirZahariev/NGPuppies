package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.models.Role;

public interface RoleRepository {

    Role loadRoleByRoleName(String roleName);
}
