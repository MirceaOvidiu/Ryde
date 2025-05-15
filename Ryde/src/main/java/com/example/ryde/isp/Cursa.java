package com.example.ryde.isp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cursa {
    private String id;
    private String startLocation;
    private String endLocation;
    private String startTime;
    private String endTime;
    private double distance;
    private String bikeId;
    private String userId;

    public void afisareDetalii() {
        System.out.println("ID: " + this.id);
        System.out.println("Locatie de start: " + this.startLocation);
        System.out.println("Locatie de final: " + this.endLocation);
        System.out.println("Timp de start: " + this.startTime);
        System.out.println("Timp de final: " + this.endTime);
        System.out.println("Distanta: " + this.distance + " km");
        System.out.println("ID Bicicleta: " + this.bikeId);
        System.out.println("ID Utilizator: " + this.userId);
    }

    public void startCursa() {
        System.out.println("Cursa a inceput de la " + startLocation + " la " + endLocation);
    }

    public void endCursa() {
        System.out.println("Cursa s-a terminat de la " + startLocation + " la " + endLocation);
    }

    public void writeCursaToFile(Cursa cursa) {

         try (FileWriter writer = new FileWriter("cursa.txt", true)) {
             writer.write(cursa.toString());
         } catch (IOException e) {
             e.printStackTrace();
         }
    }


}
