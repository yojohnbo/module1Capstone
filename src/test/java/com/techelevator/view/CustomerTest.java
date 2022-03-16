package com.techelevator.view;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CustomerTest {
    Customer customer;
    Product productA;
    Product productB;
    @Before
    public void create_Customer_Test() {
        customer = new Customer();
        BigDecimal moneyProvided = new BigDecimal(0.00).setScale(2, RoundingMode.UP);
        productA = new Chip();
        productB = new Candy();
        productA.setName("Potato Crisps");
        productB.setName("Cowtales");
        productA.setPrice(BigDecimal.valueOf(3.05));
        productB.setPrice(BigDecimal.valueOf(1.50));
        productA.setType("Chip");
        productB.setType("Candy");
        productA.setSlotIdentifier("A");
        productB.setSlotIdentifier("B");
        productB.setQuantity(0);

    }


    @Test
    public void feedMoneyTests(){
        BigDecimal moneyProvided = customer.feedMoney(0.00).setScale(2, RoundingMode.UP);
        BigDecimal moneyProvided1 = customer.feedMoney(1.25).setScale(2, RoundingMode.UP);
        BigDecimal moneyProvided2 = customer.feedMoney(-1.00).setScale(2, RoundingMode.UP);
        BigDecimal moneyProvided3 = customer.feedMoney(2.00).setScale(2, RoundingMode.UP);
        Assert.assertEquals("****Amount entered is not a whole dollar amount****", new BigDecimal(0.00).setScale(2, RoundingMode.UP), moneyProvided);
        Assert.assertEquals("****Amount entered is not a whole dollar amount****", new BigDecimal(0.00).setScale(2, RoundingMode.UP), moneyProvided1);
        Assert.assertEquals("****Amount entered is not a whole dollar amount****", new BigDecimal(0.00).setScale(2, RoundingMode.UP), moneyProvided2);
        Assert.assertEquals(BigDecimal.valueOf(2.00).setScale(2, RoundingMode.UP), moneyProvided3);
    }



    @Test
    public void changeReturnedTests(){
        BigDecimal amountToSpend1 = new BigDecimal(0);
        BigDecimal amountToSpend2 = new BigDecimal(2);
        Assert.assertEquals(BigDecimal.valueOf(0), amountToSpend1);
        Assert.assertEquals(BigDecimal.valueOf(2), amountToSpend2);
    }


    }


