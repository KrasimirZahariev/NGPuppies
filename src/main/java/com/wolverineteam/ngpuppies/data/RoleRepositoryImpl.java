package com.wolverineteam.ngpuppies.data;

import com.wolverineteam.ngpuppies.data.base.RoleRepository;
import com.wolverineteam.ngpuppies.models.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Role loadRoleByRoleName(String roleName) {
        Role role = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "from Role as r " +
                    "where  r.role = :roleName";
            Query q = session.createQuery(query).setParameter("roleName", roleName);
            role = (Role)q.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return role;
    }
}
