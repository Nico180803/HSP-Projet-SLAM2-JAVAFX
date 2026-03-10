package appli.model.principal;

public class Commande {
    private int id;
    private Utilisateur utilisateur;
    private ProduitFournisseur produitFournisseur;
    private int quantite;
    private double prix;

    public Commande(int id, Utilisateur utilisateur, ProduitFournisseur produitFournisseur, int quantite, double prix) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.produitFournisseur = produitFournisseur;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Commande(Utilisateur utilisateur, ProduitFournisseur produitFournisseur, int quantite, double prix) {
        this.utilisateur = utilisateur;
        this.produitFournisseur = produitFournisseur;
        this.quantite = quantite;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ProduitFournisseur getProduitFournisseur() {
        return produitFournisseur;
    }

    public void setProduitFournisseur(ProduitFournisseur produitFournisseur) {
        this.produitFournisseur = produitFournisseur;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getRefUtilisateur() {
        return utilisateur != null ? utilisateur.getNom() + " " + utilisateur.getPrenom() : "";
    }

    public String getRefProduit() {
        return produitFournisseur != null && produitFournisseur.getProduit() != null
                ? produitFournisseur.getProduit().getLibelle() : "";
    }

    public String getRefFournisseur() {
        return produitFournisseur != null && produitFournisseur.getFournisseur() != null
                ? produitFournisseur.getFournisseur().getNom() : "";
    }
}
