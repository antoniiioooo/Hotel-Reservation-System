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
    public Room(String bed, String num, String floor)
    {
        roomType = bed;
        if (bed.equals("Suite"))
        {
            roomType = bed;
            bedType = "King";
            bedCount = 2;
            roomNumber = Integer.parseInt(num);
            roomFloor = Integer.parseInt(floor);
            disabilityOption = false;
            smokingOption = true;
            roomOccupied = false;
            roomPrice = 1249.99;
        }
        else if (bed.equals("King")){
            roomType = bed;
            bedType = "King";
            bedCount = 1;
            roomNumber = Integer.parseInt(num);
            roomFloor = Integer.parseInt(floor);
            disabilityOption = false;
            smokingOption = false;
            roomOccupied = false;
            roomPrice = 999.99;
        }
        else if (bed.equals("Queen")){
            roomType = bed;
            bedType = "Queen";
            bedCount = 2;
            roomNumber = Integer.parseInt(num);
            roomFloor = Integer.parseInt(floor);
            disabilityOption = false;
            smokingOption = false;
            roomOccupied = false;
            roomPrice = 749.99;
        }
        else if (bed.equals("Twin")){
            roomType = bed;
            bedType = "Twin";
            bedCount = 2;
            roomNumber = Integer.parseInt(num);
            roomFloor = Integer.parseInt(floor);
            disabilityOption = false;
            smokingOption = false;
            roomOccupied = false;
            roomPrice = 499.99;
        }
        else {
            bedType = "N/A";
            bedCount = 0;
            roomNumber = 0;
            roomFloor = 0;
            disabilityOption = false;
            smokingOption = false;
            roomOccupied = false;
            roomPrice = 0;
        }


    }

    
    // accessor methods
    public String GetRoomType(){
        return roomType;
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

    // alter methods
    public void changeRoomNumber(int newNum){
        roomNumber = newNum;
    }
    public void changeRoomFloor(int newF){
        roomFloor = newF;
    }
    public void changeAccessibility(){
        if (disabilityOption){
            disabilityOption = false;
        }
        else {disabilityOption = true;}
    }
    public void changeSmoking(){
        if (smokingOption){
            smokingOption = false;
        }
        else {smokingOption = true;}
    }
    public void changeOccupancy(boolean state){
        roomOccupied = state;
    }

}
