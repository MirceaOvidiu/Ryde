package com.example.ryde.isp;

public class Administrator {
    private String username;
    private String parola;

    public Administrator(String username, String parola) {
        this.username = username;
        this.parola = parola;
    }

    public boolean autentificare(String user, String pass) {
        return username.equals(user) && parola.equals(pass);
    }

    public void editeazaCont(String newUsername, String newParola) {
        if (newParola == null || newParola.isEmpty()) {
            System.out.println("Parola nu poate fi goala.");
            return;
        }
        this.username = newUsername;
        this.parola = newParola;
    }

    public String getUsername() {
        return username;
    }

    public void adaugaBicicleta(Bicicleta b, GestionareBiciclete gestionare) {
        gestionare.adaugaBicicleta(b);
    }

    public void stergeBicicleta(int id, GestionareBiciclete gestionare) {
        gestionare.stergeBicicleta(id);
    }

    public void vizualizareBiciclete(GestionareBiciclete gestionare) {
        gestionare.afiseazaBiciclete();
    }

    public void actualizeazaBicicleta(Bicicleta b, GestionareBiciclete gestionare) {
        gestionare.actualizeazaBicicleta(b);
    }

    public void adaugaClient(Client c, GestionareClienti gestionare) {
        gestionare.adaugaClient(c);
    }

    public void stergeClient(int id, GestionareClienti gestionare) {
        gestionare.stergeClient(id);
    }

    public void vizualizareClienti(GestionareClienti gestionare) {
        gestionare.afiseazaClienti();
    }

    public void actualizeazaClient(Client c, GestionareClienti gestionare) {
        gestionare.actualizeazaClient(c);
    }

    public void adaugaAngajat(Angajat a, GestionareAngajati gestionare) {
        gestionare.adaugaAngajat(a);
    }

    public void stergeAngajat(int id, GestionareAngajati gestionare) {
        gestionare.stergeAngajat(id);
    }

    public void vizualizareAngajati(GestionareAngajati gestionare) {
        gestionare.afiseazaAngajati();
    }

    public void actualizeazaAngajat(Angajat a, GestionareAngajati gestionare) {
        gestionare.actualizeazaAngajat(a);
    }
}
