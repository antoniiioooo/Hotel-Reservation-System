package com.reservation_gui;

/** 
 * AdminAccess is used for password checking within the MainWindow GUI in order to access the ManagerReport
 * object
 * @author Joshua
 * @version 1.1
 */
public class AdminAccess {
    
    // the default code in order to access the ManagerReport
    private String accessCode = "!@#$%^&*()";
    private String checkCode;

    // constructors
    public AdminAccess(String confirm){
        checkCode = confirm;
    }

    /** 
     * checks to see if the code passed to the object matches the one established 
     * @author Joshua
     * @return boolean for password confirmation match
     * @version 1.1
     */
    public boolean confirmAccess() {
        // confirms a boolean check for the password input
        if (checkCode.equals(accessCode)) {
            return true;
        }
        return false;
    }
}
