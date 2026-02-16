package appli.model.logs;

import appli.model.principal.Utilisateur;

import java.time.LocalDateTime;

public class LogsUtilisateur {

    private int id;
    private Utilisateur utilisateur;
    private TypeAction typeAction;
    private TableCible tableCible;
    private int refLigne;
    private LocalDateTime dateAction;
    private String details;


    public LogsUtilisateur() {}

    public LogsUtilisateur(int id,  Utilisateur utilisateur, TypeAction typeAction,  TableCible tableCible, int refLigne, LocalDateTime dateAction, String details) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.typeAction = typeAction;
        this.tableCible = tableCible;
        this.refLigne = refLigne;
        this.dateAction = dateAction;
        this.details = details;
    }

    @Override
    public String toString() {
        return "LogsUtilisateur{" +
                "id=" + id +
                ", utilisateur=" + utilisateur +
                ", typeAction=" + typeAction +
                ", tableCible=" + tableCible +
                ", refLigne=" + refLigne +
                ", dateAction=" + dateAction +
                ", details='" + details + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public TypeAction getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(TypeAction typeAction) {
        this.typeAction = typeAction;
    }

    public TableCible getTableCible() {
        return tableCible;
    }

    public void setTableCible(TableCible tableCible) {
        this.tableCible = tableCible;
    }

    public int getRefLigne() {
        return refLigne;
    }

    public void setRefLigne(int refLigne) {
        this.refLigne = refLigne;
    }

    public LocalDateTime getDateAction() {
        return dateAction;
    }

    public void setDateAction(LocalDateTime dateAction) {
        this.dateAction = dateAction;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDescription(String s) {

    }

    public void setLogsUtilisateur(String s) {
    }
}
