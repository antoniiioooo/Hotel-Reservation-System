package com.reservation_gui;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JTextPane;

public class ManagerReport {
    
    private LinkedList<ReservationOptions> resList;
    private LinkedList<Customer> cusList;
    private Room[] roomList;    

    // default constructor takes in lists within hotel
    public ManagerReport(LinkedList<ReservationOptions> res, LinkedList<Customer> cus, Room[] room) {
        resList = res;
        cusList = cus;
        roomList = room;
    }

    // displays the list of reservations for the hotel
    public JTextPane getReservationList() {
        JTextPane reservations = new JTextPane();
        reservations.setEditable(false);
        reservations.setBackground(new Color(158,158,158));
        reservations.setFont(new Font("MV Boli", Font.PLAIN, 13));

        // goes through the list of reservations within the hotel and prints all the details associated with it
        for (ReservationOptions res : resList) {
            reservations.setText(reservations.getText() +
            "-----------------------------------------------------------------------------------------------------------------------------\n" +
            "Customer Name: " + res.getCustomer().getFirstName() + " " + res.getCustomer().getLastName() + "\n" + 
            "Customer ID: " + res.getCustomer().getCustID() + "\t\t" +
            "Confirmation Number: " + res.getCustomer().getConfrimNum() + "\t\t" +
            "Transaction ID: " + res.getCustomer().getTransID() + "\n" +
            "Check-In Date: " + res.getCheckInString() + "\t\t" +
            "Check-Out Date: " + res.getCheckOutString() + "\n" +
            "Bed Type: " + res.getRoomChosen().GetRoomType() + "\t\t" +
            "Room Number: " + res.getRoomChosen().GetRoomNumberString() + "\t\t" +
            "Price: $" + res.getRoomChosen().GetRoomPrice() + "\n" +
            "-----------------------------------------------------------------------------------------------------------------------------\n"
            );
        }

        return reservations;
    }

    // displays the list of customers within the hotel
    public JTextPane getCustomerList() {

        JTextPane customers = new JTextPane();
        customers.setEditable(false);
        customers.setBackground(new Color(158,158,158));
        customers.setFont(new Font("MV Boli", Font.PLAIN, 13));

        // displays all the customers within the hotel that have set reservations
        for (Customer cus : cusList) {
            customers.setText(customers.getText() + 
            "-----------------------------------------------------------------------------------------------------------------------------\n" +
            "Customer Name: " + cus.getFirstName() + " " + cus.getLastName() + "\n" + 
            "Customer ID: " + cus.getCustID() + "\t\t" +
            "Confirmation Number: " + cus.getConfrimNum() + "\t\t" +
            "Transaction ID: " + cus.getTransID() + "\n" +
            "Phone Number: " + cus.getPhoneNum() + "\t\t" +
            "Email: " + cus.getEmail() + "\n" +
            "-----------------------------------------------------------------------------------------------------------------------------\n"
            );
        }

        return customers;
    }

    // displays the rooms within the hotel
    public JTextPane getRoomList() {

        JTextPane rooms = new JTextPane();
        rooms.setEditable(false);
        rooms.setBackground(new Color(158,158,158));
        rooms.setFont(new Font("MV Boli", Font.PLAIN, 13));

        // shows the list of rooms within the hotel
        for (Room room : roomList) {
            rooms.setText(rooms.getText() + 
            "-----------------------------------------------------------------------------------------------------------------------------\n" +
            "Bed Type: " + room.GetRoomType() + "\t\t" +
            "Bed Count: " + room.GetBedCount() + "\n" +
            "Room Number: " + room.GetRoomNumberString() + "\t\t" +
            "Floor Number: " + room.GetRoomFloorString() + "\n" +
            "Accessibility: " + room.GetRoomAccessibleString() + "\t\t" +
            "Smoking / Non-Smoking: " + room.GetRoomSmokingString() + "\n" +
            "Price: $" + room.GetRoomPriceString() +
            "\n-----------------------------------------------------------------------------------------------------------------------------\n"
            );
        }

        return rooms;
    }
}
