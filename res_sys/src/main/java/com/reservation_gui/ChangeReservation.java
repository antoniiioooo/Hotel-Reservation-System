package com.reservation_gui;

/**
 * ChangeReservation allows the customer to reenter personal information that corresponds to their existing reservation
 * @author Joey Gomez
 * @version 1.1 
 */
public class ChangeReservation {
    private Customer customer;
    private Payment payment;

    public ChangeReservation(Customer customer, Payment payment) {
        this.customer = customer;
        this.payment = payment;
    }

    /**
     * updates the customer information with the given user input
     * @param firstName
     * @param lastName
     * @param phone
     * @param email
     */
    public void updateCustomerInfo(String firstName, String lastName, String phone, String email) {
        // Update customer information
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNum(phone);
        customer.setEmail(email);
    }

    /**
     * updates the customers payment information with given user input
     * @param cardHolderName
     * @param cardNumber
     * @param expirationDate
     * @param cvv
     */
    public void updatePaymentInfo(String cardHolderName, String cardNumber, String expirationDate, String cvv) {
        //Update payment information
        payment.setCardHolderName(cardHolderName);
        payment.setCardNumber(cardNumber);
        payment.setExpirationDate(expirationDate);
        payment.setCvvNumber(cvv);
    }

    /**
     * gets stored customer information and returns it to caller
     * @return Customer 
     */
    public Customer getCustomer(){
        return customer;
    }
}       