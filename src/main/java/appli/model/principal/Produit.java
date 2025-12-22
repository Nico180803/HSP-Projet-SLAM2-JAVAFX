package appli.model.principal;

import appli.model.enums.Dangerosite;

public class Produit {
    private int id;
    private String libelle;
    private String description;
    private Dangerosite dangerosite;
    private int quantite;

    public Produit() {}

    public Produit(String libelle, String description,Dangerosite dangerosite, int quantite) {
        this.libelle = libelle;
        this.description = description;
        this.dangerosite = dangerosite;
        this.quantite = quantite;
    }

    public Produit(int id, String libelle, String description,Dangerosite dangerosite, int quantite) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.dangerosite = dangerosite;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", dangerosite=" + dangerosite +
                ", quantite=" + quantite +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dangerosite getDangerosite() {
        return dangerosite;
    }

    public void setDangerosite(Dangerosite dangerosite) {
        this.dangerosite = dangerosite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
