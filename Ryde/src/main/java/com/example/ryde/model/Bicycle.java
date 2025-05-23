/** Bicycle entity class.
 * @author Dodi Mircea Ovidiu
 * @version 20 11 2024
 */

package com.example.ryde.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "bicycle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Bicycle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String model;


    @JoinColumn(name = "location")
    private Long location;
    private Float hourly_rate;
    private Long occupied_by;
}
