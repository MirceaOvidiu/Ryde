/** user entity class.
 * @author Dodi Mircea Ovidiu
 * @version 20 nov 2024
 */

package com.example.ryde.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
        return "{" +
                "username='" + username + '\'' +
                " | email='" + email + '\'' +
                '}';
    }
}