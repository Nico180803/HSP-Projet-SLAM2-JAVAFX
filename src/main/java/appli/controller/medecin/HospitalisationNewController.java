package appli.controller.medecin;

import appli.controller.main.MainController;
import appli.factory.DaoFactory;
import appli.model.principal.Chambre;
import appli.model.principal.DossierPriseEnCharge;
import appli.model.principal.Hospitalisation;
import appli.service.DossierPriseEnChargeService;
import appli.service.HospitalisationService;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDateTime;

public class HospitalisationNewController {

    public ChoiceBox<Chambre> refChambre;
    public ChoiceBox<DossierPriseEnCharge> refDossier;
    public DatePicker dateDebut;
    public DatePicker dateFin;

    private final DossierPriseEnChargeService dossierPriseEnChargeService = new DossierPriseEnChargeService();
    private final HospitalisationService hospitalisationService = new HospitalisationService();

    public void initialize() {
        refDossier.getItems().addAll(dossierPriseEnChargeService.findAll());

        refChambre.getItems().addAll(DaoFactory.getChambreDAO().getAll());
    }

    public void onValiderFiche(ActionEvent actionEvent) {
        DossierPriseEnCharge dossier = refDossier.getSelectionModel().getSelectedItem();
        Chambre chambre = refChambre.getSelectionModel().getSelectedItem();

        if (dossier == null || chambre == null || dateDebut.getValue() == null || dateFin.getValue() == null) {
            System.out.println("Erreur : champs incomplets");
            return;
        }

        LocalDateTime debut = dateDebut.getValue().atStartOfDay();
        LocalDateTime fin = dateFin.getValue().atStartOfDay();

        Hospitalisation hospitalisation = new Hospitalisation(dossier, chambre, debut, fin);
        hospitalisationService.add(hospitalisation);
        MainController.getInstance().refreshMain();
    }
}
