package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.data.dto.BillDAO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;

import java.util.List;

public interface BillService {

    List<Bill> getUnpaidBillsByBankId(int bankId);

    void createBill(Bill bill);

    void payBills(List<String> bills, int bankId);

    List<BillDAO> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval, String subscriberId,
                                                            int bankId);

    List<Bill> getSubscribersPaymentsHistoryDescendingByBankId(int bankId);

    List<Service> getSubscriberPaidServicesByBankId(String subscriberId, int bankId);

    List<BillDAO> getTenBiggestPaymentsByBankId(int bankId);

    List<BillDAO> getTenMostRecentPaymentsByBankId(int bankId);
}
