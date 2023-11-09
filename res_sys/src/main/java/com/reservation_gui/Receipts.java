package com.reservation_gui;
import javax.swing.*;
import java.awt.*;



public class Receipts{
    private Customer custInfo;
    private Room roomInfo;
    //private Payment paymentInfo;

    /* default constructor */
    public Receipts(){
        /* creates customer and room info */
        this.custInfo = new Customer("FirstName", "LastName", "(818)555-5555", "email@email.com");
        this.roomInfo = new Room("King", 2, 101, 1, false, false, false, 999.99);
    }

    /* paramiterized constructor takes customer and room info */
    public Receipts(Customer cust, Room room /* Payment pay */){
        this.custInfo = cust;
        this.roomInfo = room;
        //this.paymentInfo = pay;
    }
    
    /* generates receipt report for display */
    private JTextPane GenerateDisplayReceipt(){
        /* creating text pane for report */
        JTextPane reportTextPane = new JTextPane();
        
        /* adding report info to the text pane */
        reportTextPane.setText(
        "\t\t\tMatador Hotels Receipt for " + custInfo.getFirstName() + " " + custInfo.getLastName() + "\n" +
        "-----------------------------------------------------------------------------------------------------------------\n" +
        "Customer ID: " + custInfo.getCustID() +
        "\t\tConfirmation Number: " + custInfo.getConfrimNum() +
        "\tTransaction ID: " + custInfo.getTransID() + "\n" +
        "Phone Number: " + custInfo.getPhoneNum() +
        "\tEmail: " + custInfo.getEmail() + "\n" +
        "-----------------------------------------------------------------------------------------------------------------\n" +
        "Check-In Date: 10/27/23\t\t" +
        "Check-Out Date: 10/28/23\n" +
        "Bed Type: " + roomInfo.GetRoomType() + "\t\t" +
        "Bed Count: " + roomInfo.GetBedCount() + "\n" +
        "Room Number: " + roomInfo.GetRoomNumberString() + "\t\t" +
        "Floor Number: " + roomInfo.GetRoomFloorString() + "\n" +
        "Accessibility: " + roomInfo.GetRoomAccessibleString() + "\t\t" +
        "Smoking / Non-Smoking: " + roomInfo.GetRoomSmokingString() + "\n" +
        "\n\t\tPrice: $" + roomInfo.GetRoomPriceString()
        );
        return reportTextPane;
     }

     /* generates receipt report for printing */
     private JTextPane GeneratePrintReceipt(){
        /* creating text pane for report */
        JTextPane reportTextPane = new JTextPane();
        reportTextPane.setBackground(Color.WHITE);
        
        /* adding report info to the text pane */
        reportTextPane.setText(
        "\tMatador Hotels Receipt for " + this.custInfo.getFirstName() + " " + this.custInfo.getLastName() + "\n" +
        "-----------------------------------------------------------------------------------------------------------------------------\n" +
        "Customer ID: " + this.custInfo.getCustID() +
        "\t\tConfirmation Number: " + this.custInfo.getConfrimNum() +
        "\nTransaction ID: " + this.custInfo.getTransID() + "\n" +
        "Phone Number: " + this.custInfo.getPhoneNum() +
        "\tEmail: " + this.custInfo.getEmail() + "\n" +
        "------------------------------------------------------------------------------------------------------------------------------\n" +
        "Check-In Date: 10/27/23\t\t" +
        "Check-Out Date: 10/28/23\n" +
        "Bed Type: " + this.roomInfo.GetRoomType() + "\t\t" +
        "Bed Count: " + this.roomInfo.GetBedCount() + "\n" +
        "Room Number: " + this.roomInfo.GetRoomNumberString() + "\t\t" +
        "Floor Number: " + this.roomInfo.GetRoomFloorString() + "\n" +
        "Accessibility: " + this.roomInfo.GetRoomAccessibleString() + "\t\t" +
        "Smoking / Non-Smoking: " + this.roomInfo.GetRoomSmokingString() + "\n" +
        "\n\t\tPrice: $" + this.roomInfo.GetRoomPriceString()
        );
      return reportTextPane;
     }

     /* getter to display receipt */
    public JTextPane GetReceipt(){
        return GenerateDisplayReceipt();
    }

    /* function to download or print the report */
    public void downloadOrPrintReceipt(){
        /* generates printer receipt */
        JTextPane textPane = GeneratePrintReceipt(); 
        try{
            /* calls built in print/download function */
            textPane.print(null,null, true, null, null, true);
        }catch (java.awt.print.PrinterException e){
            e.printStackTrace();
        }
    }
}