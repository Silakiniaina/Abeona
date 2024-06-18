package main;

import obj.Evenement;

public class Main {
    public static void main(String[] args) {
        try {
            Evenement evenement = new Evenement();
            Evenement evenements = evenement.getById("EVT2");
            System.out.println(evenements.getDescription());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
