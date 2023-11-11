package com.reservation_gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class ManipFile {
    
    // members
    private String resFileLoc;
    private String cusFileLoc;
    private String roomFileLoc;
    BufferedWriter writer;
    BufferedReader reader;

    // constructors

    // sets the locations of the designated files through passed String variables
    public ManipFile(String resFile, String cusFile, String roomFile)
    {
        
        resFileLoc = resFile;
        cusFileLoc = cusFile;
        roomFileLoc = roomFile;
    }

    // methods

    // populates the reservation list with information from the reservations data file; passed the list to update for the hotel
    public void PopulateResContainer(LinkedList<ReservationOptions> reservations, LinkedList<Customer> customers, Room[] rooms)
    {
        try {
            reader = new BufferedReader(new FileReader(resFileLoc));
            String line;
            reader.readLine();

            // variables to store customer and room for the reservation to be put into the reservation list
            Customer custChosen = new Customer();
            Room roomChosen = new Room();

            while ((line = reader.readLine()) != null)
            {
                String[] split = line.split(", ");
                
                // loop through the customer list to find specific customer with unique ID, retrieve the full customer
                for (Customer cust : customers){
                    if (cust.getCustID().equals(split[0]))
                        custChosen = cust;
                }

                // loop through rooms list to find specific room number, retrieve the full room
                for (Room room : rooms){
                    if (room.GetRoomNumberString().equals(split[1]))
                        roomChosen = room;
                }

                // add reservation to the list with appropriate information retrieved
                reservations.add(new ReservationOptions(custChosen, roomChosen, split[2], split[3]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // populates the customer list with information from the customers data file; passed the list to update for the hotel
    public void PopulateCusContainer(LinkedList<Customer> customers)
    {
        try {
            reader = new BufferedReader(new FileReader(cusFileLoc));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                // line input from file is split, where order goes from (firstName, lastName, phone, email, customer ID, confirmation number, transaction ID)
                String[] split = line.split(", ");
                customers.add(new Customer(split[0], split[1], split[2], split[3], split[4], split[5], split[6]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // populates the rooms list with information from the room data file; passed the list to update for the hotel
    public void PopulateRoomContainer(Room[] rooms)
    {
        try {
            reader = new BufferedReader(new FileReader(roomFileLoc));
            String line;
            reader.readLine();
            int track = 0;
            while ((line = reader.readLine()) != null)
            {
                // line split in this order: (String bed, int count, int roomNum, int floor, boolean access, boolean smoking, boolean occupancy, double price)
                String[] split = line.split(", ");
                rooms[track] = new Room(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), 
                Boolean.parseBoolean(split[4]), Boolean.parseBoolean(split[5]), Boolean.parseBoolean(split[6]), Double.parseDouble(split[7]));
                track++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // method to update the reservation data file with the new reservation list
    public void UpdateResFile(LinkedList<ReservationOptions> reservations)
    {
         try {
            writer = new BufferedWriter(new FileWriter(resFileLoc));
            writer.write("CustID, RoomNum, CheckIn, CheckOut\n");
            for (ReservationOptions res : reservations)
            {
                // syntax for the reservation data file with all the required info for populating the list in another run of the program
                writer.write(res.getCustomer().getCustID() + ", ");
                writer.write(res.getRoomChosen().GetRoomNumberString() + ", ");
                writer.write(res.getCheckInString() + ", ");
                writer.write(res.getCheckOutString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // method to update the customer data file with the new customer list
    public void UpdateCusFile(LinkedList<Customer> customers)
    {
        try {
            writer = new BufferedWriter(new FileWriter(cusFileLoc));
            writer.write("First, Last, Phone, Email, CustID, ConfirmNum, TransID\n");
            for (Customer cus : customers)
            {
                // syntax for the customer data file with all the required info for populating the list in another run of the program
                writer.write(cus.getFirstName() + ", ");
                writer.write(cus.getLastName() + ", ");
                writer.write(cus.getPhoneNum() + ", ");
                writer.write(cus.getEmail() + ", ");
                writer.write(cus.getCustID() + ", ");
                writer.write(cus.getConfrimNum() + ", ");
                writer.write(cus.getTransID() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // method to update the room data file with the new room list
    public void UpdateRoomFile(Room[] rooms)
    {
        try {
            writer = new BufferedWriter(new FileWriter(roomFileLoc));
            writer.write("BedType, BedCount, RoomNum, RoomFloor, Accessibility, Smoking, Occupancy, Price\n");
            for(int i = 0; i < rooms.length; i++)
            {
                // array list progression for room details needed to make new room objects once back in the populate functions
                writer.write(rooms[i].GetRoomType() + ", ");
                writer.write(rooms[i].GetBedCount() + ", ");
                writer.write(rooms[i].GetRoomNumberString() + ", ");
                writer.write(rooms[i].GetRoomFloorString() + ", ");
                writer.write(rooms[i].GetRoomAccessibleString() + ", ");
                writer.write(rooms[i].GetRoomSmokingString() + ", ");
                writer.write(rooms[i].GetRoomOccupiedString() + ", ");
                writer.write(rooms[i].GetRoomPriceString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
