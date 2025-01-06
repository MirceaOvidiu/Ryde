/** Department model class
 * @author Dodi Mircea Ovidiu
 * @version 6 jan 2025
 */

package com.example.ryde.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Long manager;

    @Transient
    private Long employeeCount;

    @Override
    public String toString() {
        return "{" + name +
                '}';
    }
}
