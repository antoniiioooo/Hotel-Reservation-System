package com.reservation_gui;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class MainWindow{
    private JFrame mainWin;
    private JPanel centerPanel;
    private JScrollPane scrollPane;
    private Hotel hotelTest;
 
    public MainWindow(Hotel hotelChosen){
      hotelTest = hotelChosen;
      this.initialize();
    }
 
    private void initialize(){
      /* Creates and designs the main window */
      mainWin = new JFrame();
      this.mainWin.setTitle("Hotel Reservation System");

      // sets up updates to the data files at termination of the program
      mainWin.addWindowListener(new WindowAdapter()
      {
          @Override
          public void windowClosing(WindowEvent e)
          {
            // updates the data files through use of manipFile within the hotel object
            hotelTest.getManipFile().UpdateResFile(hotelTest.getReservationList());
            hotelTest.getManipFile().UpdateCusFile(hotelTest.getCustomerList());
            hotelTest.getManipFile().UpdateRoomFile(hotelTest.getRoomsList());
            e.getWindow().dispose();
          }
      });
      this.mainWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.mainWin.setSize(800, 300);
      this.mainWin.setLocationRelativeTo(null);
      
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
            centerPanel.setVisible(false);
            roomsListPanel();
         }
        });

        /* creating action listener for review reservation button */
        reviewResButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e){
            centerPanel.setVisible(false);
            idConfirmChoicePanel();
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
        this.mainWin.setSize(800, 300);
        this.mainWin.setLocationRelativeTo(null);
     }

     public void roomsListPanel(){

        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(new Color(161, 158, 158));
        
        /* creating new top panel to hold check in and check out panels */
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        top.setBackground(new Color(161, 158, 158));

        /* creating check in and check out panels to hold check in and check out date picker and label */
        JPanel checkIPanel = new JPanel();
        JPanel checkOutPanel = new JPanel();

        /* setting background colors for check in/out labels */
        checkIPanel.setBackground(new Color(161, 158, 158));
        checkOutPanel.setBackground(new Color(161, 158, 158));

        /* creating check in/out labels */
        JLabel checkInLabel = new JLabel("Check-In Date:");
        JLabel checkOutLabel = new JLabel("Check-Out Date:");
      
        /* creating date models for check in/out */
        UtilDateModel checkInModel = new UtilDateModel();
        UtilDateModel checkOutModel = new UtilDateModel();
        
        /* creating date to hold current day's date */
        Date today = new Date();

        /* creating date to hold next day's date */
        Date tomorrow = new Date();
        /* adding one day to current date to get next day's date */
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, 1);
        tomorrow = cal.getTime();

        /* adding today's date and tommorrow's date to the date model so they show up on creation */
        checkInModel.setValue(today);
        checkOutModel.setValue(tomorrow);
        

        /* creating starting properties for the calendar */
        Properties props=new Properties();
        props.put("text.today","Today");
        props.put("text.month","Month");
        props.put("text.year","Year");

        /* creating calendar objects and setting starting properties */
        JDatePanelImpl checkInDatePanel = new JDatePanelImpl(checkInModel, props);
        JDatePanelImpl checkOutDatePanel = new JDatePanelImpl(checkOutModel, props); 

        /* creating check in/out date pickers (input bars and buttons) and adding previously created calendar objects */ 
        JDatePickerImpl checkInDatePicker = new JDatePickerImpl(checkInDatePanel, new DateFormatter(){
            //creating format for date display
            private String datePatern = "MM/dd/yyyy";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatern);
         
            @Override
            public String valueToString(Object value) throws ParseException {
               if (value != null) {
                  Calendar cal = (Calendar) value;
                  return dateFormatter.format(cal.getTime());
               }
               return "";
            }
         });
        JDatePickerImpl checkOutDatePicker = new JDatePickerImpl(checkOutDatePanel, new DateFormatter(){
            //creating format for date display
            private String datePatern = "MM/dd/yyyy";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatern);

            @Override
            public String valueToString(Object value) throws ParseException {
               if (value != null) {
                  Calendar cal = (Calendar) value;
                  return dateFormatter.format(cal.getTime());
               }
               return "";
            }
         });  
         
         /* creating action listeners for the  date calendar objects so date can be saved when chosen */
         checkInDatePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               String checkInDate = Integer.toString(checkInDatePicker.getModel().getMonth() + 1) + "/" 
               + Integer.toString(checkInDatePicker.getModel().getDay()) + "/"
               + Integer.toString(checkInDatePicker.getModel().getYear()); 
 
               System.out.println(checkInDate);
            }
         });

         checkOutDatePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               String checkOutDate = Integer.toString(checkOutDatePicker.getModel().getMonth() + 1) + "/" 
               + Integer.toString(checkOutDatePicker.getModel().getDay()) + "/"
               + Integer.toString(checkOutDatePicker.getModel().getYear()); 
 
               System.out.println(checkOutDate);
            }
         });
         

        /* adding check in label and date picker to check in panel */
        checkIPanel.add(checkInLabel);
        checkIPanel.add(checkInDatePicker);

        /* adding check out label and date picker to check out panel */
        checkOutPanel.add(checkOutLabel);
        checkOutPanel.add(checkOutDatePicker);
   
        /* adding check in/out panels to top panel */
        top.add(checkIPanel);
        top.add(checkOutPanel);

        /* adding top panel to center panel */
        centerPanel.add(top, BorderLayout.NORTH);
        
         /*creating new middle panel for rooms list*/
        JPanel middle = new JPanel(new GridLayout(13, 2, 10, 10));
        middle.setBackground(new Color(161, 158, 158));
        
        for(Room room : hotelTest.getRoomsList()){
         /*creating the labels, image icons and reservation buttons for each room*/
           JPanel roomPics = new JPanel(new BorderLayout());
           roomPics.setBackground(new Color(161, 158, 158));

           JLabel label1 = new JLabel();
           label1.setHorizontalTextPosition(SwingConstants.CENTER);
           label1.setVerticalTextPosition(SwingConstants.BOTTOM);
           label1.setHorizontalAlignment(SwingConstants.CENTER);
           
           try {
            BufferedImage roomImg = null;
           switch(room.GetRoomType().toLowerCase()){
            case "king":
               roomImg = ImageIO.read(new File("King.jpg"));
               break;
            case "queen":
               roomImg = ImageIO.read(new File("Queen.jpg"));
               break;
            case "twin":
               roomImg = ImageIO.read(new File("Twin.jpg"));
               break;
            case "suite":
               roomImg = ImageIO.read(new File("Suite.jpg"));
               break;
            default:
               roomImg = ImageIO.read(new File("Matador.png"));
            }
             
           label1.setIcon(new ImageIcon(roomImg));
           label1.setText(room.GetRoomType());
           label1.setIconTextGap(0);

           }
           catch(IOException e1){
                  //TODO Auto-generated catch block
                  e1.printStackTrace();
           }
           /*creating action listener for reserve button*/
           JButton resButton = new JButton("Reserve This Room");
           resButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               scrollPane.setVisible(false);
               String checkInDate = Integer.toString(checkInDatePicker.getModel().getMonth() + 1) + "/" 
               + Integer.toString(checkInDatePicker.getModel().getDay()) + "/"
               + Integer.toString(checkInDatePicker.getModel().getYear());

               String checkOutDate = Integer.toString(checkOutDatePicker.getModel().getMonth() + 1) + "/" 
               + Integer.toString(checkOutDatePicker.getModel().getDay()) + "/"
               + Integer.toString(checkOutDatePicker.getModel().getYear());
               getCustInfoPanel(room, checkInDate,checkOutDate);
            }
           });

           resButton.setBackground(new Color(153, 153, 153));
           resButton.setFocusable(false);

           /*Overriding default boarders */
           EmptyBorder rpBorder = new EmptyBorder(0, 50, 0, 100);
           roomPics.setBorder(rpBorder);

           /*adding label and button to picture panel */
           roomPics.add(label1, BorderLayout.CENTER);
           roomPics.add(resButton, BorderLayout.SOUTH);

           /*creating the labels for the room information*/
           JPanel roomInfo = new JPanel(new GridLayout(7, 2,0,-200));
           roomInfo.setBackground(new Color(161, 158, 158));

           Font font = new Font("MV Boli", Font.PLAIN ,15);
          
           JLabel roomTypeLabel = new JLabel("Room Type: ");
           JLabel roomTypeVar = new JLabel(room.GetRoomType());
           roomTypeLabel.setFont(font);
           roomTypeVar.setFont(font);
           roomTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
           
           JLabel bedAmountLabel = new JLabel("Amount of Beds: ");
           JLabel bedAmountVar = new JLabel(room.GetBedCountString());
           bedAmountLabel.setFont(font);
           bedAmountVar.setFont(font);
           bedAmountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

           JLabel roomNumLabel = new JLabel("Room Number: ");
           JLabel roomNumVar = new JLabel(room.GetRoomNumberString());
           roomNumLabel.setFont(font);
           roomNumVar.setFont(font);
           roomNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);

           JLabel roomFloorLabel = new JLabel("Room Floor: ");
           JLabel roomFloorVar = new JLabel(room.GetRoomFloorString());
           roomFloorLabel.setFont(font);
           roomFloorVar.setFont(font);
           roomFloorLabel.setHorizontalAlignment(SwingConstants.RIGHT);

           JLabel accessibilityLabel = new JLabel("Disability Accessible: ");
           JLabel accessibilityVar = new JLabel(room.GetRoomAccessibleString());
           accessibilityLabel.setFont(font);
           accessibilityVar.setFont(font);
           accessibilityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
           
           JLabel nonSmokingLabel = new JLabel("Available Non-Smoking: ");
           JLabel nonSmokingVar = new JLabel(room.GetRoomSmokingString());
           nonSmokingLabel.setFont(font);
           nonSmokingVar.setFont(font);
           nonSmokingLabel.setHorizontalAlignment(SwingConstants.RIGHT);

           JLabel roomPriceLabel = new JLabel("Room Price: ");
           JLabel roomPriceVar = new JLabel(room.GetRoomPriceString());
           roomPriceLabel.setFont(font);
           roomPriceVar.setFont(font);
           roomPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                      
           /*adding labels to info panel */
           roomInfo.add(roomTypeLabel);
           roomInfo.add(roomTypeVar);
           roomInfo.add(bedAmountLabel);
           roomInfo.add(bedAmountVar);
           roomInfo.add(roomNumLabel);
           roomInfo.add(roomNumVar);
           roomInfo.add(roomFloorLabel);
           roomInfo.add(roomFloorVar);
           roomInfo.add(accessibilityLabel);
           roomInfo.add(accessibilityVar);
           roomInfo.add(nonSmokingLabel);
           roomInfo.add(nonSmokingVar);
           roomInfo.add(roomPriceLabel);
           roomInfo.add(roomPriceVar);

           /* adding each panel to the center panel*/
           middle.add(roomPics);
           middle.add(roomInfo);

           //this.centerPanel.add(top, BorderLayout.NORTH);
           this.centerPanel.add(middle, BorderLayout.CENTER);
         }


         JPanel bottom = new JPanel();
         bottom.setBackground(new Color(161, 158,158));
         /*creating button to go back */
         JButton backButton = new JButton("Go Back");
         backButton.setBackground(new Color(153, 153, 153));

         /*creating action listener for back button */
         backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               //centerPanel.setVisible(false);
               scrollPane.setVisible(false);
               mainCenterPanel();
            }
         });

         /* adding back button to bottom panel */
         bottom.add(backButton);
         
         /* taking automatic focus away from back button */
         backButton.setFocusable(false);


         /* adding bottom panel to center panel */
         this.centerPanel.add(bottom, BorderLayout.SOUTH);


         /* creating scroll pane and adding the center panel to the scroll pane */
        scrollPane = new JScrollPane(centerPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        
        /*over riding default boreders around the scroll pane*/
        EmptyBorder centerBorder = new EmptyBorder(0, 0, 0, 0);   
        scrollPane.setBorder(centerBorder);
         
        /*adding the scroll pane to main window frame */
        this.mainWin.add(scrollPane, BorderLayout.CENTER);
        this.mainWin.setSize(1000, 800);
        this.mainWin.setLocationRelativeTo(null);
     }

     public void getCustInfoPanel(Room room, String checkIn, String checkOut){
         /*creating new center panel for entering customer information*/
         this.centerPanel = new JPanel(new BorderLayout());
         this.centerPanel.setBackground(new Color(161, 158, 158));
        
         /* creating panels for top, middle, and bottom of customer information panel */
         JPanel top = new JPanel();
         JPanel middle = new JPanel(new GridLayout(4,1));
         JPanel bottom = new JPanel();

         /* setting background colors for top, middle, and bottom panels */
         top.setBackground(new Color(161, 158, 158));
         middle.setBackground(new Color(161,158, 158));
         bottom.setBackground(new Color( 161, 158, 158));
         
         /*creating customer info label and adding it to top panel*/
         JLabel custInfo = new JLabel("Customer Information");
         custInfo.setFont(new Font("MV Boli", Font.PLAIN, 30));
         top.add(custInfo);
         
         /* creating panels for first name, last name, phone, and email labels and input boxes */
         JPanel fNamePanel = new JPanel();
         JPanel lNamePanel = new JPanel();
         JPanel phonePanel = new JPanel();
         JPanel emailPanel = new JPanel();

         /* setting background color for each panel */
         fNamePanel.setBackground(new Color(161,158, 158));
         lNamePanel.setBackground(new Color(161,158, 158));
         phonePanel.setBackground(new Color(161,158, 158));
         emailPanel.setBackground(new Color(161,158, 158));

         /* creating first name label and textfield, adding border to label and adding label and textfield to appropiate panel */
         JLabel fNameLabel = new JLabel("First Name:");
         JTextField fNameInput = new JTextField(20);
         fNameLabel.setBorder(new EmptyBorder(0, 0, 0, 24));
         fNamePanel.add(fNameLabel);
         fNamePanel.add(fNameInput);

         /* creating last name label and textfield adding border to label and adding label and textfield to appropiate panel */
         JLabel lNameLabel = new JLabel("Last Name:");
         JTextField lNameInput = new JTextField(20);
         lNameLabel.setBorder(new EmptyBorder(0, 0, 0, 25));
         lNamePanel.add(lNameLabel);
         lNamePanel.add(lNameInput);

         /* creating phone number label and textfield adding label and textfield to appropiate panel */
         JLabel phoneLabel = new JLabel("Phone Number:");
         phonePanel.add(phoneLabel);
         try{
            /* creating text field with input mask for phone number */
            JFormattedTextField phoneInput = new JFormattedTextField(new MaskFormatter("(###) ###-####"));
            phoneInput.setColumns(20);
            phonePanel.add(phoneInput);
         
            /* creating email label and textfield adding border to label and adding label and textfield to appropiate panel */
            JLabel emailLabel = new JLabel("Email:");
            JTextField emailInput = new JTextField(20);
            emailLabel.setBorder(new EmptyBorder(0, 0, 0, 55));
            emailPanel.add(emailLabel);
            emailPanel.add(emailInput);

            /* adding each attribute panel to the middle panel */
            middle.add(fNamePanel);
            middle.add(lNamePanel);
            middle.add(phonePanel);
            middle.add(emailPanel);
         
            /* creating continue button for customer info panel */      
            JButton continueButton = new JButton("Continue");
            continueButton.setBackground(new Color(153, 153, 153));
            
            /* creating action listener for continue button */
            continueButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e){
                  Customer customer = new Customer();
                  /* sets customer fields from text input */
                  customer.setFirstName(fNameInput.getText());
                  customer.setLastName(lNameInput.getText());
                  customer.setPhoneNum(phoneInput.getText());
                  /* checking if email is valild */
                  if(emailInput.getText().contains("@") && emailInput.getText().contains(".com")){
                     customer.setEmail(emailInput.getText());
                     
                     // creation of the reservation with all information received needed for the object
                     ReservationOptions reserve = new ReservationOptions(customer, room, checkIn, checkOut);
                     // adding the reservation into the list within the hotel
                     hotelTest.addReservation(reserve);
                     hotelTest.addCustomer(customer);

                     /* hides current center panel */
                     centerPanel.setVisible(false);
                     /* creates Receipt object and populates it with customer and room information */
                     Receipts receipts = new Receipts(reserve);
                     /* passes the receipts object to the display receipt method */
                     displayReceipt(receipts);
                  }
                  else{
                     /* if email input does not have vaild characters then error message will be shown
                        and user will be allowed to try again once the "ok" button has been pressed */
                     JOptionPane.showMessageDialog(null,
                     "Error: Not a vaild email, please try again", "Error Message", 
                     JOptionPane.ERROR_MESSAGE);
                  }
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
            centerPanel.add(middle, BorderLayout.CENTER);
            centerPanel.add(bottom, BorderLayout.SOUTH);

            /*adding customer info panel to the main window, and setting window size and screen location */
            this.mainWin.add(centerPanel);
            this.mainWin.setSize(800, 400);
            this.mainWin.setLocationRelativeTo(null);  
         }catch(ParseException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
     }

     public void idConfirmChoicePanel(){
      /* creating panel for user to choose which search option they'd like to use */
      this.centerPanel = new JPanel(new BorderLayout(10, 10));
      this.centerPanel.setBackground(new Color(161, 158, 158));

      /* creating panel for Customer ID or Confirmation choice  and setting background color*/
      JPanel choicePanel = new JPanel(new GridLayout(1, 2, 50, 10));
      choicePanel.setBackground(new Color(161, 158, 158));

      /* creating label for options, setting font and alignment attributes */
      JLabel idORConfirm = new JLabel("Would you like to look up your reservation by Customer ID or Confirmation Number?");
      idORConfirm.setFont(new Font("MV Boli", Font.PLAIN, 16));
      idORConfirm.setHorizontalAlignment(SwingConstants.CENTER);
      
      /* creating buttons for options */
      JButton idButton = new JButton("Customer ID");
      JButton confirmButton = new JButton("Confirmation Number");

      /*setting button colors */
      idButton.setBackground(new Color(153, 153, 153));
      confirmButton.setBackground(new Color(153, 153, 153));

      /* taking automatic focus away from buttons */
      idButton.setFocusable(false);
      confirmButton.setFocusable(false);

      /* action listener for id button */
      idButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            /* creating label for Customer ID choice */
            JLabel idLabel = new JLabel("Customer ID:");
            /* hiding current scroll pane */
            scrollPane.setVisible(false);
            /* creates panels for getting reservation information with customer id lable passed */
            getReservationInfoPanel(idLabel);
         }
      });

      /* action listener for conifmation number button */
      confirmButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed (ActionEvent e){
            /* creating label for confirmation number choice */
            JLabel confirmLabel = new JLabel("Confirmation Number:");
            /* hiding current scroll pane */
            scrollPane.setVisible(false);
            /* creates panels for getting reservation information with confirmation lable passed */
            getReservationInfoPanel(confirmLabel);
         }
      });

      /* adding buttons to the choice panel */
      choicePanel.add(idButton);
      choicePanel.add(confirmButton);
      
      /*overriding default borders for choice panel */
      EmptyBorder choiceBorder = new EmptyBorder(10, 150, 10, 150);
      choicePanel.setBorder(choiceBorder);

      /*creating panel for back button */
      JPanel backPanel = new JPanel();
      backPanel.setBackground(new Color(161, 158,158));
      /*creating button to go back */
      JButton backButton = new JButton("Go Back");
      backButton.setBackground(new Color(153, 153, 153));

      /*creating action listener for back button */
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            scrollPane.setVisible(false);
            mainCenterPanel();
         }
      });

      /* adding back button to back panel */
      backPanel.add(backButton);

      /* taking automatic focus away from back button */
      backButton.setFocusable(false);
      
      /* adding options label and choice panel, and back panel to center panel */
      this.centerPanel.add(idORConfirm, BorderLayout.NORTH);
      this.centerPanel.add(choicePanel, BorderLayout.CENTER);
      this.centerPanel.add(backPanel, BorderLayout.SOUTH);

      /* creating scroll pane and adding the center panel to the scroll pane */
      this.scrollPane = new JScrollPane(centerPanel);
      this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
      /* over riding default boreders around the scroll pane */
      EmptyBorder scrollPaneBorder = new EmptyBorder(0, 0, 0, 0);   
      scrollPane.setBorder(scrollPaneBorder);
      
      /* adding the scroll pane to main window frame */
      this.mainWin.add(scrollPane, BorderLayout.CENTER);
      this.mainWin.setSize(800, 350);
      this.mainWin.setLocationRelativeTo(null);
     }

     public void getReservationInfoPanel(JLabel typeToVerify){
      /* creating panel for getting the reservation look up info */
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));
      
      /* creating panels for top, middle, and bottom of reservaion look up panel */
      JPanel top = new JPanel();
      JPanel middle = new JPanel(new GridLayout(3, 1));
      JPanel bottom = new JPanel();

      /* setting background colors for top, middle, and bottom panels */
      top.setBackground(new Color(161, 158, 158));
      middle.setBackground(new Color(161,158, 158));
      bottom.setBackground(new Color(161,158, 158));

      /*creating Reservation Information label, setting font attributes and adding label to top panel */
      JLabel reviewRes = new JLabel("Reservation Information");
      reviewRes.setFont(new Font("MV Boli", Font.PLAIN, 30));
      top.add(reviewRes);
      
      /* creating panels for the verify , prompt, and name labes and textfields */
      JPanel verifyPanel = new JPanel();
      JPanel promptPanel = new JPanel();
      JPanel namePanel = new JPanel();

      /* setting background colors for each panel */
      verifyPanel.setBackground(new Color(161, 158, 158));
      promptPanel.setBackground(new Color(161,158, 158));
      namePanel.setBackground(new Color(161,158, 158));

      /* creating prompt label, setting font attributes and adding to prompt panel */
      JLabel prompt = new JLabel("Enter The first and last name associated with the Reservation");
      prompt.setFont(new Font("MV Boli", Font.PLAIN, 20));
      promptPanel.add(prompt);

      /* creating textfield for id/confirmation input, adding the label and textfield to verify panel */
      try{
         /* creating text field with input mask for cust id and confirm num */
         JFormattedTextField inputToVerify = new JFormattedTextField(new MaskFormatter("AAAAAAA"));
         inputToVerify.setColumns(20);
         verifyPanel.add(typeToVerify);
         verifyPanel.add(inputToVerify);
         
         /* creating name label and textfield, adding label to appropiate panel */
         JLabel nameLabel = new JLabel("Full Name: ");
         JTextField name = new JTextField(50);
         namePanel.add(nameLabel);
         namePanel.add(name);

         /* adding each attribute panel to the middle panel in the order they will appear */
         middle.add(verifyPanel);
         middle.add(promptPanel);
         middle.add(namePanel);
         
         /* creating continue button and setting background color */
         JButton continueButton = new JButton("Continue");
         continueButton.setBackground(new Color(153, 153, 153));

         /*creating action listener for continue button */
         continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){    
               
               System.out.println(e.getActionCommand() + " was pressed");
               /* creating test customer and room objects  */
               Customer cust = new Customer("First", "Last", "(818)555-5555", "email@email.com");
               Room room = new Room("King", 2, 101, 1, false, false, false, 999.99);
               scrollPane.setVisible(false);
               reviewReservationPanel(cust, room);

               /* if both the customer id and confirmation number fields or the name field is empty */          
               /* if((custID.getText().equals("") && confirmNum.getText().equals("")) || (name.getText().equals("")) ){
                  System.out.println(e.getActionCommand() + " was pressed.\n");
               } */
               /* if there is input in the Customer ID field, but not the Confirmation number field */
               /*  else if(!custID.getText().equals("") && confirmNum.getText().equals("")){
                  System.out.println(custID.getText());
                  System.out.println(name.getText());
                  scrollPane.setVisible(false);
                  reviewReservationPanel(cust, room);
               } */
               /* if there is input in the Confirmation number field but not the Customer ID field*/
               /* else if(custID.getText().equals("") && !confirmNum.getText().equals("")){
                  System.out.println(confirmNum.getText());
                  System.out.println(name.getText());
                  scrollPane.setVisible(false);
                  reviewReservationPanel(cust, room);
               } */
               /* if there is input in both the Customer ID and Confirmation number fields
               else if(!custID.getText().equals("") && !confirmNum.getText().equals("")){} */
            }
         });
         
         
         /*creating button to go back */
         JButton backButton = new JButton("Go Back");
         backButton.setBackground(new Color(153, 153, 153));

         /*creating action listener for back button */
         backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               scrollPane.setVisible(false);
               idConfirmChoicePanel();
            }
         });

         /* taking focus away from button */
         continueButton.setFocusable(false);
         backButton.setFocusable(false);
         
         /*adding buttons to bottom panel */
         bottom.add(continueButton);
         bottom.add(backButton);

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
         this.mainWin.setSize(800, 500);
         this.mainWin.setLocationRelativeTo(null);
      }
      catch(ParseException e){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

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
            //getReservationInfoPanel();
            idConfirmChoicePanel();
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
 

