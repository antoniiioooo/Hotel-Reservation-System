package com.reservation_gui;

import java.util.*;


class Customer{
    private String fName;
    private String lName;
    private String phoneNum;
    private String email;
    private String custID;
    private String transID;
    private String confirmation;
    //private Payment paymentInfo;

    public Customer(){
        this.custID = Integer.toString(createCustID());
    }

    private int createCustID(){
        Random rand = new Random();
        return rand.nextInt(9000000) + 1000000;
    } 
    public String getCustID(){
        return this.custID;
    }

    public void setFirstName(String fNameInput){
        this.fName = fNameInput;
    }
    public String getFirstName(){
        return this.fName;
    }

    public void setLastName(String lNameInput){
        this.lName = lNameInput;
    }
    public String getLastName(){
        return this.lName;
    }

    public void setPhoneNum(String phoneInput){
        this.phoneNum = phoneInput;
    }
    public String getPhoneNum(){
        return this.phoneNum;
    }

    public void setEmail(String emailInput){
        this.email = emailInput;
    }
    public String getEmail(){
        return this.email;
    }

    public void setTransID(String id){
        this.transID = id;
    }
    public String getTransID(){
        return this.transID;
    }

    public void setConfirmNum(String confirmNum){
        this.confirmation = confirmNum;
    }
    public String getConfrimNum(){
        return this.confirmation;
    }

    /* public void setPaymentInfo(Payment payInfo){
        this.paymentInfo = payInfo;
    }
    public Payment getPaymentInfo(){
        return this.paymentInfo;
    } */
}