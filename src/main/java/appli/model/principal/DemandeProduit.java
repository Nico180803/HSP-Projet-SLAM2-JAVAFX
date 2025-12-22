package appli.model.principal;

import appli.model.enums.Statut;

import java.time.LocalDateTime;

public class DemandeProduit {

    private int id;
    private Utilisateur medecinDemandeur;
    private Produit produit;
    private int quantite;
    private Statut statut;
    LocalDateTime dateDemande;

    public DemandeProduit() {}

    public DemandeProduit(int id, Utilisateur medecinDemandeur, Produit produit, int quantite, Statut statut, LocalDateTime dateDemande) {
        this.id = id;
        this.medecinDemandeur = medecinDemandeur;
        this.produit = produit;
        this.quantite = quantite;
        this.statut = statut;
        this.dateDemande = dateDemande;
    }

    public DemandeProduit(Utilisateur medecinDemandeur, Produit produit, int quantite, Statut statut, LocalDateTime dateDemande) {
        this.medecinDemandeur = medecinDemandeur;
        this.produit = produit;
        this.quantite = quantite;
        this.statut = statut;
        this.dateDemande = dateDemande;
    }

    @Override
    public String toString() {
        return "DemandeProduit{" +
                "id=" + id +
                ", medecinDemandeur=" + medecinDemandeur +
                ", produit=" + produit +
                ", quantite=" + quantite +
                ", dateDemande=" + dateDemande +
                '}';
    }

    public int getId() {
        return id;
    }

    public Utilisateur getMedecinDemandeur() {
        return medecinDemandeur;
    }

    public void setMedecinDemandeur(Utilisateur medecinDemandeur) {
        this.medecinDemandeur = medecinDemandeur;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }
}
