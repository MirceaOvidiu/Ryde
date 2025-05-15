package com.example.ryde.isp;
import main.java.com.example.ryde.isp.User;
import com.example.ryde.isp.Meniu;

public class Aplication {
    public static void main(String[] args) {

//        // create one user
//        User user = new User();
//        user.setId("1");
//        user.setName("John Doe");
//        user.setEmail("");
//        user.setPass("password123");
//        user.afisare();
//
//        // create 10 bickes
//        Bicicleta[] biciclete = new Bicicleta[10];
//        for (int i = 0; i < biciclete.length; i++) {
//            biciclete[i] = new Bicicleta();
//            biciclete[i].setId(String.valueOf(i + 1));
//            // randomize location
//            int randomLocation = (int) (Math.random() * Statii.values().length);
//            biciclete[i].setLocation(Statii.values()[randomLocation].getStationName());
//            biciclete[i].setBatteryStatus("100%");
//            biciclete[i].setAvailable(true);
//            biciclete[i].setUser(user);
//        }

        // create menu
        Meniu meniu = new Meniu();

        // show menu
        meniu.afisareMeniu();

    }

}
