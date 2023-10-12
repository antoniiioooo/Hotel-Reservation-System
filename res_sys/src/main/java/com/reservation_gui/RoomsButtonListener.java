package com.reservation_gui;
import java.awt.event.*;

class RoomsButtonListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent event){
       MainWindow RoomsWin = new MainWindow();
       RoomsWin.hideCenterPanel();
       RoomsWin.roomsListPannel();
    } 
   
   }