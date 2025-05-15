package com.example.ryde.isp;

import java.util.ArrayList;
import java.util.List;

public class GestionareClienti {
    private List<Client> clienti = new ArrayList<>();

    public void adaugaClient(Client client) {
        if (cautaClientDupaId(client.getId()) == null) {
            clienti.add(client);
        } else {
            System.out.println("Clientul cu acest ID exista deja.");
        }
    }

    public void stergeClient(int id) {
        clienti.removeIf(c -> c.getId() == id);
    }

    public void afiseazaClienti() {
        for (Client client : clienti) {
            System.out.println(client);
        }
    }

    public List<Client> getClienti() {
        return clienti;
    }

    public Client cautaClientDupaId(int id) {
        for (Client c : clienti) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void actualizeazaClient(Client clientNou) {
        for (int i = 0; i < clienti.size(); i++) {
            if (clienti.get(i).getId() == clientNou.getId()) {
                clienti.set(i, clientNou);
                return;
            }
        }
        System.out.println("Clientul nu a fost gasit pentru actualizare.");
    }
}
