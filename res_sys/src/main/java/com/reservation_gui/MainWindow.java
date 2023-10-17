package com.reservation_gui;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JFrame mainWin;
    private JPanel centerPanel;
    private JScrollPane scrollPane;
 
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
      this.MainCenterPanel();

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

     public void MainCenterPanel(){
        /*creates panel for center of page and sets its color*/
        this.centerPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        this.centerPanel.setBackground(new Color(161, 158, 158)); 
        
        /*creates button for listing hotel rooms to reserve*/
        JButton roomsButton = new JButton("Reserve a Room");
  
        /*creates button for reviewing the reservation*/
        JButton reviewResButton = new JButton("Review Your Reservation");
  
        /*creates button for Admin Access*/
        JButton  adminButton = new JButton("Admin Access");

        /*creating an action listener for rooms button */
        roomsButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            MainWindow resWindow = new MainWindow();
            resWindow.centerPanel.setVisible(false);
            resWindow.roomsListPanel();
         }
        });
        
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

     public void roomsListPanel(){
        /*creating new center panel for rooms list*/
        this.centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        this.centerPanel.setBackground(new Color(161, 158, 158));
        
        
        for(int i = 0; i < 4; i++){
         /*creating the labels, image icons and reservation buttons for each room*/
           JPanel roomPics = new JPanel(new BorderLayout());
           roomPics.setBackground(new Color(161, 158, 158));

           JLabel label1 = new JLabel("A picture of the room will be here");
           label1.setHorizontalTextPosition(SwingConstants.CENTER);
           label1.setVerticalTextPosition(SwingConstants.BOTTOM);
           label1.setHorizontalAlignment(SwingConstants.CENTER);
  
           ImageIcon matadorIcon = new ImageIcon("Matador.png");
           label1.setIcon(matadorIcon);
           label1.setIconTextGap(0);
           
           /*creating action listener for reserve button*/
           JButton resButton = new JButton("Reserve This Room Type");
           resButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               scrollPane.setVisible(false);
               getCustInfoPanel();
            }
           });
           resButton.setBackground(new Color(153, 153, 153));
           resButton.setFocusable(false);

           /*Overriding default boarders */
           EmptyBorder b = new EmptyBorder(0, 100, 0, 100);
           roomPics.setBorder(b);

           /*adding label and button to picture panel */
           roomPics.add(label1, BorderLayout.CENTER);
           roomPics.add(resButton, BorderLayout.SOUTH);

           /*creating the labels for the room information*/
           JPanel roomInfo = new JPanel(new GridLayout(5, 2));
           roomInfo.setBackground(new Color(161, 158, 158));

           JLabel roomTypeLabel = new JLabel("Room Type: ");
           JLabel roomTypeVar = new JLabel("Type");
           roomTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
           
           JLabel roomAmountLabel = new JLabel("Rooms of this Type: ");
           JLabel roomAmountVar = new JLabel("Number");
           roomAmountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

           JLabel availableRoomsLabel = new JLabel("Total Available Rooms: ");
           JLabel availableRoomsVar = new JLabel("Number");
           availableRoomsLabel.setHorizontalAlignment(SwingConstants.RIGHT);

           JLabel smokingLabel = new JLabel("Available Smoking Rooms: ");
           JLabel smokingVar = new JLabel("Number");
           smokingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
           
           JLabel nonSmokingLabel = new JLabel("Available Non-Smoking Rooms: ");
           JLabel nonSmokingVar = new JLabel("Number");
           nonSmokingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
           
           /*adding labels to info panel */
           roomInfo.add(roomTypeLabel);
           roomInfo.add(roomTypeVar);
           roomInfo.add(roomAmountLabel);
           roomInfo.add(roomAmountVar);
           roomInfo.add(availableRoomsLabel);
           roomInfo.add(availableRoomsVar);
           roomInfo.add(smokingLabel);
           roomInfo.add(smokingVar);
           roomInfo.add(nonSmokingLabel);
           roomInfo.add(nonSmokingVar);

           /* adding each panel to the center panel*/
           this.centerPanel.add(roomPics);
           this.centerPanel.add(roomInfo);
         }


         /* creating scroll pane and adding the center panel to the scroll pane */
        scrollPane = new JScrollPane(centerPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        /*over riding default boreders around the scroll pane*/
        EmptyBorder centerBorder = new EmptyBorder(0, 0, 0, 0);   
        scrollPane.setBorder(centerBorder);
         

        /*adding the scroll pane to main window frame */
        this.mainWin.add(scrollPane, BorderLayout.CENTER);
        this.mainWin.setSize(800, 800);
        this.mainWin.setLocationRelativeTo(null);
     }

     public void getCustInfoPanel(){
      /*creating new center panel for entering customer information*/
         this.centerPanel = new JPanel(new BorderLayout());
         this.centerPanel.setBackground(new Color(161, 158, 158));
        
         /*panel for top of the customer info panel */
         JPanel top = new JPanel();
         top.setBackground(new Color(161, 158, 158));
         /*creating customer info label */
         JLabel custInfo = new JLabel("Customer Information");
         custInfo.setFont(new Font("MV Boli", Font.PLAIN, 30));
         /*adding label to top panel */
         top.add(custInfo);
         
         /*panel for center of the customer info panel */
         JPanel center = new JPanel(new GridLayout(4,2, 10, 10));
         center.setBackground(new Color(161,158, 158));

         /*overridng default boarders */
         EmptyBorder centerBorder = new EmptyBorder(0, 0, 0, 200);
         center.setBorder(centerBorder);
         
         /*creating labels and input boxes for the customer info */
         JLabel fNameLabel = new JLabel("First Name:");
         fNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
         JTextField fNameInput = new JTextField();
         
         JLabel lNameLabel = new JLabel("Last Name:");
         lNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
         JTextField lNameInput = new JTextField();
         

         JLabel phoneLabel = new JLabel("Phone Number:");
         phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
         JTextField phoneInput = new JTextField();
         
         JLabel emailLabel = new JLabel("Email:");
         emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
         JTextField emailInput = new JTextField();

         /*adding all labels and input boxes to the center panel */
         center.add(fNameLabel);
         center.add(fNameInput);
         center.add(lNameLabel);
         center.add(lNameInput);
         center.add(phoneLabel);
         center.add(phoneInput);
         center.add(emailLabel);
         center.add(emailInput);

         /*panel for bottom of the customer info panel */
         JPanel bottom = new JPanel();

         /*creating continue button for customer info panel */
         bottom.setBackground(new Color( 161, 158, 158));
         JButton continueButton = new JButton("Continue");
         continueButton.setBackground(new Color(153, 153, 153));

         /*creating action listener for continue button */
         continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               Customer customer = new Customer();
               customer.setFirstName(fNameInput.getText());
               customer.setLastName(lNameInput.getText());
               customer.setPhoneNum(phoneInput.getText());
               customer.setEmail(emailInput.getText());
               
               System.out.println(customer.getCustID());
               System.out.println(customer.getFirstName() + " " + customer.getLastName());
               System.out.println(customer.getPhoneNum());
               System.out.println(customer.getEmail());
            }
         });

         /*creating button to go back */
         JButton backButton = new JButton("Go Back");
         backButton.setBackground(new Color(153, 153, 153));

         /*creating action listener for back button */
         backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               centerPanel.setVisible(false);
               roomsListPanel();
            }
         });

         /*taking focus away from buttons that are not being interacted with */
         continueButton.setFocusable(false);
         backButton.setFocusable(false);

         /*adding buttons to bottom panel */
         bottom.add(continueButton);
         bottom.add(backButton);

         /*adding panels to customer info panel */
         centerPanel.add(top, BorderLayout.NORTH);
         centerPanel.add(center, BorderLayout.CENTER);
         centerPanel.add(bottom, BorderLayout.SOUTH);

         /*adding customer info panel to the main window, and setting window size and screen location */
         this.mainWin.add(centerPanel);
         this.mainWin.setSize(800, 400);
         this.mainWin.setLocationRelativeTo(null);  
     }
}
 

