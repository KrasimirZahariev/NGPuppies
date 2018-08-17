package com.wolverineteam.ngpuppies.data;

import com.wolverineteam.ngpuppies.data.base.BillRepository;
import com.wolverineteam.ngpuppies.models.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillRepositoryImpl implements BillRepository{

    @Override
    public List<Bill> getAllNotPaid(int bankId) {
        return null;
    }

    @Override
    public void pay(List<Bill> bills) {

    }

    @Override
    public List<Bill> getAllPaidSorted(int bankId) {
        return null;
    }
}
