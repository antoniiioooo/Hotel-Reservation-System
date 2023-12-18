package com.reservation_gui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MainWindowTest {
    @Test
    public void testAdminAccessPanel() {
        AdminAccess correctPassWord = new AdminAccess("!@#$%^&*()");
        AdminAccess incorrectPassWord = new AdminAccess("!@#$$8@!*");

        assertTrue(correctPassWord.confirmAccess());
        assertFalse(incorrectPassWord.confirmAccess());
    }

    public void testGetReservationInfoPanel() {
        // Correct inputs
        ReviewReservation correctInputs = new ReviewReservation("David, Martinez, 3086694, 4190333");
        assertTrue(correctInputs.isValidInput());
    
        // Incorrect first name
        ReviewReservation incorrectFirstName = new ReviewReservation("", "Martinez", "3086694", "4190333");
        assertFalse(incorrectFirstName.isValidInput());
    
        // Incorrect last name
        ReviewReservation incorrectLastName = new ReviewReservation("David", "", "3086694", "4190333");
        assertFalse(incorrectLastName.isValidInput());
    
        // Incorrect customer ID
        ReviewReservation incorrectCustomerID = new ReviewReservation("David", "Martinez", "", "4190333");
        assertFalse(incorrectCustomerID.isValidInput());
    
        // Incorrect confirmation number
        ReviewReservation incorrectConfirmationNumber = new ReviewReservation("David", "Martinez", "12345", "");
        assertFalse(incorrectConfirmationNumber.isValidInput());
    }
}