package appli.model.principal;

import java.time.LocalDateTime;

public class Hospitalisation {

    private int id;
    private DossierPriseEnCharge dossierPriseEnCharge;
    private Chambre chambre;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public  Hospitalisation() {}

    public Hospitalisation(DossierPriseEnCharge dossierPriseEnCharge, Chambre chambre, LocalDateTime dateDebut, LocalDateTime dateFin) {
        this.dossierPriseEnCharge = dossierPriseEnCharge;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Hospitalisation(int id, DossierPriseEnCharge dossierPriseEnCharge, Chambre chambre, LocalDateTime dateDebut, LocalDateTime dateFin) {
        this.id = id;
        this.dossierPriseEnCharge = dossierPriseEnCharge;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Hospitalisation{" +
                "id=" + id +
                ", dossierPriseEnCharge=" + dossierPriseEnCharge +
                ", chambre=" + chambre +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getRefDossier() {
        if (dossierPriseEnCharge != null && dossierPriseEnCharge.getPatient() != null) {
            FichePatient patient = dossierPriseEnCharge.getPatient();
            return patient.getNom() + " " + patient.getPrenom();
        }
        return "";
    }

    public String getRefChambre() {
        return chambre != null ? chambre.getNum() : "";
    }

    public DossierPriseEnCharge getDossierPriseEnCharge() {
        return dossierPriseEnCharge;
    }

    public void setDossierPriseEnCharge(DossierPriseEnCharge dossierPriseEnCharge) {
        this.dossierPriseEnCharge = dossierPriseEnCharge;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }
}
