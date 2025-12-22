package appli.model.principal;

import java.time.LocalDate;

public class Ordonnance {

    private int id;
    private LocalDate dateTimeOrdonnance;
    private DossierPriseEnCharge dossierPriseEnCharge;

    public Ordonnance() {}

    public Ordonnance(LocalDate dateTimeOrdonnance,  DossierPriseEnCharge dossierPriseEnCharge) {
        this.dateTimeOrdonnance = dateTimeOrdonnance;
        this.dossierPriseEnCharge = dossierPriseEnCharge;
    }

    public Ordonnance(int id, LocalDate dateTimeOrdonnance,  DossierPriseEnCharge dossierPriseEnCharge) {
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

    public LocalDate getDateTimeOrdonnance() {
        return dateTimeOrdonnance;
    }

    public void setDateTimeOrdonnance(LocalDate dateTimeOrdonnance) {
        this.dateTimeOrdonnance = dateTimeOrdonnance;
    }

    public DossierPriseEnCharge getDossierPriseEnCharge() {
        return dossierPriseEnCharge;
    }

    public void setDossierPriseEnCharge(DossierPriseEnCharge dossierPriseEnCharge) {
        this.dossierPriseEnCharge = dossierPriseEnCharge;
    }
}
