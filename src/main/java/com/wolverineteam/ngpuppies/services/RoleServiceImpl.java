package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.RoleRepository;
import com.wolverineteam.ngpuppies.models.Role;
import com.wolverineteam.ngpuppies.services.base.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role loadRoleByRoleName(String roleName) {
        return roleRepository.loadRoleByRoleName(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }
}
