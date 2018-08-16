package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.models.Currency;
import com.wolverineteam.ngpuppies.services.base.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class Controller {

    @Autowired
    private CurrencyService currencyService;

    public Controller(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("currencies/{id}")
    public Currency getCurrencyById(@PathVariable("id") String currencyId) {
        int id = Integer.parseInt(currencyId);
        return currencyService.getById(id);
    }
}
