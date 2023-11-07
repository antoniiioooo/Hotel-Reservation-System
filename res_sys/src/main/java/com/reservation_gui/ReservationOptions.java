package com.reservation_gui;

import java.util.Date;

public class ReservationOptions {

    /* Create private variables */
    private Customer customer;
    private Room roomChosen;
    private Date checkInDate;
    private Date checkOutDate;

    /* Constructor */
    public ReservationOptions(Customer customer, Room room, Date checkIn, Date checkOut) {
        this.customer = customer;
        this.roomChosen = room;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
    }

    /* Methods */
    public void updateRoomAvailability(Hotel hotel) {
        /* Craete method in Hotel class to update room availability
        and a method in Room to change room unavailablilty */
        roomChosen.setAvailable(false); //Room class
        hotel.updateRoomAvailability(roomChosen);  // Hotel class
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
    public void setCheckInDate(Date checkIn) {
        this.checkInDate = checkIn;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(Date checkOut) {
        this.checkOutDate = checkOut;
    }
}
