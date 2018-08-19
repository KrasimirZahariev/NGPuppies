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
public class BillRepositoryImpl implements BillRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public BillRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Bill getById(int id) {
        Bill bill = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bill = session.get(Bill.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
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
            String query = "select b.id, b.service.service,b.subscriber.phoneNumber, b.subscriber.firstName,b.subscriber.lastName, b.startDate, " +
                    "b.endDate, b.amount, b.currency.currency, b.paymentDate " +
                    "from Bill as b " +
                    "join fetch Subscriber as s on b.subscriber = s.phoneNumber " +
                    "join fetch User as u on s.bank.userId = u.userId " +
                    "where s.bank.userId = :bankId and b.paymentDate is NULL";
            bills = session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return bills;
    }

    @Override
    public void payBills(List<Integer> bills) {
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
    public List<Bill> getDescendingPaymentsByBankId(int bankId) {
        List<Bill> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.id, b.service.service, b.subscriber.phoneNumber,b.subscriber.firstName,b.subscriber.lastName, b.startDate, " +
                    "b.endDate, b.amount, b.currency.currency, b.paymentDate " +
                    "from Bill as b " +
                    "join fetch Subscriber as s on b.subscriber = s.phoneNumber " +
                    "join fetch User as u on s.bank.userId = u.userId " +
                    "where s.bank.userId = :bankId and b.paymentDate is Not NULL " +
                    "order by b.paymentDate DESC ";
            bills = session.createQuery(query)
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
    public List<Object[]> getMinAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval) {
        List<Object[]> records = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try (Session session = sessionFactory.openSession()) {
            Date startDate = new Date(format.parse(timeInterval.get(0)).getTime());
            Date endDate = new Date(format.parse(timeInterval.get(1)).getTime());
            int bankId = Integer.parseInt(timeInterval.get(2));
            session.beginTransaction();
            String query = "select s.firstName, s.lastName, s.phoneNumber, b.currency.currency, " +
                    "avg(b.amount), max(b.amount), b.paymentDate " +
                    "from Bill as b " +
                    "join Subscriber as s on b.subscriber = s.phoneNumber " +
                    "where b.paymentDate >= :startDate and b.paymentDate <= :endDate and s.bank.userId = :bankId " +
                    "group by b.subscriber";
            records = session.createQuery(query)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setParameter("bankId",bankId)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return records;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Bill> getPaidServicesByBankId(int bankId) {
        List<Bill> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select distinct b.service.service " +
                    "from Bill as b " +
                    "join fetch Subscriber as s on b.subscriber = s.phoneNumber " +
                    "join fetch Service as se on b.service.serviceId = se.serviceId " +
                    "where s.bank.userId = :bankId and b.paymentDate is Not NULL";
            bills = session.createQuery(query)
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
    public List<Object[]> getTenBiggestPaymentsByBankId(int bankId) {
        List<Object[]> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.id, b.subscriber.phoneNumber,b.subscriber.firstName,b.subscriber.lastName, 'BGN' as Currency, " +
                    "sum(b.amount*(case when b.currency.currency != 'bgn' then  b.currency.exchangeRate else 1 end)) as SumAmount " +
                    "from Bill as b " +
                    "where b.subscriber.bank.userId = :bankId and b.paymentDate is Not NULL " +
                    "group by b.subscriber " +
                    "order by SumAmount DESC";
            bills = session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .setMaxResults(10)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bills;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Bill> getTenMostRecentPaymentsByBankId(int bankId) {
        List<Bill> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.id, b.service.service, b.subscriber.phoneNumber,b.subscriber.firstName,b.subscriber.lastName, b.startDate, " +
                    "b.endDate, b.amount, b.currency.currency, b.paymentDate " +
                    "from Bill as b " +
                    "join fetch Subscriber as s on b.subscriber = s.phoneNumber " +
                    "join fetch User as u on s.bank.userId = u.userId " +
                    "where s.bank.userId = :bankId and b.paymentDate is Not NULL " +
                    "order by b.paymentDate DESC";
            bills = session.createQuery(query)
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
