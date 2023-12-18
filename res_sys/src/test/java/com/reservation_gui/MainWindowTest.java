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

        @Test
        public void testGetReservationInfoPanel() 
        {
            
            Customer correctFirstName = new Customer("John", "Doe", "555-555-5555", "jd@jd.com");
            Customer wrongFirstName = new Customer("", "Doe", "555-555-5555", "jd@jd.com");
    
            assertTrue(correctFirstName.confirmCustomer());
            assertFalse(wrongFirstName.confirmCustomer());
        } 
}