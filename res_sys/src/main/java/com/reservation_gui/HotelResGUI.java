package com.reservation_gui;

import javax.swing.SwingUtilities;

public class HotelResGUI {
    public static void main(String[] args) {
         //Run program on the event dispatch thread
         SwingUtilities.invokeLater(new Runnable(){
           //running instance of our window type
            @Override
            public void run() {
                MainWindow mainWin = new MainWindow();
            }
        });        
    }
}