package com.techelevator.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Customer {
    private BigDecimal moneyProvided = new BigDecimal(0.00).setScale(2, RoundingMode.UP);
    public List<AuditLog> auditLogs = new ArrayList<AuditLog>();

    public BigDecimal getMoneyProvided() {
        return moneyProvided;
    }

    public void setMoneyProvided(BigDecimal moneyProvided) {
        this.moneyProvided = moneyProvided;
    }

    public Customer() {

    }

    public Customer(BigDecimal moneyProvided) {
        this.moneyProvided = moneyProvided;
    }

    public BigDecimal feedMoney (double amountToDeposit){

            if (amountToDeposit > 0 && amountToDeposit % 1 == 0) {
                moneyProvided = moneyProvided.add(BigDecimal.valueOf(amountToDeposit)).setScale(2, RoundingMode.UP);
                // add auditLog message
                auditLogs.add(new AuditLog("FEED MONEY: $" + amountToDeposit + " $" + moneyProvided));

            } else {
                System.out.println("****Amount entered is not a whole dollar amount****");
            }
            return moneyProvided;

    }

    public BigDecimal changeReturned (BigDecimal amountToSpend){
            moneyProvided = moneyProvided.subtract(amountToSpend);
        return moneyProvided;
    }

    public Product selectProduct (Map<String, Product> inventoryMap, BigDecimal moneyProvided, Scanner userInput,Customer customer ){
        // list available products
        Product product = new Product();
        if (moneyProvided.equals(BigDecimal.ZERO)) {
            System.out.println("Deposit money before making a selection");
        } else {
            // loop through the map
            for(Map.Entry<String, Product> singleProduct: inventoryMap.entrySet()) {
                // only print products available
                if (singleProduct.getValue().getQuantity()>0) {
                    System.out.println("Slot identifier: " + singleProduct.getValue().getSlotIdentifier() + " || "
                            + singleProduct.getValue().getName()
                            +", Price: $" + singleProduct.getValue().getPrice()
                            + ", Quantity remaining: " + singleProduct.getValue().getQuantity());
                }
            }

            // compare userinput with slotID
            System.out.println("Make a selection with slot identifier: ");
            String slotIdentifierPicked = userInput.nextLine();

            boolean matchFound = false;
            // loop thru keyset
            for (String slotID: inventoryMap.keySet()){
                // if valid, then dispense;
                if (slotIdentifierPicked.equals(slotID)) {
                    //item sold out
                    if (inventoryMap.get(slotID).getQuantity() == 0){
                        System.out.println(inventoryMap.get(slotID).getName() + " are Sold Out :(");
                    } else {
                        // product available, - dispense , printMessage
                        if (inventoryMap.get(slotID).getType().equals("Drink")){
                            product = new Drink();
                            setupProduct(inventoryMap, customer, product,slotID);

                        }else if (inventoryMap.get(slotID).getType().equals("Chip")){
                            product = new Chip();
                            setupProduct(inventoryMap, customer, product,slotID);

                        }else if (inventoryMap.get(slotID).getType().equals("Candy")){
                            product = new Candy();
                            setupProduct(inventoryMap, customer, product,slotID);

                        }else if (inventoryMap.get(slotID).getType().equals("Gum")){
                            product = new Gum();
                            setupProduct(inventoryMap, customer,product,slotID);
                        }

                        // update  quantity of item,
                        if (product.getPrice().compareTo(moneyProvided)!=1 ){
                            inventoryMap.get(slotID).setQuantity(product.getQuantity()-1);
                            inventoryMap.get(slotID).sell();

                            auditLogs.add(new AuditLog(product.getName()+" "+product.getSlotIdentifier()
                                    +" $"+ product.getPrice()+ " $"+ customer.getMoneyProvided()));
                        }
                    }
                    matchFound = true;
                }
            }
            // if customer enter invalid code
            if (!matchFound){
                 System.out.println("**** Slot identifier does not exist - Please enter a valid slot identifier ****");
            }
        }
        return product;
    }

    private void setupProduct(Map<String, Product> inventoryMap, Customer customer, Product product, String slotID) {
        product.setName(inventoryMap.get(slotID).getName());
        product.setType(inventoryMap.get(slotID).getType());
        product.setPrice(inventoryMap.get(slotID).getPrice());
        product.setSlotIdentifier(inventoryMap.get(slotID).getSlotIdentifier());
        product.setQuantity(inventoryMap.get(slotID).getQuantity());
        product.printMessage(customer);
    }

    // hit finish transaction - got a message money returned, moneyProvided =0,
    public void finishTransaction(Product product, Customer customer, CoinBank coinBank){
        BigDecimal amountToReturn = customer.getMoneyProvided();
        BigDecimal amountToSpend = product.getPrice();
        // if customer feed money but didn't buy anything,and hit finish transaction
        if (amountToSpend==null){
            System.out.println("Not interested in another item? Hope to see you next time!");
            System.out.println("Here is your change: $" + amountToReturn);
            coinBank.subtractFromCoinBank(amountToReturn);
        }else {
            coinBank.addToCoinBank(amountToSpend);

            if (amountToReturn == BigDecimal.valueOf(0)) {
                System.out.println("Thank you for your business! :)");
            } else {
                System.out.println("Here is your change: $" + amountToReturn);
                coinBank.subtractFromCoinBank(amountToReturn);
            }

        }
        auditLogs.add(new AuditLog("GIVE CHANGE: $" + moneyProvided + " $0.00"));
        customer.setMoneyProvided(BigDecimal.valueOf(0));
    }


}
