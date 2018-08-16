package com.wolverineteam.ngpuppies.data;

import com.wolverineteam.ngpuppies.data.base.CurrencyRepository;
import com.wolverineteam.ngpuppies.models.Currency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CurrencyRepositoryImpl implements CurrencyRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public CurrencyRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Currency getById(int id) {
        Currency currency = null;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            currency = session.get(Currency.class, id);
            session.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return currency;
    }

    @Override
    public List<Currency> getAll() {
        List<Currency> currencies = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            currencies = session.createQuery("from Currency", Currency.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return currencies;
    }

    @Override
    public void update(int id, Currency currency) {
        Currency oldCurrency = getById(id);
        oldCurrency.setCurrency(currency.getCurrency());
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(oldCurrency);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void create(Currency currency) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(currency);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        Currency currency = getById(id);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(currency);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
