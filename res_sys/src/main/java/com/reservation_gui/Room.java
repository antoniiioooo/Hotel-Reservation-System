package com.reservation_gui;

public class Room 
{
    // variables
    private String roomType;
    private String bedType;
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
        bedType = "N/A";
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
        bedType = bed;
        bedCount = count;
        roomNumber = roomNum;
        roomFloor = floor;
        disabilityOption = access;
        smokingOption = smoking;
        roomOccupied = occupancy;
        roomPrice = price;
    }

    // accessor methods

    // gets room/bed type
    public String GetRoomType(){
        return roomType;
    }
    // gets bed count
    public int GetBedCount(){
        return bedCount;
    }
    // gets the bed count but parsed into String
    public String GetBedCountString(){
        return Integer.toString(bedCount);
    }
    // gets the price per night of the room
    public double GetRoomPrice(){
        return roomPrice;
    }
    // room price String option
    public String GetRoomPriceString(){
        return Double.toString(roomPrice);
    }
    // gets the room number
    public int GetRoomNumber(){
        return roomNumber;
    }
    // room number String option
    public String GetRoomNumberString(){
        return Integer.toString(roomNumber);
    }
    // gets floor where room is
    public int GetRoomFloor(){
        return roomFloor;
    }
    // room floor as String
    public String GetRoomFloorString()
    {
        return Integer.toString(roomFloor);
    }
    // check if the room is occupied
    public boolean GetRoomOccupied(){
        return roomOccupied;
    }
    // check occupancy as string
    public String GetRoomOccupiedString(){
        if (roomOccupied) {return "Yes";}
        else {return "No";}
    }
    // get accessibilty/disability option
    public boolean GetRoomAccessible(){
        return disabilityOption;
    }
    // get accessibilty/disability option as string
    public String GetRoomAccessibleString(){
        if (disabilityOption) {return "Yes";}
        else {return "No";}
    }
    // check if room allows smoking
    public boolean GetRoomSmoking(){
        return smokingOption;
    }
    // smoking availability as string
    public String GetRoomSmokingString(){
        if (smokingOption) {return "Yes";}
        else {return "No";}
    }

    // alter methods
    // sets the room's number
    public void SetRoomNumber(int newNum){
        roomNumber = newNum;
    }
    // sets the room floor
    public void SetRoomFloor(int newF){
        roomFloor = newF;
    }
    // sets whether the room is accessible for disabled individuals or not by passed variable
    public void SetAccessibility(boolean state){
        disabilityOption = state;
    }
    // sets if room is smoking or non-smoking
    public void SetSmoking(boolean state){
        smokingOption = state;
    }
    // sets if the room is currently occupied
    public void SetOccupancy(boolean state){
        roomOccupied = state;
    }

}
