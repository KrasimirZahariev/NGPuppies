package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.BillRepository;
import com.wolverineteam.ngpuppies.data.base.CurrencyRepository;
import com.wolverineteam.ngpuppies.data.base.ServiceRepository;
import com.wolverineteam.ngpuppies.data.base.SubscriberRepository;
import com.wolverineteam.ngpuppies.data.dao.BillDAO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Currency;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.utils.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;
    private SubscriberRepository subscriberRepository;
    private ServiceRepository serviceRepository;
    private CurrencyRepository currencyRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, SubscriberRepository subscriberRepository, ServiceRepository serviceRepository, CurrencyRepository currencyRepository) {
        this.billRepository = billRepository;
        this.subscriberRepository = subscriberRepository;
        this.serviceRepository = serviceRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Bill> getUnpaidBillsByBankId(int bankId) {
        return billRepository.getUnpaidBillsByBankId(bankId);
    }

    @Override
    public void createBill(BillDTO bill) {
        Bill newBill = new Bill();
        newBill.setService(serviceRepository.loadServiceByServiceName(bill.getService()));
        newBill.setSubscriber(subscriberRepository.getSubscriberById(bill.getPhoneNumber()));
        newBill.setCurrency(currencyRepository.loadCurrencyByCurrencyName(bill.getCurrency()));
        newBill.setStartDate(new DateParser().getDateFromString(bill.getStartDate()));
        newBill.setEndDate(new DateParser().getDateFromString(bill.getEndDate()));
        newBill.setAmount(bill.getAmount());
        newBill.setPaymentDate(null);

        billRepository.createBill(newBill);
    }

    @Override
    public void payBills(List<String> bills, int bankId) {
        Set<Integer> billsCheck = billRepository.getSetOfUnpaidBillsByBankId(bankId);
        List<Integer> billsToBePaid = bills.stream()
                .map(Integer::parseInt)
                .filter(billsCheck::contains)
                .collect(Collectors.toList());

        billRepository.payBills(billsToBePaid);
    }

    @Override
    public List<BillDAO> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval, String subscriberId,
                                                                   int bankId) {

        Date startDate = new DateParser().getDateFromString(timeInterval.get(0));
        Date endDate = new DateParser().getDateFromString(timeInterval.get(1));
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
    public List<BillDAO> getTenBiggestPayersByBankId(int bankId) {
        return billRepository.getTenBiggestPayersByBankId(bankId);
    }

    @Override
    public List<BillDAO> getTenMostRecentPaymentsByBankId(int bankId) {
        return billRepository.getTenMostRecentPaymentsByBankId(bankId);
    }
}
