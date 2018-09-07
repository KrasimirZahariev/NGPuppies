package com.wolverineteam.ngpuppies.data;

import com.wolverineteam.ngpuppies.data.base.RoleRepository;
import com.wolverineteam.ngpuppies.exception.RequestCantBeProcessedException;
import com.wolverineteam.ngpuppies.models.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
            role = (Role) q.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }

        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            roles = session.createQuery("from Role ", Role.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }
        return roles;
    }
}
