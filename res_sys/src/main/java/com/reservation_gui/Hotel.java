package com.reservation_gui;

import java.util.LinkedList;

public class Hotel 
{
    // members
    private String hotelName = "Matador Hotels";
    private String hotelAddress = "12345 Some St., Northridge, CA, 91330";
    private String hotelEmail = "MatadorHotels@matador.com";
    private String hotelPhone = "555-555-5555";
    private String checkIn = "1:00 PM";
    private String checkOut = "10:00 AM";
    private LinkedList<Customer> customerList = new LinkedList<Customer>();
    private LinkedList<Room> roomList = new LinkedList<Room>();
    private LinkedList<ReservationOptions> reservations = new LinkedList<ReservationOptions>();
    private ManipFile files;


    // constructors
    public Hotel()
    {
        files = new ManipFile("resFile.txt", "cusFile.txt", "roomFile.txt");
        //files.PopulateResContainer(reservations);
        //files.PopulateCusContainer(customerList);
        files.PopulateRoomContainer(roomList);
    }

    public Hotel(String resFileLoc, String cusFileLoc, String roomFileLoc)
    {
        files = new ManipFile(resFileLoc, cusFileLoc, roomFileLoc);
        //files.PopulateResContainer(reservations);
        //files.PopulateCusContainer(customerList);
        files.PopulateRoomContainer(roomList);
    }

    // methods
    public void UpdateHotelPhone(String newPhone){
        hotelPhone = newPhone;
    }
    public void UpdateCheckInOut(String in, String out){
        checkIn = in;
        checkOut = out;
    }
    public void addReservation(ReservationOptions reservation){
        reservations.add(reservation);
    }

    public LinkedList<Room> getRoomsList(){
        return roomList;
    }
    public String getHotelName(){
        return hotelName;
    }
        public String getHotelAddress(){
        return hotelAddress;
    }
        public String getHotelEmail(){
        return hotelEmail;
    }
        public String getHotelPhone(){
        return hotelPhone;
    }
        public String getHotelCheckIn(){
        return checkIn;
    }
        public String getHotelCheckOut(){
        return checkOut;
    }
}
