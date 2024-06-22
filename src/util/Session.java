package util;

import model.entities.User;

public class Session {
    private static User userA;

    public static void setUser(User user){
        Session.userA = user;
    }
    
    public static User getUser(){
        return userA;
    }
}