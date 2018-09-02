package com.wolverineteam.ngpuppies.data;

import com.wolverineteam.ngpuppies.data.base.SubscriberRepository;
import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.models.Subscriber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscriberRepositoryImpl implements SubscriberRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public SubscriberRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Subscriber getSubscriberById(String subscriberId, int bankId) {
        Subscriber subscriber = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            subscriber = session.get(Subscriber.class,subscriberId);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return subscriber;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SubscriberDAO getSubscriberDAOById(String subscriberId, int bankId) {
        List<SubscriberDAO> subscribers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select s.phoneNumber as phoneNumber, s.firstName as firstName, " +
                    "s.lastName as lastName, s.egn as egn " +
                    "from Subscriber s " +
                    "where s.bank.id = :bankId and s.phoneNumber = :subscriberId";
            subscribers = session.createQuery(query)
                    .setParameter("subscriberId", subscriberId)
                    .setParameter("bankId", bankId)
                    .setResultTransformer(Transformers.aliasToBean(SubscriberDAO.class))
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return subscribers.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SubscriberDAO getAllSubscribersByBankId(int bankId) {
        List<SubscriberDAO> subscribers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select s.phoneNumber as phoneNumber, s.firstName as firstName, " +
                    "s.lastName as lastName, s.egn as egn " +
                    "from Subscriber s " +
                    "where s.bank.id = :bankId";
            subscribers = session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .setResultTransformer(Transformers.aliasToBean(SubscriberDAO.class))
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return subscribers.get(0);
    }
}
