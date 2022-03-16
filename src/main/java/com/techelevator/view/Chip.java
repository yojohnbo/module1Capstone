package com.techelevator.view;

public class Chip extends Product {
    // constructor
    public Chip() {
        super();
    }
    // method override
    @Override
    public void printMessage(Customer customer){
        super.printMessage(customer);

        if (getPrice().compareTo(customer.getMoneyProvided())==1){
            System.out.println("Not enough money provided, please enter more money");
        }else{
            customer.changeReturned(getPrice());
            System.out.println("Here is your " + getName() + ". It cost $" + getPrice()
                    + ". You have $" + customer.getMoneyProvided() + " left.");
            System.out.println("Crunch, Crunch, Yum!");
        }

    }
}
