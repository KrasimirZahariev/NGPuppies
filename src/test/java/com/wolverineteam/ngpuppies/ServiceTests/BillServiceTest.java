package com.wolverineteam.ngpuppies.ServiceTests;

import com.wolverineteam.ngpuppies.data.base.BillRepository;
import com.wolverineteam.ngpuppies.data.dao.BillDAO;
import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.models.*;
import com.wolverineteam.ngpuppies.services.BillServiceImpl;
import com.wolverineteam.ngpuppies.services.base.BillService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

    @Mock
    BillRepository mockBillRepository;

    @InjectMocks
    BillServiceImpl billService;

    @Test
    public void getUnpaidBillwhenBankIdGiven_ReturnCorrectBills() {
        Subscriber testSubs = new Subscriber();
        testSubs.setFirstName("TestSubs");
        Bill testBill = new Bill();
        testBill.setSubscriber(testSubs);
        List<Bill> bills = new ArrayList<>();
        bills.add(testBill);

        when(mockBillRepository.getUnpaidBillsByBankId(1)).thenReturn(bills);

        List<Bill> result = billService.getUnpaidBillsByBankId(1);

        Assert.assertEquals(1, result.size());
    }

    @Test
    public void createBill_ReturnNewBill() {
        BillDTO testBill = new BillDTO();
      //  testBill.setPhoneNumber("123");
      //  testBill.setService("TV");
      //  testBill.setStartDate("11/08/2018");
      //  testBill.setEndDate("11/09/2018");
      //  testBill.setAmount(100);
      //  testBill.setCurrency("BGN");

        Bill testBill2 = new Bill();
      //  testBill2.setSubscriber(new Subscriber("123","firstName","lastName","0000",new User()));
      //  testBill2.setService(new Service("TV"));
      //  testBill2.setStartDate(new Date(java.util.Date.parse("11/08/2018")));
      //  testBill2.setEndDate(new Date(java.util.Date.parse("11/09/2018")));
      //  testBill2.setAmount(100);
      //  testBill2.setCurrency(new Currency("BGN",1));
        doNothing().when(mockBillRepository).createBill(isA(Bill.class));
        billService.createBill(testBill);

        verify(mockBillRepository, times(1)).createBill(testBill2);
    }

    @Test
    public void payBills_whenBillsIdIsGiven_ReturnPaidBills() {
        List<String> billsToBePaid = new ArrayList<>();
        billsToBePaid.add("2");
        billsToBePaid.add("1");
        billsToBePaid.add("3");
        billsToBePaid.add("4");

        List<Integer> billsToBePaidParsed = new ArrayList<>();
        billsToBePaidParsed.add(2);
        billsToBePaidParsed.add(1);
        billsToBePaidParsed.add(3);
        billsToBePaidParsed.add(4);

        doNothing().when(mockBillRepository).payBills(isA(List.class));
        billService.payBills(billsToBePaid,1);

        verify(mockBillRepository, times(1)).payBills(billsToBePaidParsed);
    }

    @Test
    public void getMaxAndAvgPaymentsInTimeInterval_ReturnCorrectDate(){

    }

    @Test
    public void getSubscriberPaymentsHistoryDescendingByBankId_ReturnCorrectBills() {
        Bill testBill = new Bill();
        List<Bill> mockBillsList = new ArrayList<>();
        mockBillsList.add(testBill);

        when(mockBillRepository.getSubscribersPaymentsHistoryDescendingByBankId(1)).thenReturn(mockBillsList);

        List<Bill> result = billService.getSubscribersPaymentsHistoryDescendingByBankId(1);

        Assert.assertEquals(1, result.size());

    }

    @Test
    public void getPaidServicesByBankId_ReturnCorrectBills() {
        List<Service> mockServices = new ArrayList<>();
        Service testService = new Service();
        mockServices.add(testService);

        when(mockBillRepository.getSubscriberPaidServicesByBankId(1,"0123456789")).thenReturn(mockServices);

        List<Service> result = mockBillRepository.getSubscriberPaidServicesByBankId(1,"0123456789");

        Assert.assertEquals(1, result.size());
    }

    @Test
    public void getTenMostRecentPaymentsByBankId_ReturnCorrectBills() {
        List<BillDAO> mockBillList = new ArrayList<>();
        BillDAO testBill1 = new BillDAO();
        BillDAO testBill2 = new BillDAO();
        mockBillList.add(testBill1);
        mockBillList.add(testBill2);

        when(mockBillRepository.getTenMostRecentPaymentsByBankId(1)).thenReturn(mockBillList);

        List<BillDAO> result = billService.getTenMostRecentPaymentsByBankId(1);

        Assert.assertEquals(2, result.size());
    }
}
