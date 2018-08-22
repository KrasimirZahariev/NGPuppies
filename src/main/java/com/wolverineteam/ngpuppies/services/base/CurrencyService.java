package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.Currency;

import java.util.List;

public interface CurrencyService {

    Currency getById(String id);

    List<Currency> getAll();

    void update(String id, Currency currency);

    void create(Currency currency);

    void delete(String id);
}
