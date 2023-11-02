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
    private Hotel hotelTest;
 
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

      /* create variable of Hotel type to test the functionality */
      hotelTest = new Hotel();
      
      /*creating header and footer panels */
      this.createHeaderFooter();

      /*creating the center panel for main page */
      this.mainCenterPanel();

      /*makes all assets visible on the main window*/
      this.mainWin.setVisible(true);

    }


    public void createHeaderFooter(){

        /* creates panel for top of page and sets it's color */
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(new Color(161, 158, 158));

        /* panels for left, middle and right side of header */
        JPanel leftHeaderPanel = new JPanel();
        JPanel middleHeaderPanel = new JPanel(new GridLayout(6, 1));
        JPanel rightHeaderPanel = new JPanel(new BorderLayout(0, 0));

        /* setting background colors for header panels */
        leftHeaderPanel.setBackground(new Color(161, 158, 158));
        middleHeaderPanel.setBackground(new Color(161, 158, 158));
        rightHeaderPanel.setBackground(new Color(161, 158, 158));

        /* creating label to hold the matador icon, adding matador icon to the left header panel */
        JLabel matadorIcon = new JLabel(new ImageIcon("Matador.png"));
        leftHeaderPanel.add(matadorIcon);

  
        /*creates labels for Hotel Information */
        JLabel headerLabel = new JLabel("Welcome to " + hotelTest.getHotelName());
        JLabel emailLabel = new JLabel("Email: " + hotelTest.getHotelEmail());
        JLabel phoneLabel = new JLabel("Phone: " + hotelTest.getHotelPhone());
        JLabel checkInLabel = new JLabel("Check-In: " + hotelTest.getHotelCheckIn());
        JLabel checkOutLabel = new JLabel("Check-Out: " + hotelTest.getHotelCheckOut());

        /* sets font attributes for hotel info labels */
        headerLabel.setFont(new Font("MV Boli", Font.BOLD, 14));
        emailLabel.setFont(new Font("MV Boli", Font.BOLD, 14));
        phoneLabel.setFont(new Font("MV Boli", Font.BOLD, 14));
        checkInLabel.setFont(new Font("MV Boli", Font.BOLD, 14));
        checkOutLabel.setFont(new Font("MV Boli", Font.BOLD, 14));

        /* adding hotel info labels to the middle header panel */
        middleHeaderPanel.add(headerLabel);
        middleHeaderPanel.add(emailLabel);
        middleHeaderPanel.add(phoneLabel);
        middleHeaderPanel.add(checkInLabel);
        middleHeaderPanel.add(checkOutLabel);

        /* creating hotel address labels and setting horizontal alignment */
        JLabel addressLabel = new JLabel("Come Visit Us At: ");
        addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel address = new JLabel(hotelTest.getHotelAddress());
        
        /* sets font attributes for address labels */
        addressLabel.setFont(new Font("MV Boli", Font.BOLD, 18));
        address.setFont(new Font("MV Boli", Font.BOLD , 14));

        /* adds address labels to the right header panel */
        rightHeaderPanel.add(addressLabel, BorderLayout.NORTH);
        rightHeaderPanel.add(address, BorderLayout.CENTER);

        /* overriding default boraders for middle header panel so it lays flush to the matador icon */
        EmptyBorder middleHeaderBorder = new EmptyBorder(0, 0, 0, 80);
        middleHeaderPanel.setBorder(middleHeaderBorder);
        
        /* adding left, middle and right panels to the header panel */
        headerPanel.add(leftHeaderPanel);
        headerPanel.add(middleHeaderPanel);
        headerPanel.add(rightHeaderPanel);

        /* overriding deafault boarders for header panel */
        EmptyBorder headerPanelBorder = new EmptyBorder(0, 10, 0, 10);
        headerPanel.setBorder(headerPanelBorder);
        
        /*creates panel for bottom of page and sets its color*/
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(new Color(161, 158, 158));
  
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

     public void mainCenterPanel(){
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

        /* creating action listener for review reservation button */
        reviewResButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e){
            MainWindow reviewResWindow = new MainWindow();
            reviewResWindow.centerPanel.setVisible(false);
            reviewResWindow.getReservationInfoPanel();
         }
        });
        
        /*creating an generic action listener and assinging it to all other buttons*/
        MyActionListener buttonListener = new MyActionListener();
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
      // temporary Room object for testing
      Room a = new Room("King", 2, 101, 1, false, false, false, 999.99);

        /*creating new center panel for rooms list*/
        this.centerPanel = new JPanel(new GridLayout(13, 2, 10, 10));
        this.centerPanel.setBackground(new Color(161, 158, 158));
        
        
        for(int i = 0; i < 13; i++){
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
               getCustInfoPanel(a);
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
           JPanel roomInfo = new JPanel(new GridLayout(6, 2));
           roomInfo.setBackground(new Color(161, 158, 158));

           JLabel roomTypeLabel = new JLabel("Room Type: ");
           JLabel roomTypeVar = new JLabel(a.GetRoomType());
           roomTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
           
           JLabel bedAmountLabel = new JLabel("Amount of Beds: ");
           JLabel bedAmountVar = new JLabel(a.GetBedCountString());
           bedAmountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

           JLabel roomFloorLabel = new JLabel("Room Floor: ");
           JLabel roomFloorVar = new JLabel(a.GetRoomFloorString());
           roomFloorLabel.setHorizontalAlignment(SwingConstants.RIGHT);

           JLabel accessibilityLabel = new JLabel("Disability Accessible: ");
           JLabel accessibilityVar = new JLabel(a.CheckRoomAccessibleString());
           accessibilityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
           
           JLabel nonSmokingLabel = new JLabel("Available Non-Smoking: ");
           JLabel nonSmokingVar = new JLabel(a.CheckRoomSmokingString());
           nonSmokingLabel.setHorizontalAlignment(SwingConstants.RIGHT);

           JLabel roomPriceLabel = new JLabel("Room Price: ");
           JLabel roomPriceVar = new JLabel(a.GetRoomPriceString());
           roomPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
           
           
           /*adding labels to info panel */
           roomInfo.add(roomTypeLabel);
           roomInfo.add(roomTypeVar);
           roomInfo.add(bedAmountLabel);
           roomInfo.add(bedAmountVar);
           roomInfo.add(roomFloorLabel);
           roomInfo.add(roomFloorVar);
           roomInfo.add(accessibilityLabel);
           roomInfo.add(accessibilityVar);
           roomInfo.add(nonSmokingLabel);
           roomInfo.add(nonSmokingVar);
           roomInfo.add(roomPriceLabel);
           roomInfo.add(roomPriceVar);

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

     public void getCustInfoPanel(Room room){
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
               /* sets customer fields from text input */
               customer.setFirstName(fNameInput.getText());
               customer.setLastName(lNameInput.getText());
               customer.setPhoneNum(phoneInput.getText());
               customer.setEmail(emailInput.getText());
               
               /* hides current center panel */
               centerPanel.setVisible(false);

               /* creates Receipt object and populates it with customer and room information */
               Receipts receipts = new Receipts(customer, room);
               /* passes the receipts object to the display receipt method */
               displayReceipt(receipts);
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

     public void getReservationInfoPanel(){
      /* creating panel for getting the reservation look up info */
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));
      
      /* creating panels for top, middle, and bottom of reservaion look up panel */
      JPanel top = new JPanel();
      JPanel middle = new JPanel(new GridLayout(6, 1));
      JPanel bottom = new JPanel();

      /* setting background colors for top, middle, and bottom panels */
      top.setBackground(new Color(161, 158, 158));
      middle.setBackground(new Color(161,158, 158));
      bottom.setBackground(new Color(161,158, 158));

      /*creating Reservation Information label and setting font attributes */
      JLabel reviewRes = new JLabel("Reservation Information");
      reviewRes.setFont(new Font("MV Boli", Font.PLAIN, 30));
      
      /*adding label to top panel */
      top.add(reviewRes);
      
      /* creating boarder for labels */
      EmptyBorder labelBorder = new EmptyBorder(0, 20, 0, 20);
      
      /* creating panels for 'first prompt' label, 'or' lable, 'second prompt' label, 'customer ID' 
      label and input, 'confirmation number' label and input, and 'name' label and input */
      JPanel firstPromptPanel = new JPanel();
      JPanel orPanel = new JPanel();
      JPanel secondPromptPanel = new JPanel();
      JPanel custIDPanel = new JPanel();
      JPanel confirmNumPanel = new JPanel();
      JPanel namePanel = new JPanel();

      /* setting background colors for each panel */
      firstPromptPanel.setBackground(new Color(161,158, 158));
      orPanel.setBackground(new Color(161,158, 158));
      secondPromptPanel.setBackground(new Color(161,158, 158));
      custIDPanel.setBackground(new Color(161,158, 158));
      confirmNumPanel.setBackground(new Color(161,158, 158));
      namePanel.setBackground(new Color(161,158, 158));

      /* creating first prompt label, setting font attributes and label border,
      adding label to appropiate panel */
      JLabel firstPrompt = new JLabel("Enter Customer ID or Confirmation Number");
      firstPrompt.setFont(new Font("MV Boli", Font.PLAIN, 20));
      firstPrompt.setBorder(labelBorder);
      firstPromptPanel.add(firstPrompt);

      /* creating 'or' label, setting font attributes and label border,
      adding label to appropiate panel */
      JLabel or = new JLabel("OR");
      or.setFont(new Font("MV Boli", Font.PLAIN, 20));
      or.setBorder(labelBorder);
      orPanel.add(or);

      /* creating second prompt label, setting font attributes and label border,
      adding label to appropiate panel */
      JLabel secondPrompt = new JLabel("Enter The first and last name associated with the Reservation");
      secondPrompt.setFont(new Font("MV Boli", Font.PLAIN, 20));
      secondPrompt.setBorder(labelBorder);
      secondPromptPanel.add(secondPrompt);

      /* creating customer ID label and textfield, setting font attributes,
      adding label and textfield to appropiate panel */
      JLabel custIDLabel = new JLabel("Customer ID: ");
      JTextField custID = new JTextField(25);
      custIDPanel.add(custIDLabel);
      custIDPanel.add(custID);

      /* creating confirmation number label and textfield, setting font attributes,
      adding label to appropiate panel */
      JLabel confirmNumLabel = new JLabel("Confirmation Number: ");
      JTextField confirmNum = new JTextField(20);
      confirmNumPanel.add(confirmNumLabel);
      confirmNumPanel.add(confirmNum);

      /* creating name label and textfield, setting font attributes,
      adding label to appropiate panel */
      JLabel nameLabel = new JLabel("Full Name: ");
      JTextField name = new JTextField(50);
      namePanel.add(nameLabel);
      namePanel.add(name);

      /* adding each attribute panel to the middle panel in the order they will appear */
      middle.add(firstPromptPanel);
      middle.add(custIDPanel);
      middle.add(orPanel);
      middle.add(confirmNumPanel);
      middle.add(secondPromptPanel);
      middle.add(namePanel);
      

      /* creating continue button and setting background color */
      JButton continueButton = new JButton("Continue");
      continueButton.setBackground(new Color(153, 153, 153));

      /*creating action listener for continue button */
      continueButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e){    

            /* creating test customer and room objects  */
            Customer cust = new Customer("First", "Last", "(818)555-5555", "email@email.com");
            Room room = new Room("King", 2, 101, 1, false, false, false, 999.99);
            
            /* if both the customer id and confirmation number fields or the name field is empty */          
            if((custID.getText().equals("") && confirmNum.getText().equals("")) || (name.getText().equals("")) ){
               System.out.println(e.getActionCommand() + " was pressed.\n");
            }
            /* if there is input in the Customer ID field, but not the Confirmation number field */
            else if(!custID.getText().equals("") && confirmNum.getText().equals("")){
               System.out.println(custID.getText());
               System.out.println(name.getText());
               scrollPane.setVisible(false);
               reviewReservationPanel(cust, room);
            }
            /* if there is input in the Confirmation number field but not the Customer ID field*/
            else if(custID.getText().equals("") && !confirmNum.getText().equals("")){
               System.out.println(confirmNum.getText());
               System.out.println(name.getText());
               scrollPane.setVisible(false);
               reviewReservationPanel(cust, room);
            }
            /* if there is input in both the Customer ID and Confirmation number fields
            else if(!custID.getText().equals("") && !confirmNum.getText().equals("")){} */
         }
      });

      /* taking focus away from button */
      continueButton.setFocusable(false);
      
      /*adding buttons to bottom panel */
      bottom.add(continueButton);



      /* adding panels to review reservation panel */
      centerPanel.add(top, BorderLayout.NORTH);
      centerPanel.add(middle, BorderLayout.CENTER);
      centerPanel.add(bottom, BorderLayout.SOUTH);

      /* creating scroll pane and adding the center panel to the scroll pane */
      scrollPane = new JScrollPane(centerPanel);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
      /* over riding default boreders around the scroll pane */
      EmptyBorder scrollPaneBorder = new EmptyBorder(0, 0, 0, 0);   
      scrollPane.setBorder(scrollPaneBorder);
         

      /* adding the scroll pane to main window frame */
      this.mainWin.add(scrollPane, BorderLayout.CENTER);
      this.mainWin.setSize(800, 600);
      this.mainWin.setLocationRelativeTo(null);

     }
     
     public void reviewReservationPanel(Customer cust, Room room){   
      /* creating panel for getting the reservation look up info */
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));

      /* creating top, middle and bottom panels */
      JPanel top = new JPanel();
      JPanel bottom = new JPanel();
      /* creating report and placing it in middle panel */
      JPanel middle = reservationReport(cust, room);

      /* setting background colors for top and bottom panels */
      top.setBackground(new Color( 161, 158, 158));
      bottom.setBackground(new Color( 161, 158, 158));

      /* creating header label, setting font attributes and adding it to top panel */
      JLabel reviewResLabel = new JLabel("Review Reservation");
      reviewResLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
      top.add(reviewResLabel);

      
      /*creating button to go back */
      JButton backButton = new JButton("Go Back");
      backButton.setBackground(new Color(153, 153, 153));

      /*creating action listener for back button */
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            scrollPane.setVisible(false);
            getReservationInfoPanel();
         }
      });

      /* adding back button to bottom panel */
      bottom.add(backButton);

      /* adding top, middle and bottom panels to the center panel */
      centerPanel.add(top, BorderLayout.NORTH);
      centerPanel.add(middle, BorderLayout.CENTER);
      centerPanel.add(bottom, BorderLayout.SOUTH);



      /* creating scroll pane and adding the center panel to the scroll pane */
      scrollPane = new JScrollPane(centerPanel);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        

      /* over riding default boreders around the scroll pane */
      EmptyBorder scrollPaneBorder = new EmptyBorder(0, 0, 0, 0);   
      scrollPane.setBorder(scrollPaneBorder);
         

      /* adding the scroll pane to main window frame */
      this.mainWin.add(scrollPane);
      this.mainWin.setSize(800, 550);
      this.mainWin.setLocationRelativeTo(null);
     }

     public JPanel reservationReport(Customer cust, Room room){
      /* creating panel for report and setting background color*/
      JPanel reportPanel = new JPanel();
      reportPanel.setBackground(new Color(161, 158, 158));

      /* creating textPane for review report and setting backgroung color */
      ReviewReservation R = new ReviewReservation(cust,room);
      JTextPane textPane = R.getReviewReservation(); 
     
      textPane.setBackground(new Color(161, 158, 158)); 

      /* adding review report info to text pane */
      /* textPane.setText(
        "\t\t\tMatador Hotels Receipt for " + cust.getFirstName() + " " + cust.getLastName() + "\n" +
        "-----------------------------------------------------------------------------------------------------------------------------\n" +
        "Customer ID: " + cust.getCustID() +
        "\t\tConfirmation Number: " + cust.getConfrimNum() +
        "\t\tTransaction ID: " + cust.getTransID() + "\n" +
        "Phone Number: " + cust.getPhoneNum() +
        "\tEmail: " + cust.getEmail() + "\n" +
        "-----------------------------------------------------------------------------------------------------------------------------\n" +
        "Check-In Date: 10/27/23\t\t" +
        "Check-Out Date: 10/28/23\n" +
        "Bed Type: " + room.GetRoomType() + "\t\t" +
        "Bed Count: " + room.GetBedCount() + "\n" +
        "Room Number: " + room.GetRoomNumberString() + "\t\t" +
        "Floor Number: " + room.GetRoomFloorString() + "\n" +
        "Accessibility: " + room.CheckRoomAccessibleString() + "\t\t" +
        "Smoking / Non-Smoking: " + room.CheckRoomSmokingString() + "\n" +
        "\n\t\tPrice: $" + room.GetRoomPriceString()
        ); */

        /* setting font for text pane */
        textPane.setFont(new Font("MV Boli", Font.PLAIN, 12));

        /* adding text pane to report panel */
        reportPanel.add(textPane);

      
      return reportPanel;
     }
     
     public void displayReceipt(Receipts receipt){
      /* creating panel for displaying reciept */
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));

      /* creating top, middle and bottom panels */
      JPanel top = new JPanel();
      JPanel middle = new JPanel();
      JPanel bottom = new JPanel();

      /* setting background colors for top and bottom panels */
      top.setBackground(new Color( 161, 158, 158));
      middle.setBackground(new Color( 161, 158, 158));
      bottom.setBackground(new Color( 161, 158, 158));

      /* creating header label, setting font attributes and adding it to top panel */
      JLabel receiptLabel = new JLabel("Review Receipt: ");
      receiptLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
      top.add(receiptLabel);

      /* creating text pane for reciept setting backgroud color, and adding it to middle panel */
      JTextPane receiptTextPane = receipt.GetReceipt();
      receiptTextPane.setBackground(new Color( 161, 158, 158));
      receiptTextPane.setFont(new Font("MV Boli", Font.PLAIN, 12));
      middle.add(receiptTextPane);

      /*creating buttons and setting background color */
      JButton backButton = new JButton("Go Back");
      JButton downloadAndPrint = new JButton("Download or Print Receipt");
      backButton.setBackground(new Color(153, 153, 153));
      downloadAndPrint.setBackground(new Color(153, 153, 153));

      /*creating action listener for back button */
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            scrollPane.setVisible(false);
            roomsListPanel();
         }
      });

      /*creating action listener for download and print button */
      downloadAndPrint.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            receipt.downloadOrPrintReceipt();
         }
      });

      /* adding buttons to bottom panel */
      bottom.add(backButton);
      bottom.add(downloadAndPrint);

      /* adding top, middle and bottom panels to the center panel */
      centerPanel.add(top, BorderLayout.NORTH);
      centerPanel.add(middle, BorderLayout.CENTER);
      centerPanel.add(bottom, BorderLayout.SOUTH);



      /* creating scroll pane and adding the center panel to the scroll pane */
      scrollPane = new JScrollPane(centerPanel);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        

      /* over riding default boreders around the scroll pane */
      EmptyBorder scrollPaneBorder = new EmptyBorder(0, 0, 0, 0);   
      scrollPane.setBorder(scrollPaneBorder);
         

      /* adding the scroll pane to main window frame */
      this.mainWin.add(scrollPane);
      this.mainWin.setSize(800, 550);
      this.mainWin.setLocationRelativeTo(null);
     }

}
 

