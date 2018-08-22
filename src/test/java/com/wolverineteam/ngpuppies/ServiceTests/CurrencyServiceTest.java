package com.wolverineteam.ngpuppies.ServiceTests;

import com.wolverineteam.ngpuppies.data.base.CurrencyRepository;
import com.wolverineteam.ngpuppies.models.Currency;
import com.wolverineteam.ngpuppies.services.CurrencyServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceTest {

    @Mock
    CurrencyRepository mockCurrencyRepo;

    @InjectMocks
    CurrencyServiceImpl currencyService;

    @Test
    public void getUserWhenUserIdGiven_ReturnCorrectUser() {
        Mockito.when(mockCurrencyRepo.getById(1))
                .thenReturn(new Currency("JSD", 1.21));

        Currency result = currencyService.getById("1");

        Assert.assertEquals("JSD", result.getCurrency());
    }

    @Test
    public void GetAllUsers_ReturnCorrectUsers() {
        Mockito.when(mockCurrencyRepo.getAll())
                .thenReturn(Arrays.asList(
                        new Currency("JSD", 1.21),
                        new Currency("JAD", 1.41),
                        new Currency("JDD", 1.31)
                ));

        List<Currency> result = currencyService.getAll();

        Assert.assertEquals(3, result.size());
    }

    @Test
    public void UpdateUserWhenUpdatedUserGiven_ReturnCorrectUpdatedUser() {
        Currency testCurrency = new Currency("JSD", 1.21);

        doNothing().when(mockCurrencyRepo).update(isA(Integer.class), isA(Currency.class));
        currencyService.update("1", testCurrency);

        verify(mockCurrencyRepo, times(1)).update(1, testCurrency);

    }

    @Test
    public void CreateNewUserWhenGivenUser_ReturnsCorrectNewUser() {
        Currency mockCurrency = new Currency("JSD", 1.21);

        doNothing().when(mockCurrencyRepo).create(isA(Currency.class));
        currencyService.create(mockCurrency);

        verify(mockCurrencyRepo, times(1)).create(mockCurrency);
    }

    @Test
    public void DeleteUserWhenGivenId_ReturnDeleteUser() {
        Currency mockCurrency = new Currency("JSD", 1.21);

        doNothing().when(mockCurrencyRepo).delete(isA(Integer.class));
        currencyService.delete("1");

        verify(mockCurrencyRepo, times(1)).delete(1);
    }
}
