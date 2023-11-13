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
    private Room[] roomList = new Room[13];
    private LinkedList<ReservationOptions> reservations = new LinkedList<ReservationOptions>();
    private ManipFile files;


    // constructors
    public Hotel()
    {
        // default initialization of the ManipFile object with location values of the data files
        files = new ManipFile("resFile.txt", "cusFile.txt", "roomFile.txt");

        // calls to the ManipFile object methods in order to populate the lists for customers, rooms, and reservations within the hotel
        files.PopulateCusContainer(customerList);
        files.PopulateRoomContainer(roomList);
        files.PopulateResContainer(reservations, customerList, roomList);
    }

    public Hotel(String resFileLoc, String cusFileLoc, String roomFileLoc)
    {
        // initialization of the ManipFile object with location values of the data files passed as variables
        files = new ManipFile(resFileLoc, cusFileLoc, roomFileLoc);

        files.PopulateCusContainer(customerList);
        files.PopulateRoomContainer(roomList);
        files.PopulateResContainer(reservations, customerList, roomList);
    }

    // methods

    // sets the hotel phone number value as a String
    public void setHotelPhone(String newPhone){
        hotelPhone = newPhone;
    }
    // sets the hotel check in & check out times as Strings
    public void setCheckInOut(String in, String out){
        checkIn = in;
        checkOut = out;
    }
    // adds a made reservation into the reservations list
    public void addReservation(ReservationOptions reservation){
        reservations.add(reservation);
    }
    // adds a customer into the customer list
    public void addCustomer(Customer cus){
        customerList.add(cus);
    }
    // gets the list of customers
    public LinkedList<Customer> getCustomerList(){
        return customerList;
    }
    // gets the list of rooms within the hotel
    public Room[] getRoomsList(){
        return roomList;
    }
    // gets the list of customers
    public LinkedList<ReservationOptions> getReservationList(){
        return reservations;
    }
    // gets the name of the hotel
    public String getHotelName(){
        return hotelName;
    }
    // gets address of hotel
    public String getHotelAddress(){
        return hotelAddress;
    }
    // gets hotel email
    public String getHotelEmail(){
        return hotelEmail;
    }
    // gets the hotel phone number
    public String getHotelPhone(){
        return hotelPhone;
    }
    // gets the hotel check in date
    public String getHotelCheckIn(){
        return checkIn;
    }
    // gets the hotel check out date
    public String getHotelCheckOut(){
        return checkOut;
    }
    // gets the manipFile object for end of program where the updates to the lists are uploaded into the data files
    public ManipFile getManipFile(){
        return files;
    }
    // retrieves the reservation within the reservation list based on customer name, either customer id or confirmation number, and a flag to specify between id or confirmation
    public ReservationOptions getReservation(String first, String last, String id, String flag){
        switch (flag) {
            case "Customer ID":
                for (ReservationOptions res : reservations){
                    // confirms that the name and the id number is matching within the list of reservations
                    if (res.getCustomer().getFirstName().equals(first) && res.getCustomer().getLastName().equals(last) 
                        && res.getCustomer().getCustID().equals(id)){
                            return res;
                        }
                }
                break;
            case "Confirmation Number":
                for (ReservationOptions res : reservations){
                    // confirms that the name and the confirmation number is matching within the list of reservations
                    if (res.getCustomer().getFirstName().equals(first) && res.getCustomer().getLastName().equals(last) 
                        && res.getCustomer().getConfrimNum().equals(id)){
                            return res;
                        }
                }
                break;
            default:
                break;
        }

        // error trigger if the reservation with those specific name and id/confirmation was not found
        ReservationOptions empty = new ReservationOptions();
        return empty;
    }
}
