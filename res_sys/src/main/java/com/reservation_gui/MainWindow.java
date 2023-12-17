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
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Currently creates and handles all GUI aspects for the system
 * @author Nexaly Orellana
 * @author Joshua Itchon
 * @version 1.1
 */

public class MainWindow{
    private JFrame mainWin;
    private JPanel centerPanel;
    private JScrollPane scrollPane;
    private Hotel hotelTest;
 
    public MainWindow(Hotel hotelChosen){
      hotelTest = hotelChosen;
      this.initialize();
    }
    
    /**
     * @author Nexaly Orellana
     * Creates and designs the GUI's main window on start up
     */
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
      this.mainWin.setSize(825, 300);
      this.mainWin.setLocationRelativeTo(null);
      
      /*creating header and footer panels */
      this.createHeaderFooter();

      /*creating the center panel for main page */
      this.mainCenterPanel();

      /*makes all assets visible on the main window*/
      this.mainWin.setVisible(true);

    }

    /**
     * @author Nexaly Orellana
     * Creates header and footer panels that will be displayed on every page of our GUI
     * header displays important Hotel Information
     * footer displays name of development team "Matador Technologies"
     */
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

     /**
      * @author Nexaly Orellana
      * creates center panel for homepage of our GUI
      * has buttons for all different options available for the System
      */
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
            reserveRoomPanel();
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
        
        /* creating action listener for the admin access button */
        adminButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            centerPanel.setVisible(false);
            adminAccessPanel();
         }
        });

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
        this.mainWin.setSize(825, 300);
        this.mainWin.setLocationRelativeTo(null);
     }

     /**
      * @author Nexaly Orellana
      *  creates center panel for displaying our list of rooms
      * allows users to choose a room to reserve and displays date pickers
      */
     public void reserveRoomPanel(){
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));     
      
      /* creating panels for top, middle, and bottom of the center panel */
      JPanel top = new JPanel(new BorderLayout());
      JPanel middle = new JPanel();
      JPanel bottom = new JPanel();

      /* setting background colors for top, middle and bottom */
      top.setBackground(new Color(161, 158, 158));
      middle.setBackground(new Color(161, 158, 158));
      bottom.setBackground(new Color(161, 158, 158));

      /* creating panels for calander, filter and button attributes */
      JPanel calanderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 10));
      JPanel filterPanel = new JPanel(new GridLayout(2,2, 0, 0));
      JPanel buttonPanel = new JPanel();

      /* setting background colors for calander, filter, and button panels */
      calanderPanel.setBackground(new Color(161, 158, 158));
      filterPanel.setBackground(new Color(161, 158, 158));
      buttonPanel.setBackground(new Color(161, 158, 158));

      /*creating checkin and checkout panels */
      JPanel checkInPanel = new JPanel();
      JPanel checkOutPanel = new JPanel();

      /* setting background colors for checkin and checkout panels */
      checkInPanel.setBackground(new Color(161, 158, 158));
      checkOutPanel.setBackground(new Color(161, 158, 158));

      /* creating check in/out labels */
      JLabel checkInLabel = new JLabel("Check-In Date:");
      JLabel checkOutLabel = new JLabel("Check-Out Date:");

      /* creating date models for check in/out */
      UtilDateModel checkInModel = new UtilDateModel();
      UtilDateModel checkOutModel = new UtilDateModel();

      /* creating dates to hold current day and next days date */
      Date today = new Date();
      Date tomorrow = new Date();

      /*adding one day to current date to get next day's date */
      Calendar cal = Calendar.getInstance();
      cal.setTime(today);
      cal.add(Calendar.DATE, 1);
      tomorrow = cal.getTime();

      /* adding today and tomorrow dates to date model to show up on creation */
      checkInModel.setValue(today);
      checkOutModel.setValue(tomorrow);

      /* creating starting properties for the calendar */
      Properties props = new Properties();
      props.put("text.today", "Today");
      props.put("text.month", "Month");
      props.put("text.year", "Year");

      /* creating date picker panel objects and setting starting properties */
      JDatePanelImpl checkInDatePanel = new JDatePanelImpl(checkInModel, props);
      JDatePanelImpl checkOutDatePanel = new JDatePanelImpl(checkOutModel, props);

      /* creating format for date display*/
      String datePattern = "MM/dd/yyyy";
      SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
      
      /* creating date picker objects for check in/out dates */
      JDatePickerImpl checkInDatePicker = new JDatePickerImpl(checkInDatePanel, new DateFormatter(){
         @Override
         public String valueToString(Object value) throws ParseException{
            if(value != null){
               Calendar cal = (Calendar) value;
               return dateFormatter.format(cal.getTime());
            }
            return "";
         }
      });
      JDatePickerImpl checkOutDatePicker = new JDatePickerImpl(checkOutDatePanel, new DateFormatter(){
         @Override
         public String valueToString(Object value) throws ParseException{
            if(value != null){
               Calendar cal = (Calendar) value;
               return dateFormatter.format(cal.getTime());
            }
            return "";
         }
      });

      /* adding check in label and date picker to check in panel */
      checkInPanel.add(checkInLabel);
      checkInPanel.add(checkInDatePicker);

      /* adding check out label and date picker to check out panel */
      checkOutPanel.add(checkOutLabel);
      checkOutPanel.add(checkOutDatePicker);

      /* adding the check in and check out panels to the calendar panel */
      calanderPanel.add(checkInPanel);
      calanderPanel.add(checkOutPanel);

      /* creating panels for room, price, accessability, and smoking filters */
      JPanel roomFilterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
      JPanel priceFilterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
      JPanel accessFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 10));
      JPanel smokingFilterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 10));

      /* setting background colors for filter panels */
      roomFilterPanel.setBackground(new Color(161, 158, 158));
      priceFilterPanel.setBackground(new Color(161, 158, 158));
      accessFilterPanel.setBackground(new Color(161, 158, 158));
      smokingFilterPanel.setBackground(new Color(161, 158, 158));

      /* creating search paramater for combo boxes */
      String roomTypes [] = {"", "Suite", "King", "Queen", "Twin"};
      String prices[] = {"", "$999.99", "$749.99", "499.99", "249.99"};

      /* creating combo boxes for room and price filters */
      JComboBox<String> roomChoices = new JComboBox<String>(roomTypes);
      JComboBox<String> priceChoices = new JComboBox<String>(prices);

      /* setting combo boxes to empty on start */
      roomChoices.setSelectedIndex(-1);
      priceChoices.setSelectedIndex(-1);

      /* creating labels for room and price combo boxes */
      JLabel typeLabel = new JLabel("Room Type: ");
      JLabel priceLabel = new JLabel("Price: ");

      /* adding combo boxes and labels to appropriate panels */
      roomFilterPanel.add(typeLabel);
      roomFilterPanel.add(roomChoices);
      priceFilterPanel.add(priceLabel);
      priceFilterPanel.add(priceChoices);

      /* overriding default boarders on price filter panel */
      roomFilterPanel.setBorder(new EmptyBorder(0,20,0,0));
      priceFilterPanel.setBorder(new EmptyBorder(0,45,0,0));
      
      /* creating label and check box for accessability filter */
      JLabel accessLabel = new JLabel("Accessability: ");
      JCheckBox accessability = new JCheckBox();
      
      /* creating label and check box for smoking filter */
      JLabel smokingLabel =  new JLabel("Smoking: ");
      JCheckBox smoking = new JCheckBox();

      /* setting background colors for check boxes */
      accessability.setBackground(new Color(161, 158, 158));
      smoking.setBackground(new Color(161, 158, 158));

      /* adding labels and check boxes to appropriate panels */
      accessFilterPanel.add(accessLabel);
      accessFilterPanel.add(accessability);
      smokingFilterPanel.add(smokingLabel);
      smokingFilterPanel.add(smoking);

      /* aligning checkbox panels */
      accessFilterPanel.setBorder(new EmptyBorder(0, 45, 0, 0));
      smokingFilterPanel.setBorder(new EmptyBorder(0, 70, 0, 0));

      /* adding different filter panels to main filter panel */
      filterPanel.add(roomFilterPanel);
      filterPanel.add(accessFilterPanel);
      filterPanel.add(priceFilterPanel);
      filterPanel.add(smokingFilterPanel);

      filterPanel.setBorder(new EmptyBorder(0,0,0,0));

      /* creating search button and setting background color */
      JButton searchButton = new JButton("Search");
      searchButton.setBackground(new Color(153, 153, 153));

      searchButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            /* creating linked list for available rooms */
            LinkedList<Room> availableRooms = new LinkedList<Room>();
            availableRooms.clear();
            LocalDate checkIn = checkInModel.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate checkOut = checkOutModel.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            boolean searchType = false, searchPrice = false, searchAccess = false, searchSmoke = false;

            /* checking if user is trying to search by room type  */
            if(roomChoices.getSelectedItem() != null){
               if(roomChoices.getSelectedItem() != ""){
                  searchType = true;
               }
            }

            /* checking if user is trying to search by price */
            if(priceChoices.getSelectedItem() != null){
               if(priceChoices.getSelectedItem() != ""){
                  searchPrice = true;
               }
            }

            /* checking if user is trying to search by accessability */
            if(accessability.isSelected()){
               searchAccess = true;
            }

            /* checking if user is trying to search by smoking/non-smoking */
            if(smoking.isSelected()){
               searchSmoke = true;
            }

            /* looping through all the rooms to check if each room has the search parameters */
            for(Room room : hotelTest.getRoomsList()){
               boolean addRoom = false;
               
               /* checking if user is searching by room type*/
               if(searchType){
                  /* cheaking if the current room matches the chosen room type */
                  if(room.GetRoomType().toLowerCase().equals(String.valueOf(roomChoices.getSelectedItem()).toLowerCase())){
                     addRoom = true;
                  }
               }

               /* checking if user is searching by room price */
               if(searchPrice){
                  /* checking if the user is also searching by room type */
                  if(searchType){
                     /* checking if room has passed previous search checks */
                     if(addRoom){
                        /*checking if the room does not match the chosen price */
                        if(!room.GetRoomPriceString().toLowerCase().equals(String.valueOf(priceChoices.getSelectedItem()).toLowerCase())){
                           addRoom = false;
                        }
                     }
                  }
                  /* user is only searching by price */ 
                  else{
                     /* checking if room price matches chosen price */
                     if(room.GetRoomPriceString().toLowerCase().equals(String.valueOf(priceChoices.getSelectedItem()).toLowerCase())){
                        addRoom = true;
                     }
                  }
               }

               /*checking if user is searching by accessability */
               if(searchAccess){
                  /* checking if user is also seraching by either of the previous search parameters */
                  if(searchType || searchPrice){
                     /* checking if room has passed previous search checks  */
                     if(addRoom){
                        /* checking if room is not accessible */
                        if(!room.GetRoomAccessible()){
                           addRoom = false;
                        }
                     }
                  }
                  /* user is only searching by accessibility */
                  else{
                     /* cheking if room is accessible */
                     if(room.GetRoomAccessible()){
                        addRoom = true;
                     }
                  }
               }

               /* checking if user is searching by smoking/non-smoking */
               if(searchSmoke){
                  /* checking if user is also seraching by any of the previous search parameters */
                  if(searchType || searchPrice || searchAccess){
                     /* checking if room has passed previous search checks */
                     if(addRoom){
                        /* checking if room is non-smoking */
                        if(!room.GetRoomSmoking()){
                           addRoom = false;
                        }
                     }
                  }
                  /* user is only searching by smoking/non-smoking */
                  else{
                     /* checking if room is a smoking room */
                     if(room.GetRoomSmoking()){
                        addRoom = true;
                     }
                  }
               }
               
               
               boolean dateConflict = false;
               /* loops through all reservations to see if there is a date conflict with the current room*/
               for(ReservationOptions res : hotelTest.getReservationList()){
                  /* checks if current room matches current reservation */
                  if(room.GetRoomNumberString().equals(res.getRoomChosen().GetRoomNumberString())){
                     /* if reservation check in and check out are the same as the chosen check in and check out */
                     if(res.getCheckInDate().isEqual(checkIn) && res.getCheckOutDate().isEqual(checkOut)){
                        dateConflict = true;
                     /* if reservation check in is the same as chosen check in but check out dates are different */
                     }else if(res.getCheckInDate().isEqual(checkIn) && !res.getCheckOutDate().isEqual(checkOut)){
                        dateConflict = true;
                     /* if reservation check in is different from chosen check in but check out dates are the same */
                     }else if(!res.getCheckInDate().isEqual(checkIn) && res.getCheckOutDate().isEqual(checkOut)){
                        dateConflict = true;
                     /* if chosen check in is between the current reservations check in and check out */
                     }else if(checkIn.isAfter(res.getCheckInDate()) && checkIn.isBefore(res.getCheckOutDate())){
                        dateConflict = true;
                     /* if chosen check out is between the current reservations check in and check out */
                     }else if (checkOut.isAfter(res.getCheckInDate()) && checkOut.isBefore(res.getCheckOutDate())){
                        dateConflict = true;
                     }
                  }
               }

               /* checks if any other search filters were used */
               if(searchType || searchPrice || searchAccess || searchSmoke){
                  /* if room currently matches other search filters */
                  if(addRoom){
                     /* if there is a date conflict */
                     if(dateConflict){
                        addRoom  = false;
                     }
                  }
               /* no other search filters were used */
               }else{
                  /* if no date conflict */
                  if(!dateConflict){
                     addRoom = true;
                  /* if there is a date conflict */
                  }else{
                     addRoom = false;
                  }
               }

               /* if chosen check in is after chosen check out, no rooms should be available */
               if(checkIn.isAfter(checkOut)){
                  addRoom = false;
               }

               /* checking if the room should be added to available rooms list */
               if(addRoom){
                  availableRooms.add(room);
               }

            }
            

            /* checking if any rooms are available */
            if(!availableRooms.isEmpty()){
               
               /* removes previous rooms list from the middle panel */
               middle.removeAll();
               /* adds new rooms list to the middle panel */
               middle.add(RoomsListPanel(availableRooms, checkIn, checkOut));
               
               /* adds updated middle panel to the center panel */
               centerPanel.add(middle, BorderLayout.CENTER);
               
               /* revalidates mainWin so rooms list shows properly */
               mainWin.revalidate();
               

            }
            /* there are no available rooms */
            else{
               JOptionPane.showMessageDialog(null, "No rooms matched these search parameters, please try again", 
               "Invalid Search", JOptionPane.ERROR_MESSAGE);
               scrollPane.setVisible(false);
               reserveRoomPanel();
               mainWin.revalidate();
            }
         }

      });

      buttonPanel.add(searchButton);
      
      /*adding calander, filter, and button panels to top panel */
      top.add(calanderPanel, BorderLayout.NORTH);
      top.add(filterPanel, BorderLayout.CENTER);
      top.add(buttonPanel, BorderLayout.SOUTH);

      top.setBorder(new EmptyBorder(0,0,0,0));

      /* converting today and tomorrow date objects into local date objects*/
      LocalDate localToday = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      LocalDate localTomorrow = tomorrow.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      
      /* making rooms list into linked list */
      LinkedList<Room> availableRooms = new LinkedList<Room>();
     /* searching for rooms that are available for check in today and check out tommorow */
      for(Room room : hotelTest.getRoomsList()){
         boolean addRoom = true;
         for(ReservationOptions res : hotelTest.getReservationList()){
            /* checking if the room matches the current reservation being checked */
            if(room.GetRoomNumberString().equals(res.getRoomChosen().GetRoomNumberString())){
               /* if check in and check out for current reservation is today and tomorrow  */
               if(res.getCheckInDate().isEqual(localToday) && res.getCheckOutDate().isEqual(localTomorrow)){
                  addRoom = false;
               /* if current reservation has check in equal to today but check out date not tomorrow */   
               }else if(res.getCheckInDate().isEqual(localToday) && !res.getCheckOutDate().isEqual(localTomorrow)){
                  addRoom = false;
               /* if current reservation has check in not today, but check out is tomorrow */
               }else if(!res.getCheckInDate().isEqual(localToday) && res.getCheckOutDate().isEqual(localTomorrow)){
                  addRoom = false;
               /* if today is in between the current reservations check in and check out dates  */
               }else if(localToday.isAfter(res.getCheckInDate()) && localToday.isBefore(res.getCheckOutDate())){
                  addRoom = false;
               /* if tomorrow is in between the current reservations check in and check out dates */
               }else if (localTomorrow.isAfter(res.getCheckInDate()) && localTomorrow.isBefore(res.getCheckOutDate())){
                  addRoom = false;
               }
            }
         }
         if(addRoom){
            availableRooms.add(room);
         }
      }

      /* showing all rooms in the middle panel */
      middle.add(RoomsListPanel(availableRooms, localToday, localTomorrow));

      /* creating button to go back */
      JButton backButton = new JButton("Go Back");
      backButton.setBackground(new Color(153, 153, 153));

      /* creating action listener for back button */
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            scrollPane.setVisible(false);
            mainCenterPanel();
         }
      });

      /* adding back button to bottom panel */
      bottom.add(backButton);

      /* adding top, middle and bottom panels to center panel */
      this.centerPanel.add(top, BorderLayout.NORTH);
      this.centerPanel.add(middle, BorderLayout.CENTER);
      this.centerPanel.add(bottom, BorderLayout.SOUTH);

      this.centerPanel.setBorder(new EmptyBorder(0,0,0,-150));

      /* creating scroll pane and adding the center panel to the scroll pane */
      this.scrollPane = new JScrollPane(centerPanel);
      this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      this.scrollPane.getVerticalScrollBar().setUnitIncrement(12);
      
      /* overriding default borders around the scrollpane */
      this.scrollPane.setBorder(new EmptyBorder(0, 0,0,0));

      /* adding the scroll pane to main window frame */
      this.mainWin.add(scrollPane, BorderLayout.CENTER);
      this.mainWin.setSize(900,800);
      this.mainWin.setLocationRelativeTo(null);

     }

     
     /** 
      * @author Nexaly Orellana
      * creates center panel for obtaining user input for customer information
      * @param room to associate the customer to a room they want to reserve
      * @param checkIn  to associate the customer and room to a reservation check in day
      * @param checkOut to associate the customer and room to a reservation check out day
      */
     public void getCustInfoPanel(Room room, LocalDate checkIn, LocalDate checkOut){
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
                     centerPanel.setVisible(false);
                     PaymentInfoPanel(customer, room ,checkIn, checkOut);
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
                  reserveRoomPanel();
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
            this.mainWin.setSize(825, 400);
            this.mainWin.setLocationRelativeTo(null);  
         }catch(ParseException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
     }

     /**
      * @author Nexaly Orellana
      * creates center panel for user to choose if they want to review their reservations
      * based on their customer ID or Confirmation Number
      * displays a promt and 3 buttons
      */
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
      this.mainWin.setSize(825, 350);
      this.mainWin.setLocationRelativeTo(null);
     }

     /**
      * @author Nexaly Orellana
      * creates a center panel to obtain user input to review reservation, ensures those inputs are valid
      * @param typeToVerify allows program to know which search option (Confirmation Number or Customer ID) was chosen
      */
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
         JLabel firstNameLabel = new JLabel("First Name: ");
         JLabel lastNameLabel = new JLabel("Last Name: ");
         JTextField firstName = new JTextField(25);
         JTextField lastName = new JTextField(25);
         namePanel.add(firstNameLabel);
         namePanel.add(firstName);
         namePanel.add(lastNameLabel);
         namePanel.add(lastName);

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
               // checks if there is missing input for the first and last name
               if (firstName.getText().equals("") || lastName.getText().equals("")){
                  JOptionPane.showMessageDialog(null,
                  "Error: Please Enter a First and Last Name", "Error Message", 
                  JOptionPane.ERROR_MESSAGE);
               }
               else if (typeToVerify.getText().equals("Confirmation Number:")){
                  // verifies for confirmation number search, retrieves the reservation based on provided info, passes reservation to create panel in GUI
                  ReservationOptions res = hotelTest.getReservation(firstName.getText(), lastName.getText(), inputToVerify.getText(), "Confirmation Number");

                  if (res.getCustomer() == null || res.getRoomChosen() == null || res.getCheckInString() == null || res.getCheckOutString() == null) {
                     JOptionPane.showMessageDialog(null,
                     "Error: Reservation was not found, please try again", "Error Message", 
                     JOptionPane.ERROR_MESSAGE);
                  }
                  else {
                     scrollPane.setVisible(false);
                     reviewReservationPanel(res);
                  }
               }
               else if (typeToVerify.getText().equals("Customer ID:")){
                  // verifies for id number search, retrieves the reservation based on provided info, passes reservation to create panel in GUI
                  ReservationOptions res = hotelTest.getReservation(firstName.getText(), lastName.getText(), inputToVerify.getText(), "Customer ID");
                  
                  if (res.getCustomer() == null || res.getRoomChosen() == null || res.getCheckInString() == null || res.getCheckOutString() == null) {
                     JOptionPane.showMessageDialog(null,
                     "Error: Reservation was not found, please try again", "Error Message", 
                     JOptionPane.ERROR_MESSAGE);
                  }
                  else {
                     scrollPane.setVisible(false);
                     reviewReservationPanel(res);
                  }
               }
               else {
                  // error message for any potential misinputs
                  JOptionPane.showMessageDialog(null,
                  "Error: Please Enter the Information Correctly", "Error Message", 
                  JOptionPane.ERROR_MESSAGE);
               }

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
         this.mainWin.setSize(825, 500);
         this.mainWin.setLocationRelativeTo(null);
      }
      catch(ParseException e){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

     }

     /**
      * @author Nexaly Orellana
      * Creating panel to display full reservation report
      * @param reservation to store the reservation information
      */
     public void reviewReservationPanel(ReservationOptions reservation){   
      /* creating panel for getting the reservation look up info */
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));

      /* creating top, middle and bottom panels */
      JPanel top = new JPanel();
      JPanel bottom = new JPanel();
      /* creating report and placing it in middle panel */
      JPanel middle = reservationReport(reservation);

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
      JButton changeButton = new JButton("Change Reservation Info");
      changeButton.setBackground(new Color(153, 153, 153));
        /* action listener for change button */
        changeButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            scrollPane.setVisible(false);
            editInformationPanel(reservation.getCustomer(), reservation.getPayment());      
         }
       });

   

      /* adding back button to bottom panel */
      bottom.add(backButton);
      bottom.add(changeButton);

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
      this.mainWin.setSize(825, 550);
      this.mainWin.setLocationRelativeTo(null);
     }

     /**
      * @author Nexaly Orellana
      * creating panel and adding textPane with populated report to send back to the reviewReservation panel
      * @param res to store the reservation information
      * @return JPanel to display the report on a textpane 
      */
     public JPanel reservationReport(ReservationOptions res){
      /* creating panel for report and setting background color*/
      JPanel reportPanel = new JPanel();
      reportPanel.setBackground(new Color(161, 158, 158));

      /* creating textPane for review report and setting backgroung color */
      ReviewReservation R = new ReviewReservation(res);
      JTextPane textPane = R.getReviewReservation();
     
      textPane.setBackground(new Color(161, 158, 158)); 
      
      /* setting font for text pane */
      textPane.setFont(new Font("MV Boli", Font.PLAIN, 12));

      /* adding text pane to report panel */
      reportPanel.add(textPane);

      return reportPanel;
     }
   
     /**
      * @author Nexaly Orellana
      * Creating panel to display the receipt report and gives users options to download or print the report
      * @param receipt to store the infomation needed to generate the receipt
      */
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
      receiptTextPane.setCaretPosition(0);
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
            reserveRoomPanel();
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
      this.mainWin.setSize(825, 550);
      this.mainWin.setLocationRelativeTo(null);
     }

     /**
      * @author Joshua Itchon
      * creates center panel to obtain password input and validate password
      */
   public void adminAccessPanel() {
      /*creating new center panel for entering admin access password*/
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));
        
      /* creating panels for top, middle, and bottom of admin access panel */
      JPanel top = new JPanel();
      JPanel middle = new JPanel();
      JPanel bottom = new JPanel();

      /* setting background colors for top, middle, and bottom panels */
      top.setBackground(new Color(161, 158, 158));
      middle.setBackground(new Color(161,158, 158));
      bottom.setBackground(new Color( 161, 158, 158));
      
      /*creating admin access label and adding it to top panel*/
      JLabel adminLabel = new JLabel("Admin Access");
      adminLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
      top.add(adminLabel);

      /* creating the label and text field for entering admin password */
      JLabel passwordLabel = new JLabel("Password:  ");
      JPasswordField passwordField = new JPasswordField(25);
      middle.add(passwordLabel);
      middle.add(passwordField);

      /*creating buttons and setting background color */
      JButton continueButton = new JButton("Continue");
      JButton backButton = new JButton("Go Back");
      continueButton.setBackground(new Color(153, 153, 153));
      backButton.setBackground(new Color(153, 153, 153));

      /*creating action listener for continue button */
      continueButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            AdminAccess check = new AdminAccess(String.valueOf(passwordField.getPassword()));

            /* if the entered password is correct, it will allow access to the Manager Report menu */
            if (check.confirmAccess()) {
               centerPanel.setVisible(false);
               managerReportsPanel();
            }
            /* when the password entered is incorrect */
            else {
               JOptionPane.showMessageDialog(null,
               "Error: Incorrect Password, please try again", "Error Message", 
               JOptionPane.ERROR_MESSAGE);
            }
         }
      });

      /*creating action listener for back button */
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            centerPanel.setVisible(false);
            mainCenterPanel();
         }
      });

      /* adding the buttons to the bottom panel */
      bottom.add(continueButton);
      bottom.add(backButton);

      /* adding top, middle and bottom panels to the center panel */
      this.centerPanel.add(top, BorderLayout.NORTH);
      this.centerPanel.add(middle, BorderLayout.CENTER);
      this.centerPanel.add(bottom, BorderLayout.SOUTH);

      /* displaying the center panel along with resizing the window */
      this.mainWin.add(centerPanel);
      this.mainWin.setSize(825, 350);
      this.mainWin.setLocationRelativeTo(null);
   }

   /**
    * @author Joshua Itchon
    * creating center panel to display manager options
    * displays 4 buttons
    */
   public void managerReportsPanel() {
      /* instatiation of ManagerReport object for use of displaying and downloading or printing reports */
      ManagerReport reports = new ManagerReport(hotelTest.getReservationList(), hotelTest.getCustomerList(), hotelTest.getRoomsList());

      /*creates panel for center of page and sets its color*/
      this.centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
      this.centerPanel.setBackground(new Color(161, 158, 158)); 
        
      /*creates button for the report for hotel reservations*/
      JButton resReportButton = new JButton("Reservations Report");
  
      /*creates button for report of the hotel customers*/
      JButton cusReportButton = new JButton("Customer Report");
  
      /*creates button for the report of the hotel rooms*/
      JButton roomReportButton = new JButton("Room Report");

      /* creates a back button to go back to the main menu */
      JButton backButton = new JButton("Go Back");

      /* action listener for the back button */
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            centerPanel.setVisible(false);
            mainCenterPanel();
         }
      });

      /*creating an action listener for reservation report button */
      resReportButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            centerPanel.setVisible(false);
            displayResReport(reports);
         }
      });

      /* creating action listener for customer report button */
      cusReportButton.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            centerPanel.setVisible(false);
            displayCusReport(reports);
         }
        });
        
      /* creating action listener for the room report button */
      roomReportButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            centerPanel.setVisible(false);
            displayRoomReport(reports);
         }
        });

      /*setting button colors*/
      backButton.setBackground(new Color(153,153,153));
      resReportButton.setBackground(new Color(153, 153, 153));
      cusReportButton.setBackground(new Color(153, 153, 153));
      roomReportButton.setBackground(new Color(153, 153, 153));
  
      /*taking focus away from buttons that are not being interacted with*/
      backButton.setFocusable(false);
      resReportButton.setFocusable(false);
      cusReportButton.setFocusable(false);
      roomReportButton.setFocusable(false);
  
      /*adding buttons to the center panel (center of GUI)*/
      this.centerPanel.add(resReportButton);
      this.centerPanel.add(cusReportButton);
      this.centerPanel.add(roomReportButton);
      this.centerPanel.add(backButton);
  
      /*over riding default boreders around buttons*/
      EmptyBorder centerBorder = new EmptyBorder(10, 10, 10, 10);   
      this.centerPanel.setBorder(centerBorder);
  
      /*adds panels to the main window*/
      this.mainWin.add(centerPanel, BorderLayout.CENTER);
      this.mainWin.setSize(825, 600);
      this.mainWin.setLocationRelativeTo(null);
   }

   /**
    * @author Joshua Itchon
    * creates center panel to generate and display report of all reservations in the system
    * @param reports to store the information for the report
    */
   public void displayResReport(ManagerReport reports) {
      /* creating panel for displaying the reservation report */
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));

      /* creating top, middle and bottom panels */
      JPanel top = new JPanel();
      JPanel bottom = new JPanel();
      JPanel middle = new JPanel();

      /* retrieves the report and adds it to the middle panel for display */
      JTextPane resRep = reports.getReservationList();
      resRep.setCaretPosition(0);
      middle.add(resRep);

      /* setting background colors for top and bottom panels */
      top.setBackground(new Color(161, 158, 158));
      middle.setBackground(new Color(161, 158, 158));
      bottom.setBackground(new Color(161, 158, 158));

      /* creating header label, setting font attributes and adding it to top panel */
      JLabel resRepLabel = new JLabel("Reservations Report");
      resRepLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
      top.add(resRepLabel);

      /*creating button to go back */
      JButton backButton = new JButton("Go Back");
      backButton.setBackground(new Color(153, 153, 153));

      /*creating action listener for back button */
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            scrollPane.setVisible(false);
            managerReportsPanel();
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
      scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        
      /* over riding default boreders around the scroll pane */
      EmptyBorder scrollPaneBorder = new EmptyBorder(0, 0, 0, 0);   
      scrollPane.setBorder(scrollPaneBorder);
         
      /* adding the scroll pane to main window frame */
      this.mainWin.add(scrollPane);
      this.mainWin.setSize(825, 750);
      this.mainWin.setLocationRelativeTo(null);
   }

   /**
    * @author Joshua Itchon
    * creates center panel to generate and display report of all customers in the system
    * @param reports to store the information for the report
    */
   public void displayCusReport(ManagerReport reports) {
      /* creating panel for customer report display */
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));

      /* creating top, middle and bottom panels */
      JPanel top = new JPanel();
      JPanel bottom = new JPanel();
      JPanel middle = new JPanel();

      /* retrieves the customer report and add to the middle panel */
      JTextPane cusRep = reports.getCustomerList();
      cusRep.setCaretPosition(0);
      middle.add(cusRep);

      /* setting background colors for top and bottom panels */
      top.setBackground(new Color(161, 158, 158));
      middle.setBackground(new Color(161, 158, 158));
      bottom.setBackground(new Color(161, 158, 158));

      /* creating header label, setting font attributes and adding it to top panel */
      JLabel cusRepLabel = new JLabel("Customer Report");
      cusRepLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
      top.add(cusRepLabel);

      /*creating button to go back */
      JButton backButton = new JButton("Go Back");
      backButton.setBackground(new Color(153, 153, 153));

      /*creating action listener for back button */
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            scrollPane.setVisible(false);
            managerReportsPanel();
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
      scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        
      /* over riding default boreders around the scroll pane */
      EmptyBorder scrollPaneBorder = new EmptyBorder(0, 0, 0, 0);   
      scrollPane.setBorder(scrollPaneBorder);
         
      /* adding the scroll pane to main window frame */
      this.mainWin.add(scrollPane);
      this.mainWin.setSize(825, 750);
      this.mainWin.setLocationRelativeTo(null);
   }

   /**
    * @author Joshua Itchon
    * creates center panel to generate and display report of all rooms in the system
    * @param reports to store the information for the report
    */
   public void displayRoomReport(ManagerReport reports) {
      /* creating panel for getting the room report */
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));

      /* creating top, middle and bottom panels */
      JPanel top = new JPanel();
      JPanel bottom = new JPanel();
      JPanel middle = new JPanel();

      /* retrieves the room report and adds to the middle panel */
      JTextPane roomRep = reports.getRoomList();
      roomRep.setCaretPosition(0);
      middle.add(roomRep);

      /* setting background colors for top and bottom panels */
      top.setBackground(new Color(161, 158, 158));
      middle.setBackground(new Color(161, 158, 158));
      bottom.setBackground(new Color(161, 158, 158));

      /* creating header label, setting font attributes and adding it to top panel */
      JLabel roomRepLabel = new JLabel("Room Report");
      roomRepLabel.setFont(new Font("MV Boli", Font.PLAIN, 30));
      top.add(roomRepLabel);

      /*creating button to go back */
      JButton backButton = new JButton("Go Back");
      backButton.setBackground(new Color(153, 153, 153));

      /*creating action listener for back button */
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            scrollPane.setVisible(false);
            managerReportsPanel();
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
      scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        
      /* over riding default boreders around the scroll pane */
      EmptyBorder scrollPaneBorder = new EmptyBorder(0, 0, 0, 0);   
      scrollPane.setBorder(scrollPaneBorder);
         
      /* adding the scroll pane to main window frame */
      this.mainWin.add(scrollPane);
      this.mainWin.setSize(825, 750);
      this.mainWin.setLocationRelativeTo(null);
   }
   

   
   /** 
    * @author Nexaly Orellana
    * takes in a list of rooms, a check in date, and a check out date and creates panels for each room's info to be displayed
    * when a button is pressed to reserve a room, it passes the check in date and check out date to the getCustInfoPanel method
    * @param roomsList
    * @param checkIn
    * @param checkOut
    * @return JPanel
    */
   public JPanel RoomsListPanel(LinkedList<Room> roomsList, LocalDate checkIn, LocalDate checkOut){
      JPanel roomsListPanel = new JPanel(new GridLayout(roomsList.size(), 2, 10, 10));
      roomsListPanel.setBackground(new Color(161, 158, 158));

      /* looping through the rooms list */
      for(Room room : roomsList){
         /* creating labels, image icons, and reservation buttons for each room */
         JPanel roomPicPanel = new JPanel(new BorderLayout());
         roomPicPanel.setBackground(new Color(161, 158, 158));

         JLabel roomTypeLabel = new JLabel();
         roomTypeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
         roomTypeLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
         roomTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);

         try{
            BufferedImage roomImg = null;
            switch (room.GetRoomType().toLowerCase()) {
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
                  break;
            }

            roomTypeLabel.setIcon(new ImageIcon(roomImg));
            roomTypeLabel.setText(room.GetRoomType());
            roomTypeLabel.setIconTextGap(0);
         }catch(IOException e){
            //TODO Auto-generated catch block
            e.printStackTrace();
         }

         /* creating action listener for reserve button */
         JButton resButton = new JButton("Reserve This Room");
         resButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
               scrollPane.setVisible(false);
               getCustInfoPanel(room, checkIn, checkOut);
            }
         });

         /* setting background color for button and taking away auto focus */
         resButton.setBackground((new Color(153, 153, 153)));
         resButton.setFocusable(false);

         /* overriding default borders for roomPic panel */
         roomPicPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

         /* adding label and button to picture panel */
         roomPicPanel.add(roomTypeLabel, BorderLayout.CENTER);
         roomPicPanel.add(resButton, BorderLayout.SOUTH);

         /* creating panel for room information and setting background color */
         JPanel roomInfoPanel = new JPanel(new GridLayout(7, 2, 0, -200));
         roomInfoPanel.setBackground(new Color(161, 158, 158));

         /* creating font attribute for each label */
         Font font = new Font("MV Boli", Font.PLAIN ,15);

         /* creating labels for room information */
         JLabel TypeLabel = new JLabel("Room Type: ");
         JLabel roomTypeVar = new JLabel(room.GetRoomType());
         TypeLabel.setFont(font);
         roomTypeVar.setFont(font);
         TypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
           
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

         /* adding labels to info panel */
         roomInfoPanel.add(TypeLabel);
         roomInfoPanel.add(roomTypeVar);
         roomInfoPanel.add(bedAmountLabel);
         roomInfoPanel.add(bedAmountVar);
         roomInfoPanel.add(roomNumLabel);
         roomInfoPanel.add(roomNumVar);
         roomInfoPanel.add(roomFloorLabel);
         roomInfoPanel.add(roomFloorVar);
         roomInfoPanel.add(accessibilityLabel);
         roomInfoPanel.add(accessibilityVar);
         roomInfoPanel.add(nonSmokingLabel);
         roomInfoPanel.add(nonSmokingVar);
         roomInfoPanel.add(roomPriceLabel);
         roomInfoPanel.add(roomPriceVar);

         /* adding roomPicPanel and roomInfoPanel to roomsListPanel */
         roomsListPanel.add(roomPicPanel);
         roomsListPanel.add(roomInfoPanel);
      }
      return roomsListPanel;
   }
   public void PaymentInfoPanel(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut){
      /*creating new center panel for entering customer payment information*/
      this.centerPanel = new JPanel(new BorderLayout());
      this.centerPanel.setBackground(new Color(161, 158, 158));
     
      /* creating panels for top, middle, and bottom of customer payment information panel */
      JPanel top = new JPanel();
      JPanel middle = new JPanel(new GridLayout(4,1));
      JPanel bottom = new JPanel();

      /* setting background colors for top, middle, and bottom panels */
      top.setBackground(new Color(161, 158, 158));
      middle.setBackground(new Color(161,158, 158));
      bottom.setBackground(new Color( 161, 158, 158));
      
      /*creating customer payment info label and adding it to top panel*/
      JLabel paymentInfo = new JLabel("Customer Payment Information");
      paymentInfo.setFont(new Font("MV Boli", Font.PLAIN, 30));
      top.add(paymentInfo);
      
      /* creating panels for card holder name, card number, expiration date, and cvv labels and input boxes */
      JPanel cardHolderNamePanel = new JPanel();
      JPanel cardNumberPanel = new JPanel();
      JPanel expirationDatePanel = new JPanel();
      JPanel cvvNumberPanel = new JPanel();

      /* setting background color for each panel */
      cardHolderNamePanel.setBackground(new Color(161,158, 158));
      cardNumberPanel.setBackground(new Color(161,158, 158));
      expirationDatePanel.setBackground(new Color(161,158, 158));
      cvvNumberPanel.setBackground(new Color(161,158, 158));

      /* creating card holder name label and textfield, adding border to label and adding label and textfield to appropiate panel */
      JLabel cardHolderNameLabel = new JLabel("Name on Card: ");
      JTextField cardHolderNameInput = new JTextField(20);
      cardHolderNameLabel.setBorder(new EmptyBorder(0, 0, 0, 17));
      cardHolderNamePanel.add(cardHolderNameLabel);
      cardHolderNamePanel.add(cardHolderNameInput);

      /* creating card number label and textfield adding border to label and adding label and textfield to appropiate panel */
      JLabel cardNumberLabel = new JLabel("Card Number:");
      cardNumberLabel.setBorder(new EmptyBorder(0, 0, 0, 25));
      cardNumberPanel.add(cardNumberLabel);

      /* creating expiration date label and textfield adding label and textfield to appropiate panel */
      JLabel expirationDateLabel = new JLabel("Expiration Date:");
      expirationDateLabel.setBorder(new EmptyBorder(0, 0,0,15));
      expirationDatePanel.add(expirationDateLabel);
      try{
         JFormattedTextField cardNumberInput = new JFormattedTextField(new MaskFormatter("####-####-####-####"));
         cardNumberInput.setColumns(20);
         cardNumberPanel.add(cardNumberInput);

         /* creating text field with input mask for expiration date */
         JFormattedTextField expirationDateInput = new JFormattedTextField(new MaskFormatter("##/##"));
         expirationDateInput.setColumns(20);
         expirationDatePanel.add(expirationDateInput);

         /* creating CVV number label and textfield adding border to label and adding label and textfield to appropiate panel */
         JLabel cvvNumberLabel = new JLabel("CVV Number:");
         JFormattedTextField cvvNumberInput = new JFormattedTextField(new MaskFormatter("###"));
         cvvNumberInput.setColumns(20);
         cvvNumberLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
         cvvNumberPanel.add(cvvNumberLabel);
         cvvNumberPanel.add(cvvNumberInput);

         /* adding each attribute panel to the middle panel */
         middle.add(cardHolderNamePanel);
         middle.add(cardNumberPanel);
         middle.add(expirationDatePanel);
         middle.add(cvvNumberPanel);
      
         /* creating continue button for payment info panel */      
         JButton continueButton = new JButton("Continue");
         continueButton.setBackground(new Color(153, 153, 153));
         
         /* creating action listener for continue button */
         continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               Payment payment = new Payment();
               /* sets payment fields from text input */
               payment.setCardHolderName(cardHolderNameInput.getText());
               payment.setCardNumber(cardNumberInput.getText());
               payment.setCvvNumber(cvvNumberInput.getText());
               
               /* date validation */
               DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yy");
               YearMonth expDate = YearMonth.parse(expirationDateInput.getText(), format);
               boolean expValid = expDate.isAfter(YearMonth.now());

               /* checking if expiration date is valid */
               if(expValid){
                  /* setting expiration date */
                  payment.setExpirationDate(expirationDateInput.getText());
                  customer.setPaymentInfo(payment);                   // creation of the reservation with all information received needed for the object
                  ReservationOptions reserve = new ReservationOptions(customer, room, checkIn, checkOut, payment);
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
               else 
             {
                  /* if expiration date is not valid then error message will be shown
                     and user will be allowed to try again once the "ok" button has been pressed */
                  JOptionPane.showMessageDialog(null,
                  "Error: Not a vaild expiration date, please try again", "Error Message", 
                  JOptionPane.ERROR_MESSAGE);
               }
            
         }});
         
         /*creating button to go back */
         JButton backButton = new JButton("Go Back");
         backButton.setBackground(new Color(153, 153, 153));

         /*creating action listener for back button */
         backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               centerPanel.setVisible(false);
               reserveRoomPanel();
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
         this.mainWin.setSize(825, 400);
         this.mainWin.setLocationRelativeTo(null);  
      }catch(ParseException e){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
  }
  
   /* panel for editing reservation of the customer */
   public void editInformationPanel(Customer customer, Payment payment){
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
      JPanel cardHolderNamePanel = new JPanel();
      JPanel cardNumberPanel = new JPanel();
      JPanel expirationDatePanel = new JPanel();
      JPanel cvvNumberPanel = new JPanel();

      /* setting background color for each panel */
      fNamePanel.setBackground(new Color(161,158, 158));
      lNamePanel.setBackground(new Color(161,158, 158));
      phonePanel.setBackground(new Color(161,158, 158));
      emailPanel.setBackground(new Color(161,158, 158));
      cardHolderNamePanel.setBackground(new Color(161,158, 158));
      cardNumberPanel.setBackground(new Color(161,158, 158));
      expirationDatePanel.setBackground(new Color(161,158, 158));
      cvvNumberPanel.setBackground(new Color(161,158, 158));

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
      
      
      /* creating text field and label for card holder name, card number, expiration date, and cvv */
      JLabel cardHolderNameLabel = new JLabel("Card Holder Name:");
      JTextField cardHolderNameInput = new JTextField(20);
      cardHolderNameInput.setText(payment.getCardHolderName()); 
      cardHolderNamePanel.add(cardHolderNameLabel);
      cardHolderNamePanel.add(cardHolderNameInput);

      JLabel cardNumberLabel = new JLabel("Card Number:");
      JTextField cardNumberInput = new JTextField(20);
      cardNumberInput.setText(payment.getCardNumber()); 
      cardNumberPanel.add(cardNumberLabel);
      cardNumberPanel.add(cardNumberInput);

      JLabel expirationDateLabel = new JLabel("Expiration Date:");
      JFormattedTextField expirationDateInput = new JFormattedTextField(new MaskFormatter("##/##"));
      expirationDateInput.setText(payment.getExpirationDate()); 
      expirationDatePanel.add(expirationDateLabel);
      expirationDatePanel.add(expirationDateInput);

      JLabel cvvNumberLabel = new JLabel("CVV:");
      JFormattedTextField cvvNumberInput = new JFormattedTextField(new MaskFormatter("###"));
      cvvNumberInput.setText(payment.getCvvNumber()); 
      cvvNumberPanel.add(cvvNumberLabel);
      cvvNumberPanel.add(cvvNumberInput);
         /* adding each attribute panel to the middle panel */
         middle.add(fNamePanel);
         middle.add(lNamePanel);
         middle.add(phonePanel);
         middle.add(emailPanel);
         middle.add(cardHolderNamePanel);
         middle.add(cardNumberPanel);
         middle.add(expirationDatePanel);
         middle.add(cvvNumberPanel);

      
         /* creating continue button for customer info panel */      
         JButton saveButton = new JButton("Save");
         saveButton.setBackground(new Color(153, 153, 153));
         
         /* creating action listener for continue button */
         saveButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent E){
               /* checking if email is valild */
               if(emailInput.getText().contains("@") && emailInput.getText().contains(".com")){
                  ChangeReservation changeCustomer = new ChangeReservation(customer, payment);
               /* sets customer fields from text input */
                  changeCustomer.updateCustomerInfo(fNameInput.getText(), lNameInput.getText(), phoneInput.getText(), emailInput.getText());
                  
                  // creation of the reservation with all information received needed for the object
                  changeCustomer.updatePaymentInfo(cardHolderNameInput.getText(), cardNumberInput.getText(), expirationDateInput.getText(), cvvNumberInput.getText());
                 

                  /* hides current center panel */
                  JOptionPane.showMessageDialog(null,"Your Information Has Been Updated.", "Update Confirmation", JOptionPane.ERROR_MESSAGE);
                  centerPanel.setVisible(false);
                  mainCenterPanel();

               }
               else{
                  /* if email input does not have vaild characters then error message will be shown
                     and user will be allowed to try again once the "ok" button has been pressed */
                  JOptionPane.showMessageDialog(null,
                  "Error: Not a vaild email, please try again", "Error Message", 
                  JOptionPane.ERROR_MESSAGE);
               }
         }});
               bottom.add(saveButton);

      }catch(ParseException e){
         e.printStackTrace();
      }/* catch(IOException e){
         e.printStackTrace();
      } */
   

       
      /*adding panels to customer info panel */
      centerPanel.add(top, BorderLayout.NORTH);
      centerPanel.add(middle, BorderLayout.CENTER);
      centerPanel.add(bottom, BorderLayout.SOUTH);

      /*adding customer info panel to the main window, and setting window size and screen location */
      this.mainWin.add(centerPanel);
      this.mainWin.setSize(825, 400);
      this.mainWin.setLocationRelativeTo(null);  
   }


}

