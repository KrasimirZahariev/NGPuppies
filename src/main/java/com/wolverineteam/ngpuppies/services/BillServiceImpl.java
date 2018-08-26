package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.BillRepository;
import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;
    private UserService userService;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, UserService userService) {
        this.billRepository = billRepository;
        this.userService = userService;
    }

    @Override
    public List<Bill> getUnpaidBillsByBankId(HttpServletRequest request) {
        String userName = userService.getUsernameFromToken(request);
        User user = userService.loadUserByUsername(userName);
        int bankId = user.getUserId();

        return billRepository.getUnpaidBillsByBankId(bankId);
    }

    @Override
    public void createBill(Bill bill) {
        billRepository.createBill(bill);
    }

    @Override
    public void payBills(List<String> bills, HttpServletRequest request) {
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
    public List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval, String phoneNumber,
                                                                   HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null, endDate = null;
        try {
            startDate = new Date(format.parse(timeInterval.get(0)).getTime());
            endDate = new Date(format.parse(timeInterval.get(1)).getTime());
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }

        String userName = userService.getUsernameFromToken(request);
        User user = userService.loadUserByUsername(userName);
        int bankId = user.getUserId();

        return billRepository.getMaxAndAvgPaymentInTimeIntervalByBankId(bankId, phoneNumber, startDate, endDate);
    }

    @Override
    public List<Bill> getSubscriberPaymentsHistoryDescendingByBankId(HttpServletRequest request, String subscriberId) {
        String userName = userService.getUsernameFromToken(request);
        User user = userService.loadUserByUsername(userName);
        int bankId = user.getUserId();

        return billRepository.getSubscriberPaymentsHistoryDescendingByBankId(bankId, subscriberId);
    }

    @Override
    public List<com.wolverineteam.ngpuppies.models.Service> getPaidServicesByBankId(HttpServletRequest request) {
        String userName = userService.getUsernameFromToken(request);
        User user = userService.loadUserByUsername(userName);
        int bankId = user.getUserId();

        return billRepository.getPaidServicesByBankId(bankId);
    }

    @Override
    public List<BillDTO> getTenBiggestPaymentsByBankId(HttpServletRequest request) {
        String userName = userService.getUsernameFromToken(request);
        User user = userService.loadUserByUsername(userName);
        int bankId = user.getUserId();

        return billRepository.getTenBiggestPaymentsByBankId(bankId);
    }

    @Override
    public List<BillDTO> getTenMostRecentPaymentsByBankId(HttpServletRequest request) {
        String userName = userService.getUsernameFromToken(request);
        User user = userService.loadUserByUsername(userName);
        int bankId = user.getUserId();

        return billRepository.getTenMostRecentPaymentsByBankId(bankId);
    }
}
