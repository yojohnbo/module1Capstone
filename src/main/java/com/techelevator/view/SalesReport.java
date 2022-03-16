package com.techelevator.view;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesReport {
    // instance variables
    private String reportMessage;
    private List<Product> salesReport = new ArrayList<Product>();
    private Map<String, Product> inventoryMap = new TreeMap<String, Product>();


    public List<Product> getSalesReport() {
        return salesReport;
    }

    // constructor
    public SalesReport(Map<String, Product> inventoryMap) {
        generateSalesReport(inventoryMap);
    }

    public void generateSalesReport(Map<String, Product> inventoryMap) {

        for (Map.Entry<String, Product> singleProduct : inventoryMap.entrySet()) {
            // add product info to salesReport list
            salesReport.add(singleProduct.getValue());
        }

    }

    // create method for print sales report
    public void printReport(){
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss aa");
        Date time = Calendar.getInstance().getTime();
        try {
            String newFileName;
            newFileName = dateFormat.format(time) + " Sales report.txt" ;
            File newFile = new File(newFileName);
            newFile.createNewFile();

            // add info in each report file
            try (PrintWriter writer = new PrintWriter(newFile)){
                for (Product item : salesReport){
                    writer.println(item.getName() + " | " + item.getNumberSold());

                }

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}


