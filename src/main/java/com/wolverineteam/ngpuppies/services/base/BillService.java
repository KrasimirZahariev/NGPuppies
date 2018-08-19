package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.Bill;

import java.util.List;

public interface BillService {

    List<Bill> getUnpaidBillsByBankId(int bankId);

    void createBill(Bill bill);

    void pay(List<String> bills);

    List<Object[]> getMinAndAvgPaymentInTimeInterval(List<String> timeInterval);

    List<Bill> getAllPaidSorted(int bankId);

    List<Bill> getAllPaidServices(int bankId);

    List<Object[]> getTopPayers(int bankId);

    List<Bill> getTenMostRecentPayments(int bankId);
}
