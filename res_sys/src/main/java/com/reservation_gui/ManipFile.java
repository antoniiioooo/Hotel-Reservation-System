package com.reservation_gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManipFile {
    
    // members
    private String resFileLoc;
    private String cusFileLoc;
    private String roomFileLoc;
    BufferedWriter writer;
    BufferedReader reader;

    // constructors
    public ManipFile(String resFile, String cusFile, String roomFile)
    {
        resFileLoc = resFile;
        cusFileLoc = cusFile;
        roomFileLoc = roomFile;
    }

    // methods

    //public void PopulateResContainer(ReservationOptions[] reservations)
    //{
    //}
    public void PopulateCusContainer(Customer[] customers)
    {
        try {
            reader = new BufferedReader(new FileReader(cusFileLoc));
            int track = 0;
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] split = line.split(", ");
                customers[track] = new Customer(split[0], split[1], split[2], split[3]);
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
    public void PopulateRoomContainer(Room[] rooms)
    {
        try {
            reader = new BufferedReader(new FileReader(roomFileLoc));
            int track = 0;
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] split = line.split(", ");
                rooms[track] = new Room(split[0], split[1], split[2]);
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

    //public void UpdateResFile()
    //{
    //}
    public void UpdateCusFile(Customer[] customers)
    {
        try {
            writer = new BufferedWriter(new FileWriter(cusFileLoc));
            int track = 0;
            while (customers[track].getFirstName() != null)
            {
                writer.write(customers[track].getFirstName() + ", ");
                writer.write(customers[track].getLastName() + ", ");
                writer.write(customers[track].getPhoneNum() + ", ");
                writer.write(customers[track].getEmail() + ", ");
                writer.write(customers[track].getTransID() + "\n");
                track++;
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void UpdateRoomFile(Room[] rooms)
    {
        try {
            writer = new BufferedWriter(new FileWriter(roomFileLoc));
            int track = 0;
            while (rooms[track].GetRoomType() != "N/A")
            {
                writer.write(rooms[track].GetRoomType() + ", ");
                writer.write(rooms[track].GetRoomNumberString() + ", ");
                writer.write(rooms[track].GetRoomFloorString() + ", ");
                track++;
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
