package appli.model.principal;

public class FichePatient {

    private int id;
    private String nom;
    private String prenom;
    private String numSecuSocial;
    private String telephone;
    private String email;
    private String rue;
    private String numRue;
    private String ville;
    private String cp;


    public FichePatient(String nom, String prenom, String numSecuSocial, String telephone, String email, String rue, String numRue, String ville, String cp) {
        this.nom = nom;
        this.prenom = prenom;
        this.numSecuSocial = numSecuSocial;
        this.telephone = telephone;
        this.email = email;
        this.rue = rue;
        this.numRue = numRue;
        this.ville = ville;
        this.cp = cp;
    }

    public FichePatient(int id, String nom, String prenom, String numSecuSocial, String telephone, String email, String rue, String numRue, String ville, String cp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numSecuSocial = numSecuSocial;
        this.telephone = telephone;
        this.email = email;
        this.rue = rue;
        this.numRue = numRue;
        this.ville = ville;
        this.cp = cp;
    }

    @Override
    public String toString() {
        return
                nom + ' ' +
                prenom + ' ' +
                numSecuSocial + ' ' +
                email;
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


    public String getNumSecuSocial() {
        return numSecuSocial;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getRue() {
        return rue;
    }


    public String getNumRue() {
        return numRue;
    }

    public String getVille() {
        return ville;
    }

    public String getCp() {
        return cp;
    }

    public String getAdresseComplete() {
        return numRue + " " + rue + ", " + ville + ", " + cp;
    }
}
