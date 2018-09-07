package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.data.dto.UserDTO;
import com.wolverineteam.ngpuppies.exception.EikCanContainOnlyDigitsException;
import com.wolverineteam.ngpuppies.exception.FieldCantBeNullException;
import com.wolverineteam.ngpuppies.exception.InvalidRoleException;
import com.wolverineteam.ngpuppies.models.Currency;
import com.wolverineteam.ngpuppies.models.Role;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.*;
import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private UserService userService;
    private BillService billService;
    private SubscriberService subscriberService;
    private ServiceService serviceService;
    private CurrencyService currencyService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, BillService billService, SubscriberService subscriberService,
                           ServiceService serviceService, CurrencyService currencyService, RoleService roleService) {
        this.userService = userService;
        this.billService = billService;
        this.subscriberService = subscriberService;
        this.serviceService = serviceService;
        this.currencyService = currencyService;
        this.roleService = roleService;
    }

    @GetMapping("users/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.loadUserByUsername(username);
    }

    @PostMapping("users/create/")
    public void createUser(@RequestBody UserDTO user) throws FieldCantBeNullException, EikCanContainOnlyDigitsException, InvalidRoleException {

        checkerForUserDtoExceptions(user);
        userService.create(user);
    }

    @PutMapping("users/update/")
    public void updateUser(@RequestBody UserDTO user) throws EikCanContainOnlyDigitsException, FieldCantBeNullException, InvalidRoleException {

        checkerForUserDtoExceptions(user);
        userService.update(user);
    }

    @DeleteMapping("users/delete/{id}")
    public void deleteUser(@PathVariable("id") String stringId) {
        int userId = Integer.parseInt(stringId);
        userService.deleteUser(userId);
    }

    @GetMapping("users/")
    public List<User> getAllUsers(HttpServletRequest request) {
        return userService.getAll();
    }

    @GetMapping("subscribers/")
    public List<SubscriberDAO> getAllTelecomsSubscribers() {
        return subscriberService.getAllTelecomsSubscribers();
    }

    @GetMapping("currencies/")
    public List<Currency> getAllCurrencies() {
        return currencyService.getAll();
    }

    @GetMapping("services/")
    public List<Service> getAllServices() {
        return serviceService.getAll();
    }

    @PostMapping("bills/create/")
    public void createBill(@Valid @RequestBody BillDTO bill, BindingResult result) {
        if (result.hasErrors()) {
            return;
        }

        billService.createBill(bill);
    }

    private void checkerForUserDtoExceptions(UserDTO user) throws FieldCantBeNullException, EikCanContainOnlyDigitsException, InvalidRoleException {

        if (user.getUsername().equals("")) {
            throw new FieldCantBeNullException("Username can't be null!");
        }

        if (user.getPassword().equals("")) {
            throw new FieldCantBeNullException("Password can't be null!");
        }

        if (user.getEik().equals("")) {
            throw new FieldCantBeNullException("Eik can't be null!");

        }

        for (int i = 0; i < user.getEik().length(); i++) {
            if (!Character.isDigit(user.getEik().charAt(i))) {
                throw new EikCanContainOnlyDigitsException("Eik number can contain only digits!");
            }
        }

        if (user.getRole().equals("")) {
            throw new FieldCantBeNullException("Role can't be null!");
        }

        HashSet<String> roles = roleService.getAllRoles().stream()
                .map(Role::getRole).collect(Collectors.toCollection(HashSet::new));
        if (!roles.contains(user.getRole())) {
            throw new InvalidRoleException("Invalid role!");
        }
    }
}