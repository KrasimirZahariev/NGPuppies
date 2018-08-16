package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.models.Currency;

import java.util.List;

public interface CurrencyRepository {

    Currency getById(int id);

    List<Currency> getAll();

    void update(int id, Currency currency);

    void create(Currency currency);

    void delete(int id);
}
