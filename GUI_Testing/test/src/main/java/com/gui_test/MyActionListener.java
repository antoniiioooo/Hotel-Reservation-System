package com.gui_test;
import java.awt.event.*;
//import javax.swing.*;


class MyActionListener implements ActionListener{

   @Override
   public void actionPerformed(ActionEvent event){ 
      System.out.println(event.getActionCommand() + " was pressed.");

   }
}

// class custButtonListener implements ActionListener{
//    @Override
//    public void actionPerformed(ActionEvent event){
//       MainWindow custWin = new MainWindow();
//       custWin.hideMainCenterPanel();
//       custWin.custCenterPanel();
//    }
// }