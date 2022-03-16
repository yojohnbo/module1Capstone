package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CoinBankTest {
    CoinBank coinBank;
    @Before
    public void create_CoinBank_Test() {
        coinBank = new CoinBank();
        coinBank.setBalance(BigDecimal.valueOf(100));
    }

    //addToCoinBank - amountToSpend, if zero or number

    @Test
    public void addToCoinBankTests() {
        BigDecimal amountToSpend = new BigDecimal(0);
        BigDecimal amountToSpend2 = new BigDecimal(3);
        CoinBank coinBank = new CoinBank();
        coinBank.setBalance(BigDecimal.valueOf(0));
        Assert.assertEquals(BigDecimal.valueOf(0), coinBank.addToCoinBank(amountToSpend));
        Assert.assertEquals(BigDecimal.valueOf(3), coinBank.addToCoinBank(amountToSpend2));
    }

    //subtractFromCoinBank - amountToReturn is > 25, amountToReturn = 25, amountToReturn > 10,
    // amountToReturn = 10, amountToReturn = 5, amountToReturn = 0

    @Test
    public void subtractFromCoinBankTests(){
        BigDecimal amountToReturn = new BigDecimal(30);
        BigDecimal amountToReturn2 = new BigDecimal(25);
        BigDecimal amountToReturn3 = new BigDecimal(15);
        BigDecimal amountToReturn4 = new BigDecimal(10);
        BigDecimal amountToReturn5 = new BigDecimal(5);
        BigDecimal amountToReturn6 = new BigDecimal(0);

        Assert.assertEquals(BigDecimal.valueOf(70), coinBank.subtractFromCoinBank(amountToReturn));
        coinBank.setBalance(BigDecimal.valueOf(100));
        Assert.assertEquals(BigDecimal.valueOf(75), coinBank.subtractFromCoinBank(amountToReturn2));
        coinBank.setBalance(BigDecimal.valueOf(100));
        Assert.assertEquals(BigDecimal.valueOf(85), coinBank.subtractFromCoinBank(amountToReturn3));
        coinBank.setBalance(BigDecimal.valueOf(100));
        Assert.assertEquals(BigDecimal.valueOf(90), coinBank.subtractFromCoinBank(amountToReturn4));
        coinBank.setBalance(BigDecimal.valueOf(100));
        Assert.assertEquals(BigDecimal.valueOf(95), coinBank.subtractFromCoinBank(amountToReturn5));
        coinBank.setBalance(BigDecimal.valueOf(100));
        Assert.assertEquals(BigDecimal.valueOf(100), coinBank.subtractFromCoinBank(amountToReturn6));

    }

}

