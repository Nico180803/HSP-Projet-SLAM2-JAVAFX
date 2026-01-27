package appli.controller.management;

import appli.model.principal.DossierPriseEnCharge;
import appli.model.principal.FichePatient;
import appli.model.principal.Hospitalisation;
import appli.model.principal.Ordonnance;
import appli.service.DossierPriseEnChargeService;
import appli.service.FichePatientService;
import appli.service.HospitalisationService;
import appli.service.OrdonnanceService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MedecinManagementController implements Initializable {

    @FXML
    private TableView<Ordonnance> ordonnanceTableView;
    @FXML
    private TableView<Hospitalisation> hospitalisationTableView;
    private ObservableList<Ordonnance> ordonnances;
    private ObservableList<Hospitalisation> hospitalisations;
    private HospitalisationService hospitalisationService = new HospitalisationService();
    private OrdonnanceService ordonnanceService = new OrdonnanceService();
    @FXML
    private TableView<FichePatient> tableauFichePatient;
    private ObservableList<FichePatient> fichePatients;
    private FichePatientService fichePatientService = new FichePatientService();
    private TableView<DossierPriseEnCharge> tableauDossierPriseEnCharge;
    private ObservableList<DossierPriseEnCharge> dossierPriseEnCharges;
    private DossierPriseEnChargeService dossierPriseEnChargeService = new DossierPriseEnChargeService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableauFichePatient.setVisible(true);
        ordonnanceTableView.setVisible(false);
        hospitalisationTableView.setVisible(false);
        fichePatients = fichePatientService.findAll();
        if (fichePatients == null || fichePatients.isEmpty()) {
            System.out.println("Aucune fiche patient trouvée");
        }
        tableauFichePatient.setItems(fichePatients);

        String [][] colonnes = {
                { "Nom","nom" },
                { "Prénom","prenom" },
                { "Sécurité sociale","numSecuSocial" },
                { "Telephone","tel" },
                { "Rue","rue" },
                { "Numéro de rue","numRue" },
                { "ville","ville" },
                { "cp","cp" },

        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<FichePatient,String> maCol = new TableColumn<>(colonnes[i][0]);

            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<FichePatient,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            tableauFichePatient.getColumns().add(maCol);
        }
        TableColumn<FichePatient, Void> dossierCol = new TableColumn<>("Dossier");

        dossierCol.setCellFactory(col -> new TableCell<>() {

            private final Button btnDossier = new Button("Dossier");

            {
                btnDossier.setOnAction(e -> {
                    FichePatient patient =
                            getTableView().getItems().get(getIndex());

                    System.out.println("Dossier du patient : "
                            + patient.getNom() + " " + patient.getPrenom());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnDossier);
                }
            }
        });

        tableauFichePatient.getColumns().add(dossierCol);

    }

    @FXML
    public void onDemandeDeStock(ActionEvent actionEvent) {
    }

    public void onHospitalisation(ActionEvent actionEvent) {
        tableauFichePatient.setVisible(false);
        ordonnanceTableView.setVisible(false);
        hospitalisationTableView.setVisible(true);
        hospitalisations = hospitalisationService.findAll();
        if (hospitalisations == null || hospitalisations.isEmpty()) {
            System.out.println("Aucune fiche patient trouvée");
        }
        hospitalisationTableView.setItems(hospitalisations);

        String [][] colonnes = {
                { "Dossier","refDossier" },
                { "Chambre","refChambre" },
                { "Date de début","dateDebut" },
                { "Date de fin","dateFin" },


        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<Hospitalisation,String> maCol = new TableColumn<>(colonnes[i][0]);

            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Hospitalisation,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            hospitalisationTableView.getColumns().add(maCol);
        }
    }

    public void onOrdonnance(ActionEvent actionEvent) {
        tableauFichePatient.setVisible(false);
        ordonnanceTableView.setVisible(true);
        hospitalisationTableView.setVisible(false);
        ordonnances = ordonnanceService.findAll();
        if (ordonnances == null || ordonnances.isEmpty()) {
            System.out.println("Aucune fiche patient trouvée");
        }
        ordonnanceTableView.setItems(ordonnances);

        String [][] colonnes = {
                { "Date de l'ordonnace","dateTimeOrdonnance" },
                { "Dossier","refDossier" },



        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<Ordonnance,String> maCol = new TableColumn<>(colonnes[i][0]);

            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Ordonnance,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            ordonnanceTableView.getColumns().add(maCol);
        }
    }
}
