package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.data.dao.BillDAO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;

import java.sql.Date;
import java.util.List;
import java.util.Set;


public interface BillRepository {

    Bill getById(int id);

    void createBill(Bill bill);

    List<Bill> getUnpaidBillsByBankId(int bankId);

    Set<Integer> getSetOfUnpaidBillsByBankId(int bankId);

    void payBills(List<Integer> bills);

    List<Bill> getSubscribersPaymentsHistoryDescendingByBankId(int bankId);

    List<BillDAO> getMaxAndAvgPaymentInTimeIntervalByBankId(int bankId, String subscriberId,
                                                            Date startDate, Date endDate);

    List<Service> getSubscriberPaidServicesByBankId(int bankId, String subscriberId);

    List<BillDAO> getTenBiggestPayersByBankId(int bankId);

    List<BillDAO> getTenMostRecentPaymentsByBankId(int bankId);
}
