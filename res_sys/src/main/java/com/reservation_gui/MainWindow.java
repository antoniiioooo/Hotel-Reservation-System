package com.reservation_gui;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MainWindow {
    private JFrame mainWin;
    private JPanel centerPanel;
 
    public MainWindow(){
       this.initialize();
    }
 
    private void initialize(){
      /* Creates and designs the main window */
      mainWin = new JFrame();
      this.mainWin.setTitle("Hotel Reservation System");
      this.mainWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.mainWin.setSize(800, 300);
      this.mainWin.setLocationRelativeTo(null);
      
      /*creating header and footer panels */
      this.createHeaderFooter();

      /*creating the center panel for main page */
      this.custCenterPanel();

      /*makes all assets visible on the main window*/
      this.mainWin.setVisible(true);

    }


    public void createHeaderFooter(){
        /*creates panel for top of page and sets it's color*/
        JPanel headerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        headerPanel.setBackground(new Color(161, 158, 158));
  
        /*creates label for Hotel Information */
        JLabel headerLabel = new JLabel();
        headerLabel.setText("<html>Welcome to Matador Hotels<br>Email: MatadorHotels@matador.com<br>Phone: (555)555-5555<br>Check-In: 1:00pm<br>Check-Out: 10:00am</html>");
        headerLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        
        /*creates image icon and attaches it to the label */
        ImageIcon matadorIcon = new ImageIcon("Matador.png");
        headerLabel.setIcon(matadorIcon);
        headerLabel.setIconTextGap(0);
        
        /*creates label for the hotel address */
        JLabel addressLabel = new JLabel();
        addressLabel.setText("<html><div style = 'text-align: center;'>Come Vist Us At:<br>12345 Some St<br>Northridge, CA<br>91330</div></html>");
        addressLabel.setFont(new Font("MV Boli", Font.PLAIN, 18));
        addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
  
        /*over riding default boreders around header labels*/
        EmptyBorder headerBorder = new EmptyBorder(0, 0, 0, 40);
        headerPanel.setBorder(headerBorder);
  
        /*adds labels to the header panel (top of GUI) */
        headerPanel.add(headerLabel);
        headerPanel.add(addressLabel);
  
        /*creates panel for bottom of page and sets its color*/
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(new Color(153, 153, 153));
  
        /*creates label for our "trademark" and adds it to the footer panel (bottom of GUI) */
        JLabel trademarkLabel = new JLabel();
        trademarkLabel.setText("Matador Technologies \u2122");
        trademarkLabel.setFont(new Font("MV Boli", Font.PLAIN ,10));
        trademarkLabel.setForeground(new Color( 115, 0, 0));
        footerPanel.add(trademarkLabel);
  
        /*adds panels to the main window*/
        this.mainWin.add(headerPanel, BorderLayout.NORTH);
        this.mainWin.add(footerPanel, BorderLayout.SOUTH);
     }

     public void custCenterPanel(){
        /*creates panel for center of page and sets its color*/
        this.centerPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        this.centerPanel.setBackground(new Color(161, 158, 158)); 
        
        /*creates button for listing hotel rooms to reserve*/
        JButton roomsButton = new JButton("Reserve a Room");
  
        /*creates button for reviewing the reservation*/
        JButton reviewResButton = new JButton("Review Your Reservation");
  
        /*creates button for Admin Access*/
        JButton  adminButton = new JButton("Admin Access");

        /*creating an action listener for rooms button and assinging it to the rooms button */
        RoomsButtonListener roomButtonListener = new RoomsButtonListener();
        roomsButton.addActionListener((roomButtonListener));
        
        /*creating an generic action listener and assinging it to all other buttons*/
        MyActionListener buttonListener = new MyActionListener();
        reviewResButton.addActionListener(buttonListener);
        adminButton.addActionListener(buttonListener);
    
        /*setting button colors*/
        roomsButton.setBackground(new Color(153, 153, 153));
        reviewResButton.setBackground(new Color(153, 153, 153));
        adminButton.setBackground(new Color(153, 153, 153));
  
        /*taking focus away from buttons that are not being interacted with*/
        roomsButton.setFocusable(false);
        reviewResButton.setFocusable(false);
        adminButton.setFocusable(false);
  
        /*adding buttons to the center panel (center of GUI)*/
        this.centerPanel.add(roomsButton);
        this.centerPanel.add(reviewResButton);
        this.centerPanel.add(adminButton);
  
        /*over riding default boreders around buttons*/
        EmptyBorder centerBorder = new EmptyBorder(10, 10, 10, 10);   
        this.centerPanel.setBorder(centerBorder);
  
        /*adds panels to the main window*/
        this.mainWin.add(centerPanel, BorderLayout.CENTER);
     }

     public void hideCenterPanel(){
        this.centerPanel.setVisible(false);
     }

     public void roomsListPannel(){
        //creating new center pannel for rooms list
        this.centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        this.centerPanel.setBackground(new Color(161, 158, 158));
        
        //creating the labels and  image icons for each room
        for(int i = 0; i < 4; i++){
           JLabel label1 = new JLabel("A picture of the room will be here");
           label1.setHorizontalTextPosition(SwingConstants.CENTER);
           label1.setVerticalTextPosition(SwingConstants.BOTTOM);
           label1.setHorizontalAlignment(SwingConstants.CENTER);
  
           ImageIcon matadorIcon = new ImageIcon("Matador.png");
           label1.setIcon(matadorIcon);
           label1.setIconTextGap(0);
           
           JLabel label2 = new JLabel("a description will be here"); 
           label2.setHorizontalAlignment(SwingConstants.CENTER);
           
           //adding each lable to the center pannel
           this.centerPanel.add(label1);
           this.centerPanel.add(label2);
         }


         /* creating scroll pane and adding the center pannel to the scroll pane */
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        /*over riding default boreders around the scroll pane*/
        EmptyBorder centerBorder = new EmptyBorder(0, 0, 0, 0);   
        scrollPane.setBorder(centerBorder);
         
        /*adding the scroll pane to main window frame */
        this.mainWin.add(scrollPane, BorderLayout.CENTER);
        this.mainWin.setSize(800, 800);
        this.mainWin.setLocationRelativeTo(null);
     }

     
}
 

