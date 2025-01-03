package com.example.ryde.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="docking_station")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DockingStation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "available_bicycles")
    private int available_bicycles;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;
}
