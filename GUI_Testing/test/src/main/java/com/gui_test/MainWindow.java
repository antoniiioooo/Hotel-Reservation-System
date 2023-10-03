package com.gui_test;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


public class MainWindow {
   private JFrame mainWin;
   private JPanel headerPanel, centerPanel, footerPanel;

   public MainWindow(){
      this.initialize();
   }

   private void initialize(){
      /*creates and designes main window*/
      mainWin = new JFrame();
      this.mainWin.setTitle("Hotel Reservation System");
      this.mainWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.mainWin.setSize(800, 300);
      this.mainWin.setLocationRelativeTo(null);
            
      /*makes all assets visible on the main window*/
      /*creating header and footer panels */
      this.createHeaderFooter();
      this.custCenterPanel();
      /*creating the center panel for main page */
      this.ShowMenu();
      this.mainWin.setVisible(true);
   }    

   public void ShowMenu(){
      /*creating a menu bar and setting its color*/
      final JMenuBar menuBar = new JMenuBar();
      menuBar.setBackground(new Color(153, 153, 153));
      
      /*creating manager menu*/
      JMenu managerMenu = new JMenu("Manager");
      
      /*creating menu item*/
      JMenuItem adminAccessItem = new JMenuItem("Admin Access");
      adminAccessItem.setActionCommand("Admin Access");
      
      /*Creating an action listener and assigning it to the menu item*/
      MyActionListener menuItemListener = new MyActionListener();
      adminAccessItem.addActionListener(menuItemListener);
      //managerMenu.addActionListener(menuItemListener);

      /*adding the menu item to the menu*/
      managerMenu.add(adminAccessItem);

      /*adding the menu to the menu bar*/
      menuBar.add(managerMenu);

      /*creating and adding an empty boarder to menu bar to eliminate default boarders*/
      EmptyBorder empB = new EmptyBorder(0, 0, 0, 0);
      menuBar.setBorder(empB);

      /*adding the menu bar to the main window and making it visible*/
      this.mainWin.setJMenuBar(menuBar);
   }

   public void createHeaderFooter(){
      /*creates panel for top of page and sets it's color*/
      this.headerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
      this.headerPanel.setBackground(new Color(161, 158, 158));

      /*creates label for Hotel Information */
      JLabel headerLabel = new JLabel();
      headerLabel.setText("<html>Welcome to Matador Hotels<br>Email: MatadorHotels@matador.com<br>Phone: (555)555-5555<br>Check-In: 1:00pm<br>Check-Out: 10:00am</html>");
      headerLabel.setFont(new Font("MV Boli", Font.ITALIC, 15));
      
      /*creates image icon and attaches it to the label */
      ImageIcon matadorIcon = new ImageIcon("Matador.png");
      headerLabel.setIcon(matadorIcon);
      headerLabel.setIconTextGap(0);

      /*creates label for the hotel address */
      JLabel addressLabel = new JLabel();
      addressLabel.setText("<html><div style = 'text-align: center;'>Come Vist Us At:<br>12345 Some St<br>Northridge, CA<br>91330</div></html>");
      addressLabel.setFont(new Font("MV Boli", Font.ITALIC, 18));
      addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);

      /*over riding default boreders around header labels*/
      EmptyBorder headerBorder = new EmptyBorder(0, 0, 0, 40);
      this.headerPanel.setBorder(headerBorder);

      /*adds labels to the header panel (top of GUI) */
      this.headerPanel.add(headerLabel);
      this.headerPanel.add(addressLabel);

      /*creates panel for bottom of page and sets its color*/
      this.footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      this.footerPanel.setBackground(new Color(153, 153, 153));


      /*creates label for our "trademark" and adds it to the footer panel (bottom of GUI) */
      JLabel trademarkLabel = new JLabel();
      trademarkLabel.setText("Matador Technologies \u2122");
      trademarkLabel.setFont(new Font("MV Boli", Font.PLAIN ,10));
      trademarkLabel.setForeground(new Color( 115, 0, 0));
      this.footerPanel.add(trademarkLabel);

      /*adds panels to the main window*/
      this.mainWin.add(headerPanel, BorderLayout.NORTH);
      this.mainWin.add(footerPanel, BorderLayout.SOUTH);
   }
   
   // public void mainCenterPanel(){
   //    this.centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
   //    this.centerPanel.setBackground(new Color(161, 158, 158));

   //    MyActionListener buttonListener = new MyActionListener();
   //    custButtonListener custButtList = new custButtonListener();
      
   //    // Customer Button
   //    JButton customerButton = new JButton();
   //    customerButton.setBounds(250, 175, 500, 150);
   //    customerButton.setText("I am a Customer");
   //    customerButton.setFocusable(false);
   //    customerButton.addActionListener(custButtList);
   //    //customerButton.setBackground(new Color(153, 153, 153));

   //    // Manager Button
   //    JButton managerButton = new JButton();
   //    managerButton.setBounds(250, 400, 500, 150);
   //    managerButton.setText("I am a Manager");
   //    managerButton.setFocusable(false);
   //    managerButton.addActionListener(buttonListener);
   //    //managerButton.setBackground(new Color(153, 153, 153));

   //    this.centerPanel.add(managerButton);
   //    this.centerPanel.add(customerButton);
      
   //    this.mainWin.add(centerPanel, BorderLayout.CENTER);
      
   // }

   // public void hideMainCenterPanel(){
   //    this.centerPanel.setVisible(false);
   // }

   public void custCenterPanel(){
      /*creates panel for center of page and sets its color*/
      this.centerPanel = new JPanel(new GridLayout(1, 4, 10, 10));
      this.centerPanel.setBackground(new Color(161, 158, 158)); 
      
      /*creates button for listing hotel rooms to reserve*/
      JButton roomsButton = new JButton("Reserve a Room");

      /*creates button for reviewing the reservation*/
      JButton reviewResButton = new JButton("Review Your Reservation");

      /*creates button for changing a reservation*/
      JButton  changeResButton = new JButton("Change Your Reservation");

      /*creates button for canceling reservation*/
      JButton cancelResButton = new JButton("Cancel Your Reservation");
      
      /*creating an action listener and assinging it to all buttons*/
      MyActionListener buttonListener = new MyActionListener();
      roomsButton.addActionListener(buttonListener);
      reviewResButton.addActionListener(buttonListener);
      changeResButton.addActionListener(buttonListener);
      cancelResButton.addActionListener(buttonListener);

      /*setting button colors*/
      roomsButton.setBackground(new Color(153, 153, 153));
      reviewResButton.setBackground(new Color(153, 153, 153));
      changeResButton.setBackground(new Color(153, 153, 153));
      cancelResButton.setBackground(new Color(153, 153, 153));

      /*taking focus away from buttons that are not being interacted with*/
      roomsButton.setFocusable(false);
      reviewResButton.setFocusable(false);
      changeResButton.setFocusable(false);
      cancelResButton.setFocusable(false);

      /*adding buttons to the center panel (center of GUI)*/
      this.centerPanel.add(roomsButton);
      this.centerPanel.add(reviewResButton);
      this.centerPanel.add(changeResButton);
      this.centerPanel.add(cancelResButton);

      /*over riding default boreders around buttons*/
      EmptyBorder centerBorder = new EmptyBorder(10, 10, 10, 10);   
      this.centerPanel.setBorder(centerBorder);

      /*adds panels to the main window*/
      this.mainWin.add(centerPanel, BorderLayout.CENTER);
   }
}


