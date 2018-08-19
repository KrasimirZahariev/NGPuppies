package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.BillRepository;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.services.base.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<Bill> getUnpaidBillsByBankId(String bankId) {
        int id = Integer.parseInt(bankId);
        return billRepository.getUnpaidBillsByBankId(id);
    }

    @Override
    public void createBill(Bill bill) {
        billRepository.createBill(bill);
    }

    @Override
    public void payBills(List<String> bills) {
        List<Integer> billsToBePaid = new ArrayList<>(bills.size());
        for (String bill : bills) {
            billsToBePaid.add(Integer.parseInt(bill));
        }
        billRepository.payBills(billsToBePaid);
    }

    @Override
    public List<Object[]> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval) {
        return billRepository.getMaxAndAvgPaymentInTimeIntervalByBankId(timeInterval);
    }

    @Override
    public List<Bill> getSubscriberPaymentsHistoryDescendingByBankId(String bankId, String subscriberId) {
        int parsedBankId = Integer.parseInt(bankId);
        int parsedSubscriberId = Integer.parseInt(subscriberId);
        return billRepository.getSubscriberPaymentsHistoryDescendingByBankId(parsedBankId, parsedSubscriberId);
    }

    @Override
    public List<Bill> getPaidServicesByBankId(String bankId) {
        int id = Integer.parseInt(bankId);
        return billRepository.getPaidServicesByBankId(id);
    }

    @Override
    public List<Object[]> getTenBiggestPaymentsByBankId(String bankId) {
        int id = Integer.parseInt(bankId);
        return billRepository.getTenBiggestPaymentsByBankId(id);
    }

    @Override
    public List<Bill> getTenMostRecentPaymentsByBankId(String bankId) {
        int id = Integer.parseInt(bankId);
        return billRepository.getTenMostRecentPaymentsByBankId(id);
    }
}
