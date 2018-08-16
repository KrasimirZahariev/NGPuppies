package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.models.Currency;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.services.base.CurrencyService;
import com.wolverineteam.ngpuppies.services.base.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class Controller {

    /** ---------------------------------------  CURRENCY METHODS*/

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ServiceService serviceService;


    public Controller(CurrencyService currencyService, ServiceService serviceService) {
        this.currencyService = currencyService;
        this.serviceService = serviceService;
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

    /** -------------------------------------------------  SERVICE METHODS*/



}
