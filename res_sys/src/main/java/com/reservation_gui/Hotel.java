package com.reservation_gui;

import java.util.LinkedList;

/** 
     * Hotel is the main container class of the system, where it holds the lists of reservations and rooms and customers
     * as well as the methods to manipulate those lists however needed
     * @author Joshua
     * @version 1.1
     */
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

    
    /** 
     * sets the hotel phone number value as a String
     * @author Joshua
     * @version 1.1
     * @param newPhone to set the new hotel phone number
     */
    public void setHotelPhone(String newPhone){
        hotelPhone = newPhone;
    }

    /** 
     * sets the hotel check in & check out times as Strings
     * @author Joshua
     * @param in for setting the check-in date
     * @param out for setting the check-out date
     * @version 1.1
     */
    public void setCheckInOut(String in, String out){
        checkIn = in;
        checkOut = out;
    }
    
    /**
     * adds a made reservation into the reservations list
     * @author Joshua
     * @version 1.1
     * @param reservation object to be added in the list of ReservationOptions within Hotel
     */
    // 
    public void addReservation(ReservationOptions reservation){
        reservations.add(reservation);
    }
    
    /** 
     * adds a customer into the customer list
     * @author Joshua
     * @version 1.1
     * @param cus Customer object to be added within the Customer linked list of Hotel object
     */
    public void addCustomer(Customer cus){
        customerList.add(cus);
    }
    
    /** 
     * gets the list of customers
     * @author Joshua
     * @version 1.1
     * @return LinkedList<Customer> is the list of customers within the Hotel object
     */
    public LinkedList<Customer> getCustomerList(){
        return customerList;
    }
    
    /** 
     * gets the list of rooms within the hotel
     * @author Joshua
     * @version 1.1
     * @return Room[] is the rooms list within the Hotel object
     */
    public Room[] getRoomsList(){
        return roomList;
    }
    
    /** 
     * gets the list of customers
     * @author Joshua
     * @version 1.1
     * @return LinkedList<ReservationOptions> reservations list within the Hotel object
     */
    public LinkedList<ReservationOptions> getReservationList(){
        return reservations;
    }
    
    /** 
     * gets the name of the hotel
     * @author Joshua
     * @version 1.1
     * @return String the name of the hotel
     */
    public String getHotelName(){
        return hotelName;
    }
    
    /** 
     * gets address of hotel
     * @author Joshua
     * @version 1.1
     * @return String the address of the hotel in full
     */
    public String getHotelAddress(){
        return hotelAddress;
    }
    
    /** 
     * gets hotel email
     * @author Joshua
     * @version 1.1
     * @return String the associated email of the hotel
     */
    public String getHotelEmail(){
        return hotelEmail;
    }
    
    /** 
     * gets the hotel phone number
     * @author Joshua
     * @version 1.1
     * @return String the hotel phone number in String form
     */
    public String getHotelPhone(){
        return hotelPhone;
    }
    
    /** 
     * gets the hotel check in date
     * @author Joshua
     * @version 1.1
     * @return String the check-in time of the entire hotel
     */
    public String getHotelCheckIn(){
        return checkIn;
    }
    
    /** 
     * gets the hotel check out date
     * @author Joshua
     * @version 1.1
     * @return String the check-out time of the entire hotel
     */
    public String getHotelCheckOut(){
        return checkOut;
    }
    
    /** 
     * gets the manipFile object for end of program where the updates to the lists are uploaded into the data files
     * @author Joshua
     * @version 1.1
     * @return ManipFile the object containing the adresses and functions for accessing the data text files
     */
    public ManipFile getManipFile(){
        return files;
    }
    
    /** 
     * retrieves the reservation within the reservation list based on customer name, either customer id or confirmation number, 
     * and a flag to specify between id or confirmation
     * @author Joshua
     * @version 1.1
     * @param first name on the reservation being search for 
     * @param last name on the reservation being searched for
     * @param id number associated with customer and the reservation
     * @param flag determines whether to search for customer ID number or confirmation ID number of the reservation and customer
     * @return ReservationOptions the located reservation with the correct matching names and ID number associated
     */
    public ReservationOptions getReservation(String first, String last, String id, String flag){
        switch (flag) {
            case "Customer ID":
                for (ReservationOptions res : reservations){
                    // confirms that the name and the id number is matching within the list of reservations
                    if (res.getCustomer().getFirstName().toLowerCase().equals(first.toLowerCase()) && res.getCustomer().getLastName().toLowerCase().equals(last.toLowerCase()) 
                        && res.getCustomer().getCustID().equals(id)){
                            return res;
                        }
                }
                break;
            case "Confirmation Number":
                for (ReservationOptions res : reservations){
                    // confirms that the name and the confirmation number is matching within the list of reservations
                    if (res.getCustomer().getFirstName().toLowerCase().equals(first.toLowerCase()) && res.getCustomer().getLastName().toLowerCase().equals(last.toLowerCase()) 
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
