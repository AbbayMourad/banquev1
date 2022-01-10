package com.banque.banquev1.user;

public class User {

    private Integer ID;
    private String Nom;
    private String Prenom;

    public User(Integer ID, String nom, String prenom) {
        this.ID = ID;
        Nom = nom;
        Prenom = prenom;
    }

    public Integer getID() {
        return ID;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }
}
