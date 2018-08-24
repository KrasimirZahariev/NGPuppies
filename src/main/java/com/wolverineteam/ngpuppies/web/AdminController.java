package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Role;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.RoleService;
import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private UserService userService;
    private BillService billService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, BillService billService, RoleService roleService) {
        this.userService = userService;
        this.billService = billService;
        this.roleService = roleService;
    }

    @PostMapping("users/create/")
    public void createUser(@RequestBody User user) {
//        List<Role> roles = new ArrayList<>();
//        roles.add(this.roleService.loadRoleByRoleName("ROLE_ADMIN"));
//        User newUSer = new User("op", "op", roles, "3232");
        userService.create(user);
    }

    @PutMapping("users/update/{id}")
    public void updateUser(@PathVariable("id") String userId, @RequestBody User user) {
        userService.update(userId, user);
    }

    @GetMapping("users/")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @DeleteMapping("users/delete/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        userService.delete(userId);
    }

    @PostMapping("bills/create/")
    public void createBill(@RequestBody Bill bill) {
        billService.createBill(bill);
    }
}
