package com.wolverineteam.ngpuppies.data;

import com.wolverineteam.ngpuppies.data.base.BillRepository;
import com.wolverineteam.ngpuppies.models.Bill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BillRepositoryImpl implements BillRepository{

    private SessionFactory sessionFactory;

    @Autowired
    public BillRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Bill getById(int id) {
        Bill bill = null;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bill = session.get(Bill.class, id);
            session.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return bill;
    }

    @Override
    public void createBill(Bill bill) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(bill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Bill> getUnpaidBillsByBankId(int bankId) {
        List<Bill> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.id, b.service, b.subscriber, b.startDate, " +
                    "b.endDate, b.amount, b.currency, b.paymentDate " +
                    "from Bill as b " +
                    "join fetch Subscriber as s on b.subscriber = s.phoneNumber " +
                    "join fetch User as u on s.bank.userId = u.userId " +
                    "where s.bank.userId = :bankId and b.paymentDate is NULL";
            bills =  session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bills;
    }

    @Override
    public void pay(List<Integer> bills) {
        Bill paidBill;
        try (Session session = sessionFactory.openSession()) {
            for (Integer bill : bills) {
                paidBill = getById(bill);
                paidBill.setPaymentDate(new Date(System.currentTimeMillis()));
                session.beginTransaction();
                session.update(paidBill);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Bill> getAllPaidSorted(int bankId) {
        List<Bill> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.id, b.service, b.subscriber, b.startDate, " +
                    "b.endDate, b.amount, b.currency, b.paymentDate " +
                    "from Bill as b " +
                    "join fetch Subscriber as s on b.subscriber = s.phoneNumber " +
                    "join fetch User as u on s.bank.userId = u.userId " +
                    "where s.bank.userId = :bankId and b.paymentDate is Not NULL " +
                    "order by b.paymentDate DESC ";
            bills =  session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bills;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getMinAndAvgPaymentInTimeInterval(List<String> timeInterval) {
        List<String> records = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try (Session session = sessionFactory.openSession()) {
            Date startDate = new Date(format.parse(timeInterval.get(0)).getTime());
            Date endDate = new Date(format.parse(timeInterval.get(1)).getTime());

            session.beginTransaction();
            String query = "select s.firstName, s.lastName, s.phoneNumber, c.currency, avg(b.amount), max(b.amount), b.paymentDate " +
                    "from Bill as b " +
                    "join Subscriber as s on b.subscriber = s.phoneNumber " +
                    "join User as u on s.bank.userId = u.userId " +
                    "join Currency as c on b.currency = c.currency " +
                    "where b.paymentDate >= :startDate and b.paymentDate <= :endDate " +
                    "group by b.subscriber";
            records =  session.createQuery(query)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return records;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Bill> getAllPaidServices(int bankId) {
        List<Bill> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select distinct b.service.service " +
                    "from Bill as b " +
                    "join fetch Subscriber as s on b.subscriber = s.phoneNumber " +
                    "join fetch Service as se on b.service.serviceId = se.serviceId " +
                    "where s.bank.userId = :bankId and b.paymentDate is Not NULL";
            bills =  session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bills;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Bill> GetTenMostRecentPaymentsByBankId(int bankId) {
        List<Bill> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.id, b.service, b.subscriber, b.startDate, " +
                    "b.endDate, b.amount, b.currency, b.paymentDate " +
                    "from Bill as b " +
                    "join fetch Subscriber as s on b.subscriber = s.phoneNumber " +
                    "join fetch User as u on s.bank.userId = u.userId " +
                    "where s.bank.userId = :bankId and b.paymentDate is Not NULL " +
                    "order by b.paymentDate DESC";
            bills =  session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .setMaxResults(10)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bills;
    }
}
