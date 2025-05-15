package com.example.ryde.isp;

import java.util.ArrayList;
import java.util.List;

public class GestionareBiciclete {
    private List<Bicicleta> biciclete = new ArrayList<>();

    public void adaugaBicicleta(Bicicleta bicicleta) {
        if (cautaBicicletaDupaId(bicicleta.getId()) == null) {
            biciclete.add(bicicleta);
        } else {
            System.out.println("Bicicleta cu acest ID exista deja.");
        }
    }

    public void stergeBicicleta(int id) {
        biciclete.removeIf(b -> b.getId() == id);
    }

    public void afiseazaBiciclete() {
        for (Bicicleta bicicleta : biciclete) {
            System.out.println(bicicleta);
        }
    }

    public List<Bicicleta> getBiciclete() {
        return biciclete;
    }

    public Bicicleta cautaBicicletaDupaId(int id) {
        for (Bicicleta b : biciclete) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public void actualizeazaBicicleta(Bicicleta bicicletaNoua) {
        for (int i = 0; i < biciclete.size(); i++) {
            if (biciclete.get(i).getId() == bicicletaNoua.getId()) {
                biciclete.set(i, bicicletaNoua);
                return;
            }
        }
        System.out.println("Bicicleta nu a fost gasita pentru actualizare.");
    }
}
