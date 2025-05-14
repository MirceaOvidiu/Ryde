package com.example.ryde.isp;
import main.java.com.example.ryde.isp.User;

public class Aplication {
    public static void main(String[] args) {
        System.out.println("Hello World!");


        User user = new User();
        user.setId("1");
        user.setName("John Doe");
        user.setEmail("");
        user.setPass("password123");
        user.afisare();

    }

}
