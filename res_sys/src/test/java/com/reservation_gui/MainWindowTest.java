package com.reservation_gui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import javax.swing.JLabel;

import org.junit.Test;

public class MainWindowTest {
    @Test
    public void testAdminAccessPanel() {
        AdminAccess correctPassWord = new AdminAccess("!@#$%^&*()");
        AdminAccess incorrectPassWord = new AdminAccess("!@#$$8@!*");

        assertTrue(correctPassWord.confirmAccess());
        assertFalse(incorrectPassWord.confirmAccess());
    }

    @Test
    public void testGetReservationInfoPanel() {    
        Customer correctFirstName = new Customer("John", "Doe", "555-555-5555", "jd@jd.com");
        Customer wrongFirstName = new Customer("", "Doe", "555-555-5555", "jd@jd.com");
        assertTrue(wrongFirstName.getFirstName().equals(""));
        assertFalse(correctFirstName.getFirstName().equals(""));

        Customer correctLastName = new Customer("John", "Doe", "555-555-5555", "jd@jd.com");
        Customer wrongLastName = new Customer("John", "","555-555-5555", "jd@jd.com");
        assertTrue(wrongLastName.getLastName().equals(""));
        assertFalse(correctLastName.getLastName().equals(""));

        JLabel verifyConf = new JLabel("Confirmation Number:");
        JLabel verifyCust = new JLabel("Customer ID:");

        assertTrue(verifyConf.getText().equals("Confirmation Number:"));
        assertFalse(verifyConf.getText().equals("Not Confirmation Number:"));

        assertTrue(verifyCust.getText().equals("Customer ID:"));
        assertFalse(verifyCust.getText().equals("Not Customer ID:"));

        LinkedList<ReservationOptions> resList = new LinkedList<ReservationOptions>();
        Customer correctCustomerID = new Customer("John", "Doe", "555-555-5555", "jd@jd.com", "0000001", "1000001", "2000001");
        Customer wrongCustomerID = new Customer("John", "Doe", "555-555-5555", "jd@jd.com", "0000002", "1000001", "2000001");
        ReservationOptions correctRes = new ReservationOptions(correctCustomerID, null, "11/11/2011", "11/13/2011");
        ReservationOptions wrongRes = new ReservationOptions(wrongCustomerID, null, "11/12/2011", "11/13/2011");
        resList.add(correctRes);

        assertTrue(resList.contains(correctRes));
        assertFalse(resList.contains(wrongRes));

        LinkedList<ReservationOptions> reserveList = new LinkedList<ReservationOptions>();
        Customer correctConfirmationNumber = new Customer("John", "Doe", "555-555-5555", "jd@jd.com", "0000001", "1000001", "2000001");
        Customer wrongConfirmationNumber = new Customer("John", "Doe", "555-555-5555", "jd@jd.com", "0000001", "1000002", "2000001");
        ReservationOptions correctReserve = new ReservationOptions(correctConfirmationNumber, null, "11/11/2011", "11/13/2011");
        ReservationOptions wrongReserve = new ReservationOptions(wrongConfirmationNumber, null, "11/12/2011", "11/13/2011");
        reserveList.add(correctReserve);
        
        assertTrue(reserveList.contains(correctReserve));
        assertFalse(reserveList.contains(wrongReserve));
    } 
}