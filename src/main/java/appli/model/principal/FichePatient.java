package appli.model.principal;

public class FichePatient {

    private int id;
    private String nom;
    private String prenom;
    private String numSecuSociale;
    private String telephone;
    private String email;
    private String rue;
    private String numRue;
    private String ville;
    private String cp;

    public FichePatient(){}

    public FichePatient(String nom, String prenom, String numSecuSociale, String telephone, String email, String rue, String numRue, String ville, String cp) {
        this.nom = nom;
        this.prenom = prenom;
        this.numSecuSociale = numSecuSociale;
        this.telephone = telephone;
        this.email = email;
        this.rue = rue;
        this.numRue = numRue;
        this.ville = ville;
        this.cp = cp;
    }

    public FichePatient(int id, String nom, String prenom, String numSecuSociale, String telephone, String email, String rue, String numRue, String ville, String cp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numSecuSociale = numSecuSociale;
        this.telephone = telephone;
        this.email = email;
        this.rue = rue;
        this.numRue = numRue;
        this.ville = ville;
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "FichePatient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numSecuSociale='" + numSecuSociale + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", rue='" + rue + '\'' +
                ", numRue='" + numRue + '\'' +
                ", ville='" + ville + '\'' +
                ", cp='" + cp + '\'' +
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumSecuSociale() {
        return numSecuSociale;
    }

    public void setNumSecuSociale(String numSecuSociale) {
        this.numSecuSociale = numSecuSociale;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumRue() {
        return numRue;
    }

    public void setNumRue(String numRue) {
        this.numRue = numRue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getAdresseComplete() {
        return numRue + " " + rue + ", " + ville + ", " + cp;
    }
}
