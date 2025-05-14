package main.java.com.example.ryde.isp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String id;
    private String name;
    private String email;
    private String pass;

    public void afisare() {
        System.out.println("User ID: " + id);
        System.out.println("User Name: " + name);
        System.out.println("User Email: " + email);

    }

}
