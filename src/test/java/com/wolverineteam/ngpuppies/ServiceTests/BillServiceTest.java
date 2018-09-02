package com.wolverineteam.ngpuppies.ServiceTests;

import com.wolverineteam.ngpuppies.data.base.BillRepository;
import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.models.Subscriber;
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
        Bill testBill2 = new Bill();

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

        List<Integer> billsToBePaidParsed = billsToBePaid.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        doNothing().when(mockBillRepository).payBills(isA(List.class));
        billService.payBills(billsToBePaid,1);

        verify(mockBillRepository, times(1)).payBills(billsToBePaidParsed);
    }

    @Test
    public void getSubscriberPaymentsHistoryDescendingByBankId_ReturnCorrectBills() {
        Bill testBill = new Bill();
        List<Bill> mockBillsList = new ArrayList<>();
        mockBillsList.add(testBill);

        when(mockBillRepository.getSubscriberPaymentsHistoryDescendingByBankId(1, 1)).thenReturn(mockBillsList);

        List<Bill> result = billService.getSubscriberPaymentsHistoryDescendingByBankId("1", "1");

        Assert.assertEquals(1, result.size());

    }

    @Test
    public void getPaidServicesByBankId_ReturnCorrectBills() {
        List<Service> mockServices = new ArrayList<>();
        Service testService = new Service();
        mockServices.add(testService);

        when(mockBillRepository.getPaidServicesByBankId(1)).thenReturn(mockServices);

        List<Service> result = mockBillRepository.getPaidServicesByBankId(1);

        Assert.assertEquals(1, result.size());
    }

    @Test
    public void getTenMostRecentPaymentsByBankId_ReturnCorrectBills() {
        List<Bill> mockBillList = new ArrayList<>();
        Bill testBill1 = new Bill();
        Bill testBill2 = new Bill();
        mockBillList.add(testBill1);
        mockBillList.add(testBill2);

        when(mockBillRepository.getTenMostRecentPaymentsByBankId(1)).thenReturn(mockBillList);

        List<Bill> result = billService.getTenMostRecentPaymentsByBankId("1");

        Assert.assertEquals(2, result.size());
    }
}
