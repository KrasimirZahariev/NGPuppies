package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.BillRepository;
import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.services.base.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

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
    public void payBills(List<String> bills, int bankId) {
        List<Integer> billsToBePaid = bills.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

                //TODO:first get all unpaid bills by bank id and check if the given IDs are contained there

//        String userName = userService.getUsernameFromToken(request);
//        User user = userService.loadUserByUsername(userName);
//        int bankId = user.getUserId();

        billRepository.payBills(billsToBePaid);
    }

    @Override
    public List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval, String subscriberId,
                                                                   int bankId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null, endDate = null;
        try {
            startDate = new Date(format.parse(timeInterval.get(0)).getTime());
            endDate = new Date(format.parse(timeInterval.get(1)).getTime());
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }

        return billRepository.getMaxAndAvgPaymentInTimeIntervalByBankId(bankId, subscriberId, startDate, endDate);
    }

    @Override
    public List<Bill> getSubscribersPaymentsHistoryDescendingByBankId(int bankId) {
        return billRepository.getSubscribersPaymentsHistoryDescendingByBankId(bankId);
    }

    @Override
    public List<com.wolverineteam.ngpuppies.models.Service> getSubscriberPaidServicesByBankId(String subscriberId,
                                                                                    int bankId) {

        return billRepository.getSubscriberPaidServicesByBankId(bankId, subscriberId);
    }

    @Override
    public List<BillDTO> getTenBiggestPaymentsByBankId(int bankId) {
        return billRepository.getTenBiggestPaymentsByBankId(bankId);
    }

    @Override
    public List<BillDTO> getTenMostRecentPaymentsByBankId(int bankId) {
        return billRepository.getTenMostRecentPaymentsByBankId(bankId);
    }
}
