package com.reservation_gui;

import static org.junit.Assert.assertEquals;
import java.util.LinkedList;

import org.junit.Test;

public class ManipFileTest {
    @Test
    public void testPopulateCusContainer() {
	    LinkedList<Customer> expectedCustomers = new LinkedList<Customer>();
        LinkedList<Customer> observedCustomers = new LinkedList<Customer>();

        ManipFile files = new ManipFile("resFile.txt","cusFile.txt", "roomFile.txt");
        files.PopulateCusContainer(observedCustomers);

        int listSize = observedCustomers.size();

       expectedCustomers.add(new Customer("John", "Doe", "555-555-5555", "jd@jd.com", "0000001", "1000001", "2000001"));
       expectedCustomers.add(new Customer("Josh", "Itchon", "(131) 313-4555", "joshuaitch@gmail.com", "1299439", "6139935", "1934198"));
       expectedCustomers.add(new Customer("Random", "Bozo", "(132) 234-6789", "idk@idc.com", "3192645", "5542461", "8706649"));
       expectedCustomers.add(new Customer("Ash", "Ketchum", "(113) 456-5677", "adajb@mdf.com", "7379020", "8501088", "9098819"));
       expectedCustomers.add(new Customer("David", "Martinez", "(888) 888-8888", "dfgdsfv@gm.com", "3086694", "4190333", "1995848"));
       expectedCustomers.add(new Customer("James", "Jones", "(821) 992-3535", "james_jones@haw.com", "2028992", "7320027", "6341477"));
       expectedCustomers.add(new Customer("Joras", "Fhada", "(323) 492-2945", "jfadas@mgdv.com", "5560336", "7197785", "9321792"));

       for(int i = 0; i < listSize; i++){
        assertEquals(expectedCustomers.get(i).getFirstName(), observedCustomers.get(i).getFirstName());
        assertEquals(expectedCustomers.get(i).getLastName(), observedCustomers.get(i).getLastName());
        assertEquals(expectedCustomers.get(i).getPhoneNum(), observedCustomers.get(i).getPhoneNum());
        assertEquals(expectedCustomers.get(i).getEmail(), observedCustomers.get(i).getEmail());
        assertEquals(expectedCustomers.get(i).getCustID(), observedCustomers.get(i).getCustID());
        assertEquals(expectedCustomers.get(i).getConfrimNum(), observedCustomers.get(i).getConfrimNum());
        assertEquals(expectedCustomers.get(i).getTransID(), observedCustomers.get(i).getTransID());
       }     
    }

    @Test
    public void testUpdateRoomFile() {
        String roomFilePath = "roomFile.txt";

        ManipFile File = new ManipFile("resFile.txt", "cusFile.txt", roomFilePath);

        Room[] expectedRooms = {
            new Room("King", 1, 101, 1, false, false, false, 749.99),
            new Room("Twin", 2, 102, 1, false, false, false, 249.99),
            new Room("Queen", 2, 103, 1, false, true, false, 499.99),
            new Room("King", 1, 104, 1, true, false, false, 749.99),
            new Room("King", 1, 201, 2, false, false, false, 749.99),
            new Room("Twin", 2, 202, 2, false, true, false, 499.99),
            new Room("Queen", 2, 203, 2, true, false, false, 499.99),
            new Room("King", 1, 204, 2, false, false, false, 749.99),
            new Room("King", 1, 301, 3, false, false, false, 749.99),
            new Room("Twin", 2, 302, 3, true, false, false, 249.99),
            new Room("Queen", 2, 303, 3, false, false, false, 499.99),
            new Room("King", 1, 304, 3, false, true, false, 749.99),
            new Room("Suite", 1, 400, 4, false, false, false, 999.99)
        };
        Room[] actualRooms = new Room[expectedRooms.length];

        /* Call the PopulateRoomContainer method to populate the actualRooms array */ 
        File.PopulateRoomContainer(actualRooms);
        
        for (int i = 0; i < expectedRooms.length; i++) { 
            assertEquals(expectedRooms[i].GetRoomType(), actualRooms[i].GetRoomType());
            assertEquals(expectedRooms[i].GetBedCountString(), actualRooms[i].GetBedCountString());
            assertEquals(expectedRooms[i].GetRoomNumberString(), actualRooms[i].GetRoomNumberString());
            assertEquals(expectedRooms[i].GetRoomFloorString(), actualRooms[i].GetRoomFloorString());
            assertEquals(expectedRooms[i].GetRoomAccessible(), actualRooms[i].GetRoomAccessible());
            assertEquals(expectedRooms[i].GetRoomSmoking(), actualRooms[i].GetRoomSmoking());
            assertEquals(expectedRooms[i].GetRoomOccupied(), actualRooms[i].GetRoomOccupied());
            assertEquals(expectedRooms[i].GetRoomPriceString(), actualRooms[i].GetRoomPriceString());
        }
    }
}
