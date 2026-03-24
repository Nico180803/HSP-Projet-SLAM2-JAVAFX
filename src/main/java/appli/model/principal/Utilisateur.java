package appli.model.principal;

import appli.model.enums.Role;

public class Utilisateur {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private Role role;
    private boolean isActive;


    public Utilisateur(String nom, String prenom, String email, String mdp, Role role, boolean isActive) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.isActive = isActive;
    }

    public Utilisateur(int id, String nom, String prenom, String email, String mdp, Role role, boolean isActive) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return  nom + ' ' +
                prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public Role getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

}
