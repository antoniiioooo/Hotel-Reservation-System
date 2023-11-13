package com.reservation_gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ReservationOptions {

    /* Create private variables */
    private Customer customer;
    private Room roomChosen;
    private Date checkInDate;
    private Date checkOutDate;
    private String checkInString;
    private String checkOutString;

    /* Constructor */
    public ReservationOptions(Customer customer, Room room, Date checkIn, Date checkOut) {
        // to format the Date objects into proper Strings
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        
        this.customer = customer;
        this.roomChosen = room;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.checkInString = df.format(checkIn);
        this.checkOutString = df.format(checkOut);
    }

    /* Constructor for string dates*/
    public ReservationOptions(Customer customer, Room room, String checkIn, String checkOut) {
        this.customer = customer;
        this.roomChosen = room;
        this.checkInString = checkIn;
        this.checkOutString = checkOut;
    }

    /* Constructor for default state*/
    public ReservationOptions() {
        this.customer = null;
        this.roomChosen = null;
        this.checkInString = null;
        this.checkOutString = null;
    }


    /* Methods */
    public void updateRoomAvailability(Hotel hotel) {
        /* Craete method in Hotel class to update room availability
        and a method in Room to change room unavailablilty */
        roomChosen.SetOccupancy(false); //Room class
        //hotel.updateRoomAvailability(roomChosen);  // Hotel class
    }

    /*  Update to Reservations List  */
    public void addToReservationList(Hotel hotel) {
        hotel.addReservation(this);  // Hotel class
    }

    /*  Getters and Setters */
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoomChosen() {
        return roomChosen;
    }
     public void setRoomChosen(Room room) {
        this.roomChosen = room;
    }
    
    public Date getCheckInDate() {
        return checkInDate;
    }
    public String getCheckInString(){
        return checkInString;
    }
    public void setCheckInDate(Date checkIn) {
        this.checkInDate = checkIn;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public String getCheckOutString(){
        return checkOutString;
    }
    public void setCheckOutDate(Date checkOut) {
        this.checkOutDate = checkOut;
    }
    // checks if the reservation is empty i.e. there is no customer or room associated with the reservation itself
    public boolean isEmpty(){
        if (customer == null || roomChosen == null){
            return true;
        }
        else {
            return false;
        }
    }
}
