package com.reservation_gui;
import javax.swing.JTextPane;

public class ReviewReservation {
    private Customer cust; 
    private Room room;
    private ReservationOptions res; 

    /* constructors */
    // default that makes an empty reservation to review and pass an error flag
    public ReviewReservation(){
        this.res = new ReservationOptions();
        this.cust = res.getCustomer();
        this.room = res.getRoomChosen();
    }
    // takes a reservation to check and review
    public ReviewReservation(ReservationOptions reserve){
        this.cust = reserve.getCustomer();
        this.room = reserve.getRoomChosen();
        this.res = reserve;
    }

    private JTextPane GenerateReviewReservation(){
        JTextPane reportTextPane = new JTextPane();
        reportTextPane.setEditable(false);

        reportTextPane.setText(
        "\t\t\tMatador Hotels Reservation Review for " + this.cust.getFirstName() + " " + this.cust.getLastName() + "\n" +
        "-----------------------------------------------------------------------------------------------------------------------------\n" +
        "Customer ID: " + this.cust.getCustID() +
        "\t\tConfirmation Number: " + this.cust.getConfrimNum() +
        "\t\tTransaction ID: " + this.cust.getTransID() + "\n" +
        "Phone Number: " + this.cust.getPhoneNum() +
        "\tEmail: " + this.cust.getEmail() + "\n" +
        "-----------------------------------------------------------------------------------------------------------------------------\n" +
        "Check-In Date: " + this.res.getCheckInString() + "\t\t" +
        "Check-Out Date: " + this.res.getCheckOutString() + "\n" +
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
    // creates a text pane for GUI when an error in which a reservation cannot be found due to misinputs
    public JTextPane GenerateErrorReview(){
        JTextPane errorTextPane = new JTextPane();
        errorTextPane.setEditable(false);

        errorTextPane.setText("This reservation was not found.\nPlease re-enter the information.");

        return errorTextPane;
    }
    // calls the appropriate review message for the reservation
    public JTextPane getReviewReservation(){
        // error check when the reservation does not exist i.e no customer or room is connected to the reservation
        if (this.cust == null || this.room == null){
            return GenerateErrorReview();
        }
        else {
        return GenerateReviewReservation();
        }
    }

}



