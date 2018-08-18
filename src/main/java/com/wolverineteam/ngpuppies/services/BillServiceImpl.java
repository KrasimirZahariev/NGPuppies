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
    public List<Bill> getUnpaidBillsByBankId(int bankId) {
        return billRepository.getUnpaidBillsByBankId(bankId);
    }

    @Override
    public void createBill(Bill bill) {
        billRepository.createBill(bill);
    }

    @Override
    public void pay(List<String> bills) {
        List<Integer> billsToBePaid = new ArrayList<>(bills.size());
        for (String bill : bills) {
            billsToBePaid.add(Integer.parseInt(bill));
        }
        billRepository.pay(billsToBePaid);
    }

    @Override
    public List<Bill> getAllPaidSorted(int bankId) {
        return billRepository.getAllPaidSorted(bankId);
    }

    @Override
    public List<Bill> getAllPaidServices(int bankId) {
        return billRepository.getAllPaidServices(bankId);
    }

    @Override
    public List<Bill> getTenMostRecentPayments(int bankId) {
        return billRepository.GetTenMostRecentPaymentsByBankId(bankId);
    }
}
