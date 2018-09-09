package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.data.dto.UserDTO;
import com.wolverineteam.ngpuppies.exception.*;
import com.wolverineteam.ngpuppies.models.Currency;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.*;
import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.utils.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

    @PostMapping("users/create/")
    public void createUser(@RequestBody UserDTO user) throws FieldCantBeNullException, EikCanContainOnlyDigitsException,
            InvalidRoleException, PasswordInvalidInputException, UsernameInvalidInputException,
            UsernameAlreadyExistException, EikAlreadyExistException {

        HashSet<String> users = userService.getAll().stream()
                .map(User::getUsername).collect(Collectors.toCollection(HashSet::new));
        if (users.contains(user.getUsername())) {
            throw new UsernameAlreadyExistException("This username already exist!");
        }

        HashSet<String> eiks = userService.getAll().stream()
                .map(User::getEik).collect(Collectors.toCollection(HashSet::new));
        if (eiks.contains(user.getEik())) {
            throw new EikAlreadyExistException("This eik already exist");
        }

        userService.checkForUserDtoExceptions(user);
        userService.create(user);
    }

    @PutMapping("users/update/")
    public void updateUser(@RequestBody UserDTO user) throws PasswordInvalidInputException,
            EikCanContainOnlyDigitsException, FieldCantBeNullException, InvalidRoleException,
            UsernameInvalidInputException {
        userService.checkForUserDtoExceptions(user);
        userService.update(user);
    }

    @DeleteMapping("users/delete/{id}")
    public void deleteUser(@PathVariable("id") String stringId) throws UserDoesNotExistException {
        int userId = Integer.parseInt(stringId);

        HashSet<Integer> users = userService.getAll().stream()
                .map(User::getUserId).collect(Collectors.toCollection(HashSet::new));

        if (!users.contains(userId)) {
            throw new UserDoesNotExistException("This user does not exist!");
        }

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
    public void createBill(@RequestBody BillDTO bill) throws SubscriberNotFountException, ServiceDoesNotExistException, CurrencyDoesNotExistException, FieldCantBeNullException, InvalidTimePeriodException {
        HashSet<String> subscribers = subscriberService.getAllTelecomsSubscribers().stream()
                .map(SubscriberDAO::getPhoneNumber).collect(Collectors.toCollection(HashSet::new));

        if (!subscribers.contains(bill.getPhoneNumber())) {
            throw new SubscriberNotFountException("The subscriber does not exist!");
        }

        HashSet<String> services = serviceService.getAll().stream()
                .map(Service::getService).collect(Collectors.toCollection(HashSet::new));

        if (!services.contains(bill.getService())) {
            throw new ServiceDoesNotExistException("This service does not exist!");
        }

        HashSet<String> currencies = currencyService.getAll().stream()
                .map(Currency::getCurrency).collect(Collectors.toCollection(HashSet::new));

        if (!currencies.contains(bill.getCurrency())) {
            throw new CurrencyDoesNotExistException("This currency does not exist!");
        }

        if (bill.getStartDate().equals("")) {
            throw new FieldCantBeNullException("The Start date can't be null!");
        }

        if (bill.getEndDate().equals("")) {
            throw new FieldCantBeNullException("The End Date can't be null!");
        }

        if (bill.getAmount() == 0) {
            throw new FieldCantBeNullException("The amount can't be 0!");
        }

        Date startDate = new DateParser().getDateFromString(bill.getStartDate());
        Date endDate = new DateParser().getDateFromString(bill.getEndDate());

        if (startDate.after(endDate) || startDate.equals(endDate)) {
            throw new InvalidTimePeriodException("The start date can't be after or equal to the end date!");
        }

        billService.createBill(bill);
    }

}