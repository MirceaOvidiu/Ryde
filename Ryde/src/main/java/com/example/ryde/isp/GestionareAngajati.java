package com.example.ryde.isp;

import java.util.ArrayList;
import java.util.List;

public class GestionareAngajati {
    private List<Angajat> angajati = new ArrayList<>();

    public void adaugaAngajat(Angajat angajat) {
        if (cautaAngajatDupaId(angajat.getId()) == null) {
            angajati.add(angajat);
        } else {
            System.out.println("Angajatul cu acest ID exista deja.");
        }
    }

    public void stergeAngajat(int id) {
        angajati.removeIf(a -> a.getId() == id);
    }

    public void afiseazaAngajati() {
        for (Angajat angajat : angajati) {
            System.out.println(angajat);
        }
    }

    public List<Angajat> getAngajati() {
        return angajati;
    }

    public Angajat cautaAngajatDupaId(int id) {
        for (Angajat a : angajati) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public void actualizeazaAngajat(Angajat angajatNou) {
        for (int i = 0; i < angajati.size(); i++) {
            if (angajati.get(i).getId() == angajatNou.getId()) {
                angajati.set(i, angajatNou);
                return;
            }
        }
        System.out.println("Angajatul nu a fost gasit pentru actualizare.");
    }
}
