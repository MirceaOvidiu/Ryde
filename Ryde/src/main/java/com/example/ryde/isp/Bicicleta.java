package com.example.ryde.isp;

import main.java.com.example.ryde.isp.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bicicleta {
    private String id;
    private String location;
    private String batteryStatus;
    private boolean isAvailable;
    private User user;
    private String name;

    public void afisareDisponibilitate() {
        if (!this.isAvailable) {
            System.out.println("Bicicleta " + this.getId() + " nu este disponibila.");
            return;
        }

        System.out.println("Bicicleta " + this.getId() + " este disponibila.");
    }


    public void rezervare(String id, User user) {
        if (user == null) {
            System.out.println("Utilizatorul nu este valid.");
            return;
        }

        if (isAvailable) {
            isAvailable = false; // Marcheaza bicicleta ca indisponibila
            this.user = user; // Asociaza utilizatorul cu bicicleta
            System.out.println("Bicicleta " + this.getId() + " a fost rezervata de utilizatorul " + user.getName() + ".");
        } else {
            System.out.println("Bicicleta " + this.getId() + " nu este disponibila.");
        }
    }

    public void eliberare( User user, Statii location) {
        if (user == null) {
            System.out.println("Utilizatorul nu este valid.");
            return;
        }

        isAvailable = true;
        this.user = null;
        this.setLocation(location.getStationName());

        System.out.println("Bicicleta " + this.getId() + " a fost eliberata de utilizatorul " + user.getName() + ".");
    }

    public void afisareDetalii() {
        System.out.println("ID: " + this.getId());
        System.out.println("Locatie: " + this.getLocation());
        System.out.println("Stare baterie: " + this.getBatteryStatus());
        System.out.println("Disponibilitate: " + (this.isAvailable ? "Disponibila" : "Indisponibila"));
    }
}
