package appli.controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class AdminController extends MainController {

    @FXML
    private StackPane contentPane;

    @FXML
    private AnchorPane testDroite;

    @FXML
    public Label userLabel;

    public void initialize() {
        // Configurer les panes hérités de MainController
        mainPane = contentPane;
        sidePage = testDroite;

        // Par défaut, on charge la première vue Médecin
        load("/appli/patient/TableDossier.fxml");
        System.out.println("Admin interface initialisée");
    }

    // ----------------- Médecin -----------------
    @FXML
    public void onDossierMedecin(ActionEvent event) {
        load("/appli/patient/TableDossier.fxml");
        System.out.println("Médecin: Fiches Patient cliqué");
    }

    @FXML
    public void onDemandeDeStock(ActionEvent event) {
        load("/appli/medecin/TableauDemandeStock.fxml");
        loadSide("/appli/medecin/DemandeStockForm.fxml");
        System.out.println("Médecin: Demande de stock cliqué");
    }

    @FXML
    public void onHospitalisation(ActionEvent event) {
        load("/appli/medecin/HospitalisationForm.fxml");
        loadSide("/appli/medecin/HospitalisationNew.fxml");
        System.out.println("Médecin: Hospitalisation cliqué");
    }

    @FXML
    public void onOrdonnance(ActionEvent event) {
        load("/appli/medecin/OrdonnanceForm.fxml");
        loadSide("/appli/medecin/HospitalisationNew.fxml");
        System.out.println("Médecin: Ordonnance cliqué");
    }

    // ----------------- Secrétariat -----------------
    @FXML
    public void onFichesPatients(ActionEvent event) {
        load("/appli/patient/TableFichePatient.fxml");
        loadSide("/appli/patient/FormFichePatient.fxml");
        System.out.println("Secrétariat: Fiches Patients cliqué");
    }

    @FXML
    public void onDossierSecretaire(ActionEvent event) {
        load("/appli/patient/TableDossier.fxml");
        loadSide("/appli/patient/FormDossier.fxml");
        System.out.println("Secrétariat: Dossiers cliqué");
    }

    // ----------------- Stock -----------------
    @FXML
    public void onListeStock(ActionEvent event) {
        load("/appli/stock/TableProduit.fxml");
        System.out.println("Stock: Liste Stock cliqué");
    }

    @FXML
    public void onValidRefus(ActionEvent event) {
        load("/appli/stock/TableDemandeProduit.fxml");
        System.out.println("Stock: Validation/Refus cliqué");
    }

    @FXML
    public void onFournisseur(ActionEvent event) {
        load("/appli/stock/TableFournisseur.fxml");
        System.out.println("Stock: Fournisseur cliqué");
    }
}