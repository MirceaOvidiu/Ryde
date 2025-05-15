/** Manager entity class.
 * @author Dodi Mircea Ovidiu
 * @version 5 jan 2025
 */

package com.example.ryde.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "management_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manager implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String department;
    private Double salary;
}
