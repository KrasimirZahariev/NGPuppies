package com.wolverineteam.ngpuppies.ServiceTests;

import com.wolverineteam.ngpuppies.data.base.RoleRepository;
import com.wolverineteam.ngpuppies.models.Role;
import com.wolverineteam.ngpuppies.services.RoleServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleServiceImpl roleService;

    @Test
    public void loadRoleByRoleName_ReturnCorrectRole() {
        Mockito.when(roleRepository.loadRoleByRoleName("ROLE_ADMIN"))
                .thenReturn(new Role("ROLE_ADMIN"));

        Role result = roleService.loadRoleByRoleName("ROLE_ADMIN");

        Assert.assertEquals("ROLE_ADMIN", result.getRole());
    }

}
