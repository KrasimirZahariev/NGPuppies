package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Role;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.RoleService;
import com.wolverineteam.ngpuppies.services.base.UserService;
import com.wolverineteam.ngpuppies.services.dto.BillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private UserService userService;
    private BillService billService;

    @Autowired
    public AdminController(UserService userService, BillService billService) {
        this.userService = userService;
        this.billService = billService;
    }

    @GetMapping("users/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.loadUserByUsername(username);
    }

    //when we create front-end we should check this one for retrieving the error messages in the form
    @PostMapping("users/create/")
    public void createUser(@Valid @RequestBody User user) {
        userService.create(user);
    }

    //this front-end form should be tested too
    @PutMapping("users/update/")
    public void updateUser(@Valid @RequestBody User user) {
        userService.update(user);
    }

    @GetMapping("users/")
    public List<User> getAllUsers(HttpServletRequest request) {
        System.out.println(request.getRemoteAddr());
        System.out.println();
        return userService.getAll();
    }

    //front-end for testing
    @PostMapping("bills/create/")
    public void createBill(@Valid @RequestBody BillDTO bill) {
        billService.createBill(bill);
    }
}