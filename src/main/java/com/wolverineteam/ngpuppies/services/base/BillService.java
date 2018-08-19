package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.Bill;

import java.util.List;

public interface BillService {

    List<Bill> getUnpaidBillsByBankId(int bankId);

    void createBill(Bill bill);

    void payBills(List<String> bills);

    List<Object[]> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval);

    List<Bill> getPaymentsHistoryDescendingByBankId(int bankId);

    List<Bill> getPaidServicesByBankId(int bankId);

    List<Object[]> getTenBiggestPaymentsByBankId(int bankId);

    List<Bill> getTenMostRecentPaymentsByBankId(int bankId);
}
