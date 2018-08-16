package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.CurrencyRepository;
import com.wolverineteam.ngpuppies.models.Currency;
import com.wolverineteam.ngpuppies.services.base.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency getById(int id) {
        return currencyRepository.getById(id);
    }

    @Override
    public List<Currency> getAll() {
        return currencyRepository.getAll();
    }

    @Override
    public void update(int id, Currency currency) {
        currencyRepository.update(id, currency);
    }

    @Override
    public void create(Currency currency) {
        currencyRepository.create(currency);
    }

    @Override
    public void delete(int id) {
        currencyRepository.delete(id);
    }
}
