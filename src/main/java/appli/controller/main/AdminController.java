package appli.controller.main;

import appli.main.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import session.SessionUtilisateur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import appli.controller.HistoriqueConnexionController;
import appli.dao.logs.jdbc.LogsUtilisateurDAO;
import appli.model.logs.LogsUtilisateur;
import appli.model.logs.HistoriqueConnexion;

import java.io.IOException;

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

    public void onPageInscriptionButtonClick() throws IOException {
        HelloApplication.changeScene("Inscription.fxml");
    }

    @FXML
    public void onHistoriqueConnexion(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/appli/admin/HistoriqueConnexion.fxml"));
        Parent root = loader.load();

        HistoriqueConnexionController controller = loader.getController();

        LogsUtilisateurDAO logsDAO = new LogsUtilisateurDAO();
        ObservableList<HistoriqueConnexion> data = FXCollections.observableArrayList();

        for (LogsUtilisateur log : logsDAO.getAll()) {
            String utilisateur = (log.getUtilisateur() != null) ? String.valueOf(log.getUtilisateur().getId()) : "Inconnu";

            data.add(new HistoriqueConnexion(
                    (log.getDateAction() != null) ? log.getDateAction().toString() : "",
                    utilisateur,
                    (log.getTypeAction() != null) ? log.getTypeAction().toString() : "",
                    (log.getDetails() != null) ? log.getDetails() : ""
            ));
        }

        controller.setData(data);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void onDeconnexionButtonClick(ActionEvent event) throws IOException {
        SessionUtilisateur.getInstance().deconnecter();
        HelloApplication.changeScene("/appli/main/Login.fxml");
        System.out.println("Déconnexion et retour à l'accueil");
    }
}