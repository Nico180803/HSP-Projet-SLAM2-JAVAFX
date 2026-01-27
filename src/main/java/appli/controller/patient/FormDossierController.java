package appli.controller.patient;

import appli.controller.main.MainController;
import appli.model.enums.Gravite;
import appli.model.principal.DossierPriseEnCharge;
import appli.model.principal.FichePatient;
import appli.model.principal.Utilisateur;
import appli.service.DossierPriseEnChargeService;
import appli.service.FichePatientService;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class FormDossierController
{
    public ChoiceBox<Gravite> graviteField;
    public TextArea descriptionField;
    public ChoiceBox<FichePatient> patientField;


    private final FichePatientService fichePatientService = new FichePatientService();
    private final DossierPriseEnChargeService dossierPriseEnChargeService = new DossierPriseEnChargeService();


    public void initialize()
    {

        //GRAVITE
        graviteField.getItems().addAll(Gravite.values());
        graviteField.getSelectionModel().select(0);

        //FICHE PATIENT
        //patientField.getItems().addAll(fichePatientService.findAll());
    }

    public void onValiderDossier(ActionEvent actionEvent) {

        String description = descriptionField.getText();
        Gravite gravite = graviteField.getSelectionModel().getSelectedItem();
        FichePatient fichePatient = patientField.getSelectionModel().getSelectedItem();

        if (description.isEmpty() || fichePatient==null ||  gravite==null)
        {
            System.out.println("Erreur champs incomplets");
        }else{
            //une fois la connexion faite, tester l'ajout//

            //DossierPriseEnCharge dossierPriseEnCharge = new DossierPriseEnCharge(LocalDateTime.now(),description,gravite,fichePatient,"",false);
            //dossierPriseEnChargeService.addDossier();


            //On recharge le tableau après l'ajout de la fiche patient
            MainController.getInstance().load("/appli/patient/TableDossier.fxml");
        }

    }
}
