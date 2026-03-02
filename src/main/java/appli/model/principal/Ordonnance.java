package appli.model.principal;

import java.time.LocalDateTime;

public class Ordonnance {

    private int id;
    private LocalDateTime dateTimeOrdonnance;
    private DossierPriseEnCharge dossierPriseEnCharge;

    public Ordonnance() {}

    public Ordonnance(LocalDateTime dateTimeOrdonnance,  DossierPriseEnCharge dossierPriseEnCharge) {
        this.dateTimeOrdonnance = dateTimeOrdonnance;
        this.dossierPriseEnCharge = dossierPriseEnCharge;
    }

    public Ordonnance(int id, LocalDateTime dateTimeOrdonnance,  DossierPriseEnCharge dossierPriseEnCharge) {
        this.id = id;
        this.dateTimeOrdonnance = dateTimeOrdonnance;
        this.dossierPriseEnCharge = dossierPriseEnCharge;
    }

    @Override
    public String toString() {
        return "Ordonnance{" +
                "id=" + id +
                ", dateTimeOrdonnance=" + dateTimeOrdonnance +
                ", dossierPriseEnCharge=" + dossierPriseEnCharge +
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

    public LocalDateTime getDateTimeOrdonnance() {
        return dateTimeOrdonnance;
    }

    public void setDateTimeOrdonnance(LocalDateTime dateTimeOrdonnance) {
        this.dateTimeOrdonnance = dateTimeOrdonnance;
    }

    public DossierPriseEnCharge getDossierPriseEnCharge() {
        return dossierPriseEnCharge;
    }

    public void setDossierPriseEnCharge(DossierPriseEnCharge dossierPriseEnCharge) {
        this.dossierPriseEnCharge = dossierPriseEnCharge;
    }
}
