package com.example.ryde.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyUser implements Serializable {
    @Id
    private long id;
    private String iban;
    private String email;
    private String password;
    private String role;
    private String username;

    public MyUser(long id, String name, String password, String email, String iban) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.email = email;
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String findUserByUsername(String username) {
        return "SELECT * FROM \"user\" WHERE username = '" + username + "'";
    }

    public String findUserByEmail(String email) {
        return "SELECT * FROM \"user\" WHERE email = '" + email + "'";
    }
}