package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.Currency;

import java.util.List;

public interface CurrencyService {

    Currency getById(int id);

    List<Currency> getAll();

    void update(int id, Currency currency);

    void create(Currency currency);

    void delete(int id);
}
