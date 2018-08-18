package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.Bill;

import java.util.List;

public interface BillService {

    List<Bill> getUnpaidBillsByBankId(int bankId);

    void pay(List<String> bills);

    List<Bill> getAllPaidSorted(int bankId);

    List<Bill> getAllPaidServices(int bankId);

    List<Bill> getTenMostRecentPayments(int bankId);
}
