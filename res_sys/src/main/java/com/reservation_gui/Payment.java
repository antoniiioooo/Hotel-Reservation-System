package com.reservation_gui;

import java.util.ArrayList;
import java.util.List;

/**
 * Payment stores the information of the customer's debit/credit card: card number, expiration date and cvv number.
 * @author Joey Gomez
 * @version 1.1
 */

public class Payment 
{
    /* Create private variables */
    private String cardHolderName;
    private String cardNumber;
    private String expirationDate;
    private String cvvNumber; 
    
    /* Constructor for default state */
    public Payment()
    {
        this.cardHolderName = null;
        this.cardNumber = null;
        this.expirationDate = null;
        this.cvvNumber = null;
    }

    /* Create constructor for payment information */
    public Payment(String cardHolderName, String cardNumber, String expirationDate, String cvvNumber)
    {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
    }


    
    /** 
     * this method will create a list called paymentDetails and add/store the information about cardNumber, expirationDate, and cvvNumber into a list. 
     * @return return the customer's payment details: card number, cvv number and expiration date
     */
    public List<String> paymentInfo() {
        List<String> paymentDetails = new ArrayList<>();
        paymentDetails.add(cardHolderName);
        paymentDetails.add(cardNumber);
        paymentDetails.add(expirationDate);
        paymentDetails.add(cvvNumber);

        return paymentDetails;
    }

    
    /** 
     * String get method for the card number
     * @return card number of that customer 
     */
    public String getCardNumber(){
        return cardNumber;
    } 
    /** 
     * void set method for the customer's card number 
     * @param cardNumber takes the current customer's card number and set it 
     */
    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }

    /** 
     * String get method for the expiration date 
     * @return expiration date for that customer 
     */
    public String getExpirationDate() {
        return expirationDate;
    }
    /** 
     * void set method for the customer's expiration date 
     * @param expirationDate
     */

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }
    public void setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    /**
     * String method for the name on the card
     * @return card holder name
     */
    public String getCardHolderName(){
        return cardHolderName;
    }

    /**
     * void method for the card holder name
     * @param cardHolderName sets the name of the card holder
     */
    public void setCardHolderName(String cardHolderName){
        this.cardHolderName = cardHolderName;
    }
}