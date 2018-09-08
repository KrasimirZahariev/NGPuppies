package com.wolverineteam.ngpuppies.data;

import com.wolverineteam.ngpuppies.data.base.BillRepository;
import com.wolverineteam.ngpuppies.data.dao.BillDAO;
import com.wolverineteam.ngpuppies.exception.RequestCantBeProcessedException;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
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
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Bill> getUnpaidBillsByBankId(int bankId) {
        List<Bill> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.id as billId, b.service.service as service, " +
                    "b.subscriber.phoneNumber as phoneNumber, b.subscriber.firstName as firstName, " +
                    "b.subscriber.lastName as lastName, b.startDate as startDate, " +
                    "b.endDate as endDate, round(b.amount,2) as amount, " +
                    "b.currency.currency as currency " +
                    "from Bill as b " +
                    "where b.subscriber.bank.userId = :bankId and b.paymentDate is NULL";
            bills = session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .setResultTransformer(Transformers.aliasToBean(BillDAO.class))
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }
        return bills;
    }


    @Override
    @SuppressWarnings("unchecked")
    public Set<Integer> getSetOfUnpaidBillsByBankId(int bankId) {
        List<Integer> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.id as billId " +
                    "from Bill as b " +
                    "where b.subscriber.bank.userId = :bankId and b.paymentDate is NULL";
            bills = session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }

        return new HashSet<>(bills);
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
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Bill> getSubscribersPaymentsHistoryDescendingByBankId(int bankId) {
        List<Bill> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.id as billId, b.service.service as service, " +
                    "b.subscriber.phoneNumber as phoneNumber, b.subscriber.firstName as firstName, " +
                    "b.subscriber.lastName as lastName, round(b.amount,2) as amount, " +
                    "b.currency.currency as currency, b.paymentDate as paymentDate " +
                    "from Bill as b " +
                    "where b.subscriber.bank.userId = :bankId " +
                    "and b.paymentDate is Not NULL " +
                    "order by b.paymentDate DESC ";
            bills = session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .setResultTransformer(Transformers.aliasToBean(BillDAO.class))
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }

        return bills;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillDAO> getMaxAndAvgPaymentInTimeIntervalByBankId(int bankId, String phoneNumber,
                                                                   Date startDate, Date endDate) {
        List<BillDAO> records = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select s.firstName as firstName, s.lastName as lastName, " +
                    "s.phoneNumber as phoneNumber, b.currency.currency as currency, " +
                    "round(avg(b.amount*(case when b.currency.currency != 'BGN' " +
                    "then  b.currency.exchangeRate else 1 end)),2) as avg, round(max(b.amount*(case when b.currency.currency != 'BGN' " +
                    "then  b.currency.exchangeRate else 1 end)),2) as max " +
                    "from Bill as b " +
                    "join Subscriber as s on b.subscriber = s.phoneNumber " +
                    "where b.paymentDate >= :startDate and b.paymentDate <= :endDate and s.bank.userId = :bankId " +
                    "and s.phoneNumber = :phoneNumber and b.paymentDate is not null " +
                    "group by b.subscriber";
            records = session.createQuery(query)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setParameter("bankId", bankId)
                    .setParameter("phoneNumber", phoneNumber)
                    .setResultTransformer(Transformers.aliasToBean(BillDAO.class))
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }

        return records;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Service> getSubscriberPaidServicesByBankId(int bankId, String subscriberId) {
        List<Service> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select distinct b.service " +
                    "from Bill as b " +
                    "where b.subscriber.bank.userId = :bankId and b.subscriber.phoneNumber = :subscriberId " +
                    "and b.paymentDate is Not NULL";
            bills = session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .setParameter("subscriberId", subscriberId)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }

        return bills;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillDAO> getTenBiggestPayersByBankId(int bankId) {
        List<BillDAO> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.subscriber.phoneNumber as phoneNumber, " +
                    "b.subscriber.firstName as firstName, " +
                    "b.subscriber.lastName as lastName, " +
                    "'BGN' as currency, " +
                    "round(sum(b.amount*(case when b.currency.currency != 'BGN' " +
                    "then  b.currency.exchangeRate else 1 end)),2) as summ " +
                    "from Bill as b " +
                    "where b.subscriber.bank.userId = :bankId and b.paymentDate is Not NULL " +
                    "group by b.subscriber " +
                    "order by summ DESC";
            bills = session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .setMaxResults(10)
                    .setResultTransformer(Transformers.aliasToBean(BillDAO.class))
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }

        return bills;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<BillDAO> getTenMostRecentPaymentsByBankId(int bankId) {
        List<BillDAO> bills = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "select b.id as billId, b.service.service as service, " +
                    "b.subscriber.phoneNumber as phoneNumber, b.subscriber.firstName as firstName, " +
                    "b.subscriber.lastName as lastName, round(b.amount,2) as amount, " +
                    "b.currency.currency as currency, b.paymentDate as paymentDate " +
                    "from Bill as b " +
                    "join fetch Subscriber as s on b.subscriber = s.phoneNumber " +
                    "join fetch User as u on s.bank.userId = u.userId " +
                    "where s.bank.userId = :bankId and b.paymentDate is Not NULL " +
                    "order by b.paymentDate DESC";
            bills = session.createQuery(query)
                    .setParameter("bankId", bankId)
                    .setMaxResults(10)
                    .setResultTransformer(Transformers.aliasToBean(BillDAO.class))
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RequestCantBeProcessedException("The request cant be processed right now. Please try again later!");
        }

        return bills;
    }
}
