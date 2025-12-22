package appli.model.principal;

import appli.model.enums.Gravite;

import java.time.LocalDateTime;

public class DossierPriseEnCharge {

    private int id;
    private LocalDateTime dateArrive;
    private String description;
    private Gravite gravite;
    private FichePatient patient;
    private Utilisateur createdBy;
    private boolean estTraite;

    public DossierPriseEnCharge(){}

    public DossierPriseEnCharge(LocalDateTime dateArrive, String description, Gravite gravite, FichePatient patient, Utilisateur createdBy, boolean estTraite ) {
        this.dateArrive = dateArrive;
        this.description = description;
        this.gravite = gravite;
        this.patient = patient;
        this.createdBy = createdBy;
        this.estTraite = estTraite;
    }

    public DossierPriseEnCharge(int id, LocalDateTime dateArrive, String description, Gravite gravite, FichePatient patient, Utilisateur createdBy, boolean estTraite ) {
        this.id = id;
        this.dateArrive = dateArrive;
        this.description = description;
        this.gravite = gravite;
        this.patient = patient;
        this.createdBy = createdBy;
        this.estTraite = estTraite;
    }

    @Override
    public String toString() {
        return "DossierPriseEnCharge{" +
                "id=" + id +
                ", dateArrive=" + dateArrive +
                ", description='" + description + '\'' +
                ", gravite=" + gravite +
                ", patient=" + patient +
                ", createdBy=" + createdBy +
                ", estTraite=" + estTraite +
                '}';
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(LocalDateTime dateArrive) {
        this.dateArrive = dateArrive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gravite getGravite() {
        return gravite;
    }

    public void setGravite(Gravite gravite) {
        this.gravite = gravite;
    }

    public FichePatient getPatient() {
        return patient;
    }

    public void setPatient(FichePatient patient) {
        this.patient = patient;
    }

    public Utilisateur getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Utilisateur createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isEstTraite() {
        return estTraite;
    }

    public void setEstTraite(boolean estTraite) {
        this.estTraite = estTraite;
    }
}
