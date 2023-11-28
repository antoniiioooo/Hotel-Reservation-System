package com.reservation_gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/** 
     * ManipFile is used to read and write information to and from the data text files to preserve and update the information of the
     * containers for reservations, customers, and rooms
     * @author Joshua
     * @version 1.1
     */
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

    
    /** 
     * populates the reservation list with information from the reservations data file; passed the list to update for the hotel
     * @author Joshua
     * @param reservations is a linked list containing the reservations list to be populated for the Hotel object
     * @param customers is a Customer object linked list that is used to populate the ReservationOptions objects with their customers
     * @param rooms is the list of rooms of the hotel used to put the respective room info into the ReservationOptions objects
     * @version 1.1
     */
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
    
    /** 
     * populates the customer list with information from the customers data file; passed the list to update for the hotel
     * @author Joshua
     * @param customers is the linked list Customer container to be updated/populated with the data file
     * @version 1.1
     */
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
    
    /** 
     * populates the rooms list with information from the room data file; passed the list to update for the hotel
     * @author Joshua
     * @param rooms is the Room array to be filled with the pertinent Room objects from the data file
     * @version 1.1
     */
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
                split[4].toLowerCase().equals("yes") ? true : false,  split[5].toLowerCase().equals("yes") ? true : false, split[6].toLowerCase().equals("yes") ? true : false, Double.parseDouble(split[7]));
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

    
    /** method to update the reservation data file with the new reservation list
     * @author Joshua
     * @param reservations is the linked list of ReservationOptions objects used to update the data file
     * @version 1.1
     */
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
    
    /** 
     * method to update the customer data file with the new customer list
     * @author Joshua
     * @param customers linked list of Customer objects used to update the appropriate data file
     * @version 1.1
     */
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
    
    /** 
     * method to update the room data file with the new room list
     * @author Joshua
     * @param rooms Room array used to write into data file
     * @version 1.1
     */
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
