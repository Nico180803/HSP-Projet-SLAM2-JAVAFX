package appli.model.logs;

public class HistoriqueConnexion {

    private String date;
    private String utilisateur;
    private String action;
    private String description;

    public HistoriqueConnexion(String date, String utilisateur, String action, String description) {
        this.date = date;
        this.utilisateur = utilisateur;
        this.action = action;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public String getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }
}