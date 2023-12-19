package com.reservation_gui;

/** 
 * Room is an object that holds pertinent information to a hotel room with certain features included as well
 * @author Joshua
 * @version 1.1
 */
public class Room 
{
    // variables
    private String roomType;
    private int bedCount;
    private int roomNumber;
    private int roomFloor;
    private boolean disabilityOption;
    private boolean smokingOption;
    private boolean roomOccupied;
    private double roomPrice;

    // constructors
    public Room()
    {
        roomType = "N/A";
        bedCount = 0;
        roomNumber = 0;
        roomFloor = 0;
        disabilityOption = false;
        smokingOption = false;
        roomOccupied = false;
        roomPrice = 0.0;
    }
    // initialize object with all passed values
    public Room(String bed, int count, int roomNum, int floor, boolean access, boolean smoking, boolean occupancy, double price)
    {
        roomType = bed;
        bedCount = count;
        roomNumber = roomNum;
        roomFloor = floor;
        disabilityOption = access;
        smokingOption = smoking;
        roomOccupied = occupancy;
        roomPrice = price;
    }

    
    /** 
     * gets room/bed type
     * @author Joshua
     * @version 1.1
     * @return String the type of room String is returned
     */
    public String GetRoomType(){
        return roomType;
    }
    
    /** 
     * gets bed count
     * @author Joshua
     * @version 1.1
     * @return int the number of beds within the room itself
     */
    public int GetBedCount(){
        return bedCount;
    }
    
    /** 
     * gets the bed count but parsed into String
     * @author Joshua
     * @version 1.1
     * @return String count of beds within the room
     */
    public String GetBedCountString(){
        return Integer.toString(bedCount);
    }
    
    /** 
     * gets the price per night of the room
     * @author Joshua
     * @version 1.1
     * @return double price per night of the room
     */
    public double GetRoomPrice(){
        return roomPrice;
    }
    
    /** 
     * gets the room price parsed as a String
     * @author Joshua
     * @version 1.1
     * @return String the price per night of a room parsed into a String
     */
    public String GetRoomPriceString(){
        return Double.toString(roomPrice);
    }
    
    /** 
     * gets the room number
     * @author Joshua
     * @version 1.1
     * @return int designated room number for the room
     */
    public int GetRoomNumber(){
        return roomNumber;
    }
    
    /** 
     * gets the room number of the room but as a String
     * @author Joshua
     * @version 1.1
     * @return String the room number parsed into a String
     */
    public String GetRoomNumberString(){
        return Integer.toString(roomNumber);
    }
    
    /** 
     * gets floor where room is
     * @author Joshua
     * @version 1.1
     * @return int the floor number of where the room is
     */
    public int GetRoomFloor(){
        return roomFloor;
    }
    
    /** 
     * gets the floor where the room is but as a String
     * @author Joshua
     * @version 1.1
     * @return String room floor parsed into a String
     */
    public String GetRoomFloorString()
    {
        return Integer.toString(roomFloor);
    }
    
    /** 
     * check if the room is occupied
     * @author Joshua
     * @version 1.1
     * @return boolean room is occupied if true, vacant if false
     */
    public boolean GetRoomOccupied(){
        return roomOccupied;
    }
    
    /** 
     * check occupancy as string
     * @author Joshua
     * @version 1.1
     * @return String occupancy status as a String
     */
    public String GetRoomOccupiedString(){
        if (roomOccupied) {return "Yes";}
        else {return "No";}
    }
    
    /** 
     * get accessibilty/disability option available or not
     * @author Joshua
     * @version 1.1
     * @return boolean true if accessible, false if not
     */
    public boolean GetRoomAccessible(){
        return disabilityOption;
    }
    
    /** 
     * get accessibilty/disability option as string
     * @author Joshua
     * @version 1.1
     * @return String accessibility boolean parsed into a String
     */
    public String GetRoomAccessibleString(){
        if (disabilityOption) {return "Yes";}
        else {return "No";}
    }
    
    /** 
     * check if room allows smoking
     * @author Joshua
     * @version 1.1
     * @return boolean smoking room either allowed or not
     */
    public boolean GetRoomSmoking(){
        return smokingOption;
    }
    
    /** 
     * smoking availability as string
     * @author Joshua
     * @version 1.1
     * @return String smoking room as a yes or no option
     */
    public String GetRoomSmokingString(){
        if (smokingOption) {return "Yes";}
        else {return "No";}
    }

    /** 
     * sets the room's number
     * @author Joshua
     * @version 1.1
     * @param newNum the new room number for the room
     */
    public void SetRoomNumber(int newNum){
        roomNumber = newNum;
    }
    
    /** 
     * sets the room floor
     * @author Joshua
     * @version 1.1
     * @param newF the new room floor number to be set
     */
    public void SetRoomFloor(int newF){
        roomFloor = newF;
    }
    
    /** 
     * sets whether the room is accessible for disabled individuals or not by passed variable
     * @author Joshua
     * @version 1.1
     * @param state new state of accessibility for the room
     */
    public void SetAccessibility(boolean state){
        disabilityOption = state;
    }
    
    /** 
     * sets if room is smoking or non-smoking
     * @author Joshua
     * @version 1.1
     * @param state new boolean state for a smoking room or not
     */
    public void SetSmoking(boolean state){
        smokingOption = state;
    }
    
    /** 
     * sets if the room is currently occupied
     * @author Joshua
     * @version 1.1
     * @param state new occupancy state for the room
     */
    public void SetOccupancy(boolean state){
        roomOccupied = state;
    }

}
