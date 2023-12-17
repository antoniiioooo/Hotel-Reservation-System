package com.reservation_gui;
import java.util.*;

/** 
 * Class to house the customer information data
 * @author Nexaly Orellana
 * @version 1.1
 * */


class Customer{
    /* private  member variables */
    private String fName;
    private String lName;
    private String phoneNum;
    private String email;
    private String custID;
    private String transID;
    private String confirmation;
    private Payment paymentInfo;

    /* Default contructor */
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

    /* parameterized constructor, takes first name, last name, phone number, and email along with the ID values of the customer and reservation itself*/
    public Customer(String first, String last, String phone, String email, String cID, String cNum, String tID){
        this.fName = first;
        this.lName = last;
        this.phoneNum = phone;
        this. email = email;
        
        this.custID = cID;
        this.transID = tID;
        this.confirmation = cNum;
    }

    /* parameterized constructor, takes first name, last name, phone number, and email along with the ID values of the customer and reservation itself as well as payment information*/
    public Customer(String first, String last, String phone, String email, String cID, String cNum, String tID, String cardName, String cardNum, String cardExp, String cardCVV){
        this.fName = first;
        this.lName = last;
        this.phoneNum = phone;
        this. email = email;
        
        this.custID = cID;
        this.transID = tID;
        this.confirmation = cNum;

        this.paymentInfo = new Payment(cardName, cardNum, cardExp, cardCVV);
    }

    /** 
     * randomized Customer ID
     * @return int for customer ID
     */
    private int createCustID(){
        Random rand = new Random();
        return rand.nextInt(9000000) + 1000000;
    } 

    /**
     * customer ID getter 
     * @return String to return customer ID as a String
     */
    public String getCustID(){
        return this.custID;
    }

    /** 
     * Sets first name from an input
     * @param fNameInput passing in a first name
     */
    public void setFirstName(String fNameInput){
        this.fName = fNameInput;
    }

    /** 
     * gets stored first name and returns to caller
     * @return String to return first name
     */
    public String getFirstName(){
        return this.fName;
    }
    
    /** 
     * Sets last name from an input
     * @param lNameInput passing in a last name
     */
    public void setLastName(String lNameInput){
        this.lName = lNameInput;
    }

    /** 
     * gets stored last name and returns to caller
     * @return String to return last name
     */
    public String getLastName(){
        return this.lName;
    }

    
    /** 
     * sets phone number from an input
     * @param phoneInput passing in a phone number
     */
    public void setPhoneNum(String phoneInput){
        this.phoneNum = phoneInput;
    }

    /**
     *  gets stored phone number and returns to caller
     * @return String to return phone number
     */
    public String getPhoneNum(){
        return this.phoneNum;
    }

    /**
     *sets email from an input
     @param emailInput passing in an email address  
     */
    public void setEmail(String emailInput){
        this.email = emailInput;
    }

    /**
     * gets stored email and returns to caller
     * @return String to return email address
     */
    public String getEmail(){
        return this.email;
    }

    /** 
     * Sets transaction ID from an input
     * @param id passing in a transaction ID 
     */
    public void setTransID(String id){
        this.transID = id;
    }

    /**
     * gets stored transaction id and returns to caller
     * @return String to return transaction ID
     */
    public String getTransID(){
        return this.transID;
    }

    /**
     * sets confirmation number from an input
     * @param confirmNum passing in a confirmation number
     */
    public void setConfirmNum(String confirmNum){
        this.confirmation = confirmNum;
    }

    /**
     * gets stored confirmation number and returns to caller
     * @return String to return confirmation number
     */
    public String getConfrimNum(){
        return this.confirmation;
    }

    /* payment info setter and getter */
    public void setPaymentInfo(Payment payInfo){
        this.paymentInfo = payInfo;
    }
    public Payment getPaymentInfo(){
        return this.paymentInfo;
    } 
}