package appli.model.principal;

public class Fournisseur {

    private int id;
    private String nom;
    private String email;
    private String tel;

    public  Fournisseur() {}

    public Fournisseur(String nom, String email, String tel) {
        this.nom = nom;
        this.email = email;
        this.tel = tel;
    }

    public Fournisseur(int id, String nom, String email, String tel) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", tel='" + tel + '\'' +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
