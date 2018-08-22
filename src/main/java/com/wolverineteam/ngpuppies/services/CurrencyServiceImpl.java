package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.CurrencyRepository;
import com.wolverineteam.ngpuppies.models.Currency;
import com.wolverineteam.ngpuppies.services.base.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency getById(String id) {
        int currencyId = Integer.parseInt(id);
        return currencyRepository.getById(currencyId);
    }

    @Override
    public List<Currency> getAll() {
        return currencyRepository.getAll();
    }

    @Override
    public void update(String id, Currency currency) {
        int currencyId = Integer.parseInt(id);
        currencyRepository.update(currencyId, currency);
    }

    @Override
    public void create(Currency currency) {
        currencyRepository.create(currency);
    }

    @Override
    public void delete(String id) {
        int currencyId = Integer.parseInt(id);
        currencyRepository.delete(currencyId);
    }
}
