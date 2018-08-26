package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;

import java.sql.Date;
import java.util.List;


public interface BillRepository {

    Bill getById(int id);

    void createBill(Bill bill);

    List<Bill> getUnpaidBillsByBankId(int bankId);

    void payBills(List<Integer> bills);

    List<Bill> getSubscriberPaymentsHistoryDescendingByBankId(int bankId, String subscriberId);

    List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(int bankId, String phoneNumber,
                                                            Date startDate, Date endDate);

    List<Service> getPaidServicesByBankId(int bankId);

    List<BillDTO> getTenBiggestPaymentsByBankId(int bankId);

    List<Bill> getTenMostRecentPaymentsByBankId(int bankId);
}
