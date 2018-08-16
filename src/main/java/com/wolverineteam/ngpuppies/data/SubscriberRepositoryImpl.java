package com.wolverineteam.ngpuppies.data;

import com.wolverineteam.ngpuppies.data.base.SubscriberRepository;
import com.wolverineteam.ngpuppies.models.Subscriber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriberRepositoryImpl implements SubscriberRepository {

    SessionFactory sessionFactory;

    @Autowired
    public SubscriberRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Subscriber getById(String id) {
        Subscriber subscriber = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            subscriber = session.get(Subscriber.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return subscriber;
    }
}
