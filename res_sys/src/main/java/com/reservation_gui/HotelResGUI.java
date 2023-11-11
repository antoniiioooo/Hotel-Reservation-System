package com.reservation_gui;

import javax.swing.SwingUtilities;

public class HotelResGUI {
    public static void main(String[] args) {

         //Run program on the event dispatch thread
         SwingUtilities.invokeLater(new Runnable(){
           
            //running instance of our window type; creating a Hotel object for the GUI
           Hotel hotel; 

            @Override
            public void run() {
                // passing a new Hotel object to the MainWindow GUI
                hotel = new Hotel();
                MainWindow mainWin = new MainWindow(hotel);
            }
        });
        
    }
}