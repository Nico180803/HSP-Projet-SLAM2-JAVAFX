package appli.model.principal;

public class ProduitFournisseur {

    private int id;
    private Produit produit;
    private Fournisseur fournisseur;
    private double prix;

    public ProduitFournisseur () {}

    public ProduitFournisseur(Produit produit, Fournisseur fournisseur, double prix) {
        this.produit = produit;
        this.fournisseur = fournisseur;
        this.prix = prix;
    }

    public ProduitFournisseur(int id, Produit produit, Fournisseur fournisseur, double prix) {
        this.id = id;
        this.produit = produit;
        this.fournisseur = fournisseur;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "ProduitFournisseur{" +
                "id=" + id +
                ", produit=" + produit +
                ", fournisseur=" + fournisseur +
                ", prix=" + prix +
                '}';
    }

    public int getId() {
        return id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
