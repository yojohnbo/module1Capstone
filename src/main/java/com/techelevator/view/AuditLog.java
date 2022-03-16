package com.techelevator.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AuditLog {
    // instance variable
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
    String auditMessage;
    Date time;

    // constructor

    public AuditLog(String auditMessage) {
        this.time = Calendar.getInstance().getTime();
        this.auditMessage = auditMessage;
    }

    // method
    public String printInLog(){
        return dateFormat.format(time)+" "+auditMessage;


    }


}
