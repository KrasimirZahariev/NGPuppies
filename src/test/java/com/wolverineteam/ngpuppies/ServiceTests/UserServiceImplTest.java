package com.wolverineteam.ngpuppies.ServiceTests;

import com.wolverineteam.ngpuppies.data.base.UserRepository;
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
                .thenReturn(new User("MockUser1", "UsersPass", new Role("Client"), "12345678"));

        User result = userService.getById(1);

        Assert.assertEquals("MockUser1", result.getUsername());
    }

    @Test
    public void GetAllUsers_ReturnCorrectUsers() {
        Mockito.when(mockUserRepository.getAll())
                .thenReturn(Arrays.asList(
                        new User("MockUser1", "UsersPass", new Role("Client"), "12345678"),
                        new User("MockUser2", "UsersPass", new Role("Client"), "12345678"),
                        new User("MockUser3", "UsersPass", new Role("Client"), "12345678"),
                        new User("MockUser4", "UsersPass", new Role("Client"), "12345678"),
                        new User("MockUser5", "UsersPass", new Role("Client"), "12345678")
                ));

        List<User> result = userService.getAll();

        Assert.assertEquals(5,result.size());
    }

    @Test
    public void UpdateUserWhenUpdatedUserGiven_ReturnCorrectUpdatedUser(){
        User mockUser1 = new User("MockUser1", "UsersPass", new Role("Client"), "12345678");
        User mockUser2 = new User("MockUser2", "UsersPass", new Role("Client"), "12345678");

        doNothing().when(mockUserRepository).update(isA(Integer.class),isA(User.class));
        userService.update("1",mockUser2);

        Mockito.verify(mockUserRepository,times(1)).update(1,mockUser2);

    }

    @Test
    public void CreateNewUserWhenGivenUser_ReturnsCorrectNewUser(){
        User mockUser1 = new User("MockUser1", "UsersPass", new Role("Client"), "12345678");

        doNothing().when(mockUserRepository).create(isA(User.class));
        userService.create(mockUser1);

        verify(mockUserRepository,times(1)).create(mockUser1);
    }

    @Test
    public void DeleteUserWhenGivenId_ReturnDeleteUser(){
        User mockUser1 = new User("MockUser1", "UsersPass", new Role("Client"), "12345678");

        doNothing().when(mockUserRepository).delete(isA(Integer.class));
        userService.delete("1");

        verify(mockUserRepository,times(1)).delete(1);
    }
}
