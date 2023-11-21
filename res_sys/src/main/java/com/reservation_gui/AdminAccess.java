package com.reservation_gui;

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
     * @return boolean for password confirmation match
     */
    public boolean confirmAccess() {
        if (checkCode.equals(accessCode)) {
            return true;
        }
        return false;
    }
}
