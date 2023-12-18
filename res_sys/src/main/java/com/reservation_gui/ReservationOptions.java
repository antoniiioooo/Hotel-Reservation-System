package com.reservation_gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Will format the Date objects into proper Strings and contains a method that will update the room availability 
 * and then add the most current reservation into the Hotel class
 * @author Joey Gomez
 * @Version 1.1
 */
public class ReservationOptions {

    /* Create private variables */
    private Customer customer;
    private Room roomChosen;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String checkInString;
    private String checkOutString;
    private Payment payment;

    /* Constructor */
    public ReservationOptions(Customer customer, Room room, Date checkIn, Date checkOut, Payment payment) {
        /*  to format the Date objects into proper Strings */
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        
        this.customer = customer;
        this.roomChosen = room;
        this.checkInDate = checkIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.checkOutDate = checkOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.checkInString = df.format(checkIn);
        this.checkOutString = df.format(checkOut);
        this.payment = payment;
    }

    /* Constructor for LocalDate dates */
    public ReservationOptions(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut, Payment payment) {
        /*  to format the Date objects into proper Strings */
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        this.customer = customer;
        this.roomChosen = room;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.checkInString = df.format(checkIn);
        this.checkOutString = df.format(checkOut);
        this.payment = payment;
    }

    /* Constructor for string dates*/
    public ReservationOptions(Customer customer, Room room, String checkIn, String checkOut, Payment payment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.customer = customer;
        this.roomChosen = room;
        this.checkInDate = LocalDate.parse(checkIn, formatter);
        this.checkOutDate = LocalDate.parse(checkOut, formatter);
        this.checkInString = checkIn;
        this.checkOutString = checkOut;
        this.payment = payment;
    }

     /* Constructor for string dates*/
    public ReservationOptions(Customer customer, Room room, String checkIn, String checkOut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.customer = customer;
        this.roomChosen = room;
        this.checkInDate = LocalDate.parse(checkIn, formatter);
        this.checkOutDate = LocalDate.parse(checkOut, formatter);
        this.checkInString = checkIn;
        this.checkOutString = checkOut;
    }


    /* Constructor for default state*/
    public ReservationOptions() {
        this.customer = null;
        this.roomChosen = null;
        this.checkInString = null;
        this.checkOutString = null;
        this.payment = null;
    }

    /** 
     * Method that will go into the Hotel class and update the room availability  
     * @param hotel passing a hotel object 
     */
    public void updateRoomAvailability(Hotel hotel) {
        roomChosen.SetOccupancy(false); //Room class
        //hotel.updateRoomAvailability(roomChosen);  // Hotel class
    }

    
    /** 
     * Method called addToReservationList that will go into the hotel class and then add that current reservation
     * @param hotel passing a hotel object 
     */
    public void addToReservationList(Hotel hotel) {
        hotel.addReservation(this);  
    }

    /** 
     * get method for the current customer 
     * @return will return that current customer
     */

    public Customer getCustomer() {
        return customer;
    }
    /** 
     * set method for the current customer
     * @param customer passing a Customer object 
     */

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    /** 
     * get method for the room chosen 
     * @return will return that specific room
     */
    public Room getRoomChosen() {
        return roomChosen;
    }
     /** 
      * set method for the current room being reserved 
      * @param room passing a Room object 
      */
     public void setRoomChosen(Room room) { 
        this.roomChosen = room;
    }
    
    /** 
     * get method for that check in date for the room being reserved (Date)
     * @return will return that check in date
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    
    /** 
     * get method for that check in date for the room being reserved (String)
     * @return will return that check in date 
     */
    public String getCheckInString(){
        return checkInString;
    }

    /** 
     * set method for the check in date 
     * @param checkIn passing a Date object
     */
    public void setCheckInDate(Date checkIn) {
        this.checkInDate = checkIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /** 
     * get method for the check out date for that room being reserved (Date)
     * @return will return that check out date
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /** 
     * get method for the check out date for the room being reserved (String)
     * @return will return that check out date 
     */
    public String getCheckOutString(){
        return checkOutString;
    }

    
    /** 
     * set method for the check out date 
     * @param checkOut passing a Date object
     */
    public void setCheckOutDate(Date checkOut) {
        this.checkOutDate = checkOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    
    /** 
     * Method that checks to see if the reservation is empty i.e. there is no customer or room associated with the reservation itself 
     * @return boolean
     */
    public boolean isEmpty(){
        if (customer == null || roomChosen == null){
            return true;
        }
        else {
            return false;
        }
    }

    public Payment getPayment(){
        return payment;
    }
    public void setPayment(Payment payment){
        this.payment = payment;
    }
}
