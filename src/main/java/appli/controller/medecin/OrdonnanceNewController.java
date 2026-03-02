package appli.controller.medecin;

import appli.controller.main.MainController;
import appli.model.principal.DossierPriseEnCharge;
import appli.model.principal.Ordonnance;
import appli.service.DossierPriseEnChargeService;
import appli.service.OrdonnanceService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDateTime;

public class OrdonnanceNewController {

    public ChoiceBox<DossierPriseEnCharge> refDossier;
    public DatePicker dateOrdonnance;

    private final DossierPriseEnChargeService dossierPriseEnChargeService = new DossierPriseEnChargeService();
    private final OrdonnanceService ordonnanceService = new OrdonnanceService();

    public void initialize() {
        ObservableList<DossierPriseEnCharge> dossiers = dossierPriseEnChargeService.findAll();
        for (DossierPriseEnCharge dossier : dossiers) {
            if (!dossier.isEstTraite()) {
                refDossier.getItems().add(dossier);
            }
        }
    }

    public void onValiderFiche(ActionEvent actionEvent) {
        DossierPriseEnCharge dossier = refDossier.getSelectionModel().getSelectedItem();

        if (dossier == null || dateOrdonnance.getValue() == null) {
            System.out.println("Erreur : champs incomplets");
            return;
        }

        LocalDateTime dateTime = dateOrdonnance.getValue().atStartOfDay();

        Ordonnance ordonnance = new Ordonnance(dateTime, dossier);
        ordonnanceService.add(ordonnance);
        MainController.getInstance().refreshMain();
    }
}
