package com.reservation_gui;
 
import java.util.Scanner;

import javax.swing.JTextPane;

public class ReviewReservation {

    // instances from customer 
    // instances of the room
    /* //String customerConfirmation;
    //String customerID; 
    //String firstName;
    //String lastName;
    //String email; 
    //String custID;  */

    private Customer cust; 
    private Room room; 


   /*  public ReviewReservation(String customerConfirmation, String firstName, String lastName, String custID){
        this.customerConfirmation = customerConfirmation;
        this.firstName = firstName; 
        this.lastName = lastName; 
        this.custID = custID; 
    } */

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
        "\t\t\tMatador Hotels Receipt for " + this.cust.getFirstName() + " " + this.cust.getLastName() + "\n" +
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
        "Accessibility: " + this.room.CheckRoomAccessibleString() + "\t\t" +
        "Smoking / Non-Smoking: " + this.room.CheckRoomSmokingString() + "\n" +
        "\n\t\tPrice: $" + this.room.GetRoomPriceString()
        );

        return reportTextPane; 
    }

    public JTextPane getReviewReservation(){
        return GenerateReviewReservation();
    }

    public void getfirstName(String firstName){
        //grabbing the customers first name 
        Scanner scan = new Scanner(System.in);
        firstName = scan.nextLine();


    }
    public String getLastName(String lastName){
        //grabbing the customer last name 
        Scanner scan = new Scanner(System.in);
        lastName = scan.nextLine();
        return lastName; 

    }
    public void getConfirmationNumber(String customerConfirmation){
        Scanner scan = new Scanner(System.in);

        //waiting on the customer confirmation number 
        int confirmationNum = scan.nextInt();

        //chaninging the int to a string 
        customerConfirmation = Integer.toString(confirmationNum);

    }
    public void getCustomerID(String customerID){
        Scanner scan = new Scanner(System.in);

        //getting the gustomer ID 
        int ID = scan.nextInt();

        //changing the int to a string 
        customerID = Integer.toString(ID);

    }
    /* public void Review(){
            //going to compare the conformation number to the file number 
        Customer first = new Customer();
        Customer last = new Customer();
        Customer confirmation = new Customer(); 

        if(firstName.equals(first) && lastName.equals(last) && customerConfirmation.equals(confirmation)){
            System.out.println("Verification Successful. Receipt is loading.");
            } else {
                System.out.println("Verification failed. The information does not match our records. ");
            } */
        public actionReviewReservation(){
            return 
        }
    
 }

