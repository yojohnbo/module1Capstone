package com.techelevator.view;

import java.math.BigDecimal;

public class Product {
    // instance variables
    private String slotIdentifier;
    private String name;
    private BigDecimal price;
    private String type;
    private int quantity = 5;
    private int numberSold;

    // getter
    public String getSlotIdentifier() {
        return slotIdentifier;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getNumberSold() {
        return numberSold;
    }

    // setter
    public void setSlotIdentifier(String slotIdentifier) {
        this.slotIdentifier = slotIdentifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberSold(int numberSold) {
        this.numberSold = numberSold;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // default constructor
    public Product() {
        this.numberSold =0;
    }

    public void printMessage(Customer customer){
    }

    public void sell(){
        this.numberSold +=1;
    }

}
