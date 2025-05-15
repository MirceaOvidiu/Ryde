package com.example.ryde.isp;

 import jakarta.persistence.*;
 import lombok.AllArgsConstructor;
 import lombok.Getter;
 import lombok.NoArgsConstructor;
 import lombok.Setter;

 import main.java.com.example.ryde.isp.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {

    private String Iban;

    private void rezervare(Bicicleta bicicleta, Statii location){

        bicicleta.rezervare( bicicleta.getId(), this);


        Cursa cursa = new Cursa();
        cursa.setStartLocation(location.getStationName());
        cursa.setUserId(this.getId());
        cursa.setBikeId(bicicleta.getId());
        cursa.setStartTime(String.valueOf(System.currentTimeMillis()));
        cursa.startCursa();



    }

    private void plata(String location){
        // Implement payment logic here
        // Calcul matematic al pretului in functie de distanta
        // Dupa care faci plata pentru bicicleta respectiva
        // Adaugi cursa intr-un fisier txt

    }
    // ??? Avem nevoie de metoda asta?
    private void eliberare(){

    }

    private void cautaBicicleta(String location){
        // Implement search logic here
        // Faci o cautare a bicicletelor dupa statii si dupa afisezi bicicletele disponibile
        // Daca nu sunt biciclete disponibile, afisezi un mesaj corespunzator

    }
    // aici este putin ciudat, ar trebui  sa stii unde se afla user-ul, eu ma gandeam sa facem o functie in bicicleta care sa aiba o functie baza pe
    // 2 argumente de timp, timpul actual si cel la care s-a rezervat si sa calculeze distanta + bateria
    private void afisareDetaliiCursa(){

    }

    // ar trebui sa facem un fisier text cred in care sa memoram cursele
    private  void afisareIstoricCurse(){
        // Implement history display logic here
    }
}
