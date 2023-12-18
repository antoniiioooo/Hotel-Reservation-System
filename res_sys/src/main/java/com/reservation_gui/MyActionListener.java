package com.reservation_gui;
import java.awt.event.*;

class MyActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent event){ 
       System.out.println(event.getActionCommand() + " was pressed.");
    }
 }