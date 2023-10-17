package com.reservation_gui;

public class Room 
{
    // variables
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
        bedType = "N/A";
        bedCount = 0;
        roomNumber = 0;
        roomFloor = 0;
        disabilityOption = false;
        smokingOption = false;
        roomOccupied = false;
        roomPrice = 0.0;
    }
    public Room(String bed, int count, int roomNum, int floor, boolean access, boolean smoking, boolean occupancy, double price)
    {
        bedType = bed;
        bedCount = count;
        roomNumber = roomNum;
        roomFloor = floor;
        disabilityOption = access;
        smokingOption = smoking;
        roomOccupied = occupancy;
        roomPrice = price;
    }

    // methods
    public String GetRoomType(){
        return bedType;
    }
    public int GetBedCount(){
        return bedCount;
    }
    public String GetBedCountString(){
        return Integer.toString(bedCount);
    }
    public double GetRoomPrice(){
        return roomPrice;
    }
    public String GetRoomPriceString(){
        return Double.toString(roomPrice);
    }
    public int GetRoomNumber(){
        return roomNumber;
    }
    public String GetRoomNumberString(){
        return Integer.toString(roomNumber);
    }
    public int GetRoomFloor(){
        return roomFloor;
    }
    public String GetRoomFloorString()
    {
        return Integer.toString(roomFloor);
    }
    public boolean CheckRoomOccupied(){
        return roomOccupied;
    }
    public String CheckRoomOccupiedString(){
        if (roomOccupied) {return "Yes";}
        else {return "No";}
    }
    public boolean CheckRoomAccessible(){
        return disabilityOption;
    }
    public String CheckRoomAccessibleString(){
        if (roomOccupied) {return "Yes";}
        else {return "No";}
    }
    public boolean CheckRoomSmoking(){
        return smokingOption;
    }
    public String CheckRoomSmokingString(){
        if (smokingOption) {return "Yes";}
        else {return "No";}
    }


}
