package com.reservation_gui;
import javax.swing.*;
import java.awt.*;

/**
 * class to generate, display and print receipts
 * @author Nexaly Orellana
 * @version 1.1
 */


public class Receipts{
    private Customer custInfo;
    private Room roomInfo;
    private String checkInInfo, checkOutInfo;
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

    /* paramiterized constructor takes reservation info */
    public Receipts(ReservationOptions res){
        this.custInfo = res.getCustomer();
        this.roomInfo = res.getRoomChosen();
        this.checkInInfo = res.getCheckInString();
        this.checkOutInfo = res.getCheckOutString();
        //this.paymentInfo = pay;
    }
    
    
    /** 
     * generates receipt report for display
     * @return JTextPane formatted textpane field for displaying to screen
     */
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
        "Check-In Date: " + this.checkInInfo + "\t\t" +
        "Check-Out Date: " + this.checkOutInfo + "\n" +
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


     
     /** 
      * generates receipt report for printing
      * @return JTextPane formatted textpane field for printing and downloading to pdf
      */
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

     
    
    /** 
     * calls function to generate receipt for display and then returns the textpane to caller
     * @return JTextPane populated with receipt report for display
     */
    public JTextPane GetReceipt(){
        return GenerateDisplayReceipt();
    }

    /**
     * calls function to generate receipt for download or print 
     * then try to open pop up window for print/download functionality
     */
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