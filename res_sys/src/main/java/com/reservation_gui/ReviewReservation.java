package com.reservation_gui;
import javax.swing.JTextPane;

public class ReviewReservation {
    private Customer cust; 
    private Room room; 

    /* constructors */
    public ReviewReservation () {
        this.cust = new Customer("FirstName", "LastName", "(818)555-5555", "email@email.com");
        this.room = new Room("King", 2, 101, 1, false, false, false, 999.99);
    }

    public ReviewReservation(Customer cust, Room room){
        this.cust = cust;
        this.room = room;

    }

    private JTextPane GenerateReviewReservation(){
        JTextPane reportTextPane = new JTextPane();

        reportTextPane.setText(
        "\t\t\tMatador Hotels Reservation Review for " + this.cust.getFirstName() + " " + this.cust.getLastName() + "\n" +
        "-----------------------------------------------------------------------------------------------------------------------------\n" +
        "Customer ID: " + this.cust.getCustID() +
        "\t\tConfirmation Number: " + this.cust.getConfrimNum() +
        "\t\tTransaction ID: " + this.cust.getTransID() + "\n" +
        "Phone Number: " + this.cust.getPhoneNum() +
        "\tEmail: " + this.cust.getEmail() + "\n" +
        "-----------------------------------------------------------------------------------------------------------------------------\n" +
        "Check-In Date: 10/27/23\t\t" +
        "Check-Out Date: 10/28/23\n" +
        "Bed Type: " + this.room.GetRoomType() + "\t\t" +
        "Bed Count: " + this.room.GetBedCount() + "\n" +
        "Room Number: " + this.room.GetRoomNumberString() + "\t\t" +
        "Floor Number: " + this.room.GetRoomFloorString() + "\n" +
        "Accessibility: " + this.room.GetRoomAccessibleString() + "\t\t" +
        "Smoking / Non-Smoking: " + this.room.GetRoomSmokingString() + "\n" +
        "\n\t\tPrice: $" + this.room.GetRoomPriceString()
        );

        return reportTextPane; 
    }

    public JTextPane getReviewReservation(){
        return GenerateReviewReservation();
    }

}



