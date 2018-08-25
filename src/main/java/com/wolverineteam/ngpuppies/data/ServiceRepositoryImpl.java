package com.wolverineteam.ngpuppies.data;

import com.wolverineteam.ngpuppies.data.base.ServiceRepository;
import com.wolverineteam.ngpuppies.models.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public ServiceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Service loadServiceByServiceName(String serviceName) {
        Service service = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "from Service as s where  service = :serviceName";
            Query q = session.createQuery(query).setParameter("serviceName", serviceName);
            service = (Service)q.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return service;
    }
}
