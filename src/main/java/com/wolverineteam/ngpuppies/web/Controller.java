package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.models.Currency;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.CurrencyService;
import com.wolverineteam.ngpuppies.services.base.SubscriberService;
import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class Controller {

    private CurrencyService currencyService;
    private UserService userService;
    private SubscriberService subscriberService;

    /** ---------------------------------------  CURRENCY METHODS*/

    @Autowired
    public Controller(CurrencyService currencyService, UserService userService, SubscriberService subscriberService) {
        this.currencyService = currencyService;
        this.userService = userService;
        this.subscriberService = subscriberService;
    }

    @GetMapping("currencies/{id}")
    public Currency getCurrencyById(@PathVariable("id") String currencyId) {
        int id = Integer.parseInt(currencyId);
        return currencyService.getById(id);
    }

    @GetMapping("currencies/")
    public List<Currency> getAllCurrencies() {
        return currencyService.getAll();
    }

    @PostMapping("currencies/create")
    public void createCurrency(@RequestBody Currency currency) {
        currencyService.create(currency);
    }

    @PutMapping("currencies/update/{id}")
    public void updateCurrency(@PathVariable("id") String currencyId, @RequestBody Currency currency) {
        int id = Integer.parseInt(currencyId);
        currencyService.update(id, currency);
    }

    @DeleteMapping("currencies/delete/{id}")
    public void deleteCurrency(@PathVariable("id") String currencyId) {
        int id = Integer.parseInt(currencyId);
        currencyService.delete((id));
    }

    /** -------------------------------------------------  USER METHODS*/

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable("id") String userId) {
        int id = Integer.parseInt(userId);
        return userService.getById(id);
    }

    @GetMapping("users/")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("users/create")
    public void createUser(@RequestBody User user) {
        userService.create(user);
    }

    @PutMapping("users/update/{id}")
    public void updateUser(@PathVariable("id") String userId, @RequestBody User user) {
        int id = Integer.parseInt(userId);
        userService.update(id, user);
    }

    @DeleteMapping("users/delete/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        int id = Integer.parseInt(userId);
        userService.delete((id));
    }

    /**
     * ----------------------------------------------  SUBSCRIBER METHODS
     */

    @GetMapping("subscribers/{id}")
    public Subscriber getSubscriberById(@PathVariable("id") String subscriberId) {
        return subscriberService.getById(subscriberId);
    }
}
