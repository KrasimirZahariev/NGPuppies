package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.data.dto.UserDTO;
import com.wolverineteam.ngpuppies.exception.FieldCantBeNullException;
import com.wolverineteam.ngpuppies.exception.ForbiddenSubscriberException;
import com.wolverineteam.ngpuppies.models.Currency;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.*;
import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private UserService userService;
    private BillService billService;
    private SubscriberService subscriberService;
    private ServiceService serviceService;
    private CurrencyService currencyService;

    @Autowired
    public AdminController(UserService userService, BillService billService, SubscriberService subscriberService,
                           ServiceService serviceService, CurrencyService currencyService) {
        this.userService = userService;
        this.billService = billService;
        this.subscriberService = subscriberService;
        this.serviceService = serviceService;
        this.currencyService = currencyService;
    }

    @GetMapping("users/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.loadUserByUsername(username);
    }

    //when we create front-end we should check this one for retrieving the error messages in the form
    @PostMapping("users/create/")
    public void createUser(@Valid @RequestBody UserDTO user, BindingResult result) throws FieldCantBeNullException {
        if (user.getUsername().equals("")) {
            throw new FieldCantBeNullException("Username can't be null!");
        }
        if (user.getPassword().equals("")) {
            throw new FieldCantBeNullException("Password can't be null!");
        }
        if (user.getEik().equals("")) {
            throw new FieldCantBeNullException("Eik can't be null!");
        }
        if (user.getRole().equals("")) {
            throw new FieldCantBeNullException("Role can't be null!");
        }

        userService.create(user);
    }

    //this front-end form should be tested too
    @PutMapping("users/update/")
    public void updateUser(@Valid @RequestBody UserDTO user, BindingResult result) {
        if (result.hasErrors()) {
            return;
        }
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

    //front-end for testing
    @PostMapping("bills/create/")
    public void createBill(@Valid @RequestBody BillDTO bill, BindingResult result) {
        if (result.hasErrors()) {
            return;
        }

        billService.createBill(bill);
    }
}