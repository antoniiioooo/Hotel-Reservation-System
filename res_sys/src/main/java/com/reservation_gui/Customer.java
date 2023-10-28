package com.reservation_gui;

import java.util.*;


class Customer{
    /* private  member variables */
    private String fName;
    private String lName;
    private String phoneNum;
    private String email;
    private String custID;
    private String transID;
    private String confirmation;
    //private Payment paymentInfo;

    /* Default ontructor */
    public Customer(){
        /* randomize customer ID, Transaction ID, and Confirmation Number */
        this.custID = Integer.toString(createCustID());
        this.transID = Integer.toString(createCustID());
        this.confirmation = Integer.toString(createCustID());
    }

    /* parameterized constructor, takes first name, last name, phone number, and email */
    public Customer(String first, String last, String phone, String email){
        this.fName = first;
        this.lName = last;
        this.phoneNum = phone;
        this. email = email;
        /* randomize customer ID, Transaction ID, and Confirmation Number */
        this.custID = Integer.toString(createCustID());
        this.transID = Integer.toString(createCustID());
        this.confirmation = Integer.toString(createCustID());
    }

    /* randomized Customer ID */
    private int createCustID(){
        Random rand = new Random();
        return rand.nextInt(9000000) + 1000000;
    } 

    /* customer ID getter */
    public String getCustID(){
        return this.custID;
    }

    /* first name setter and getter */
    public void setFirstName(String fNameInput){
        this.fName = fNameInput;
    }
    public String getFirstName(){
        return this.fName;
    }

    /* last name setter and getter */
    public void setLastName(String lNameInput){
        this.lName = lNameInput;
    }
    public String getLastName(){
        return this.lName;
    }

    /* phone number setter and getter */
    public void setPhoneNum(String phoneInput){
        this.phoneNum = phoneInput;
    }
    public String getPhoneNum(){
        return this.phoneNum;
    }

    /* email setter and getter */
    public void setEmail(String emailInput){
        this.email = emailInput;
    }
    public String getEmail(){
        return this.email;
    }

    /* transaction ID setter and getter */
    public void setTransID(String id){
        this.transID = id;
    }
    public String getTransID(){
        return this.transID;
    }

    /* confirmation number setter and getter */
    public void setConfirmNum(String confirmNum){
        this.confirmation = confirmNum;
    }
    public String getConfrimNum(){
        return this.confirmation;
    }

    /* payment info setter and getter 
    public void setPaymentInfo(Payment payInfo){
        this.paymentInfo = payInfo;
    }
    public Payment getPaymentInfo(){
        return this.paymentInfo;
    } */
}