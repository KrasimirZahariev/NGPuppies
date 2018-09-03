package com.wolverineteam.ngpuppies.ServiceTests;

import com.wolverineteam.ngpuppies.data.base.UserRepository;
import com.wolverineteam.ngpuppies.data.dto.UserDTO;
import com.wolverineteam.ngpuppies.models.Role;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository mockUserRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void getUserWhenUserIdGiven_ReturnCorrectUser() {
        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(new User("MockUser1", "UsersPass", new ArrayList<>(), "12345678"));

        User result = userService.getById(1);

        Assert.assertEquals("MockUser1", result.getUsername());
    }

    @Test
    public void GetAllUsers_ReturnCorrectUsers() {
        Mockito.when(mockUserRepository.getAll())
                .thenReturn(Arrays.asList(
                        new User("MockUser1", "UsersPass", new ArrayList<>(), "12345678"),
                        new User("MockUser2", "UsersPass", new ArrayList<>(), "12345678"),
                        new User("MockUser3", "UsersPass", new ArrayList<>(), "12345678"),
                        new User("MockUser4", "UsersPass", new ArrayList<>(), "12345678"),
                        new User("MockUser5", "UsersPass", new ArrayList<>(), "12345678")
                ));

        List<User> result = userService.getAll();

        Assert.assertEquals(5, result.size());
    }

    @Test
    public void UpdateUserWhenUpdatedUserGiven_ReturnCorrectUpdatedUser() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_ADMIN"));
        User mockUser = new User("123","0000",roles,"456");


        UserDTO mockUser2 = new UserDTO();
        mockUser2.setUsername("123");
        mockUser2.setRole("ROLE_ADMIN");
        mockUser2.setEik("456");
        mockUser2.setPassword("0000");

        doNothing().when(mockUserRepository).update(isA(User.class));
        userService.update( mockUser2);

        Mockito.verify(mockUserRepository, times(1)).update( mockUser);

    }

    @Test
    public void CreateNewUserWhenGivenUser_ReturnsCorrectNewUser() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_ADMIN"));
        User mockUser = new User("MockUser1","UsersPass",roles,"12345678");

        UserDTO mockUser1 = new UserDTO();
        mockUser1.setPassword("UsersPass");
        mockUser1.setUsername("MockUser1");
        mockUser1.setEik("12345678");
        mockUser1.setRole("ROLE_ADMIN");

        doNothing().when(mockUserRepository).create(isA(User.class));
        userService.create(mockUser1);

        verify(mockUserRepository, times(1)).create(mockUser);
    }

   // @Test
   // public void DeleteUserWhenGivenId_ReturnDeleteUser() {
   //     User mockUser1 = new User("MockUser1", "UsersPass", new ArrayList<>(), "12345678");
//
   //     doNothing().when(mockUserRepository).delete(isA(Integer.class));
   //     userService.delete("1");
//
   //     verify(mockUserRepository, times(1)).delete(1);
   // }
}
