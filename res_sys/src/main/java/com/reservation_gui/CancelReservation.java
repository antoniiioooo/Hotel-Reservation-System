package com.reservation_gui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import java.util.Iterator;


/** 
 * CancelReservation is used for removing instances of reservations and customers associated due to cancellation decision on behalf of the customer
 * @author Antonio, Joshua
 * @version 1.2
 */
public class CancelReservation {
    private String resFileLoc;

    // empty constructor for default initialization
    public CancelReservation() {
    }

    public CancelReservation(String resFile) {
        this.resFileLoc = resFile;
    }

    
    /** canceling the reservation with Customer ID
     * @author Antonio
     * @version 1.2
     * @param custID is used to find the specific customer ID to erase within the reservation file
     */
    public void cancelReservationById(String custID) {
        try {
            // Read existing reservations from the file
            LinkedList<String> existingReservations = readReservationsFromFile();

            // Go through existing reservations to find and remove the needed canceled
            // reservation
            Iterator<String> iterator = existingReservations.iterator();
            while (iterator.hasNext()) {
                String existingRes = iterator.next();
                if (existingRes.startsWith(custID + ",")) {
                    iterator.remove();
                    break;
                }
            }

            // Update the reservation data file with the modified list
            updateResFile(existingReservations);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** will help with reading the information
     * @author Antonio
     * @version 1.2
     * @return LinkedList<String> to get the list of reservations into a container for use in the search for the reservation to cancel
     * @throws IOException
     */
    private LinkedList<String> readReservationsFromFile() throws IOException {
        LinkedList<String> reservations = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader(resFileLoc));
        String line;
        while ((line = reader.readLine()) != null) {
            reservations.add(line);
        }
        reader.close();
        return reservations;
    }

    
    /** will help with updating the file
     * @author Antonio
     * @version 1.2
     * @param reservations is the list of reservations to be put into the file to update the data file for reservations itself
     * @throws IOException
     */
    private void updateResFile(LinkedList<String> reservations) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(resFileLoc));
        for (String res : reservations) {
            writer.write(res + "\n");
        }
        writer.close();
    }

    
    /** cancel reservation within list of reservations and customers by customer ID search
     * @author Joshua
     * @version 1.2
     * @param resList is the reservations list of hotel
     * @param custList is the customer list of hotel
     * @param custID is the reservation and customer to be cancelled by customer ID search
     * @return boolean for clearance of reservation and customer cancellation
     */
    public boolean cancelByCusID(LinkedList<ReservationOptions> resList, LinkedList<Customer> custList, String custID){
        ReservationOptions removeRes = new ReservationOptions();

        for (ReservationOptions res : resList){
            if (res.getCustomer().getCustID() == custID){
                removeRes = res;
                break;
            }
        }

        if (removeRes.equals(null)){
            return false;
        }
        else {
            resList.remove(removeRes);
            for (Customer cus : custList){
                if (cus.getCustID() == custID){
                    custList.remove(cus);
                    return true;
                }
            }
            return false;
        }
    }
}
