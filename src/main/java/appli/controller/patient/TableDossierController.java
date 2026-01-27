package appli.controller.patient;
import appli.controller.main.MainController;
import appli.model.principal.DossierPriseEnCharge;
import appli.service.DossierPriseEnChargeService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class TableDossierController implements Initializable {
    @FXML
    private TableView<DossierPriseEnCharge> tableauDossierPriseEnCharge;
    private ObservableList<DossierPriseEnCharge> dossierPriseEnCharges;
    private final DossierPriseEnChargeService dossierPriseEnChargeService = new DossierPriseEnChargeService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dossierPriseEnCharges = dossierPriseEnChargeService.findAll();
        if (dossierPriseEnCharges == null || dossierPriseEnCharges.isEmpty()) {
            System.out.println("Aucun dossiers trouvée");
        }
        tableauDossierPriseEnCharge.setItems(dossierPriseEnCharges);

        String [][] colonnes = {
                { "Date d'arrivé","dateTimeArrive" },
                { "Description","description" },
                { "Gravité","gravite" },
                /* LIGNES DU DESSOUS A MODIFIER AVEC LE NOM DE LA FICHE PATIENT ET PTET SOUS FORME DE LIEN POUR Y ACCEDER*/
                { "Ref Patient","ref_patient" },
                { "Créer par ","ref_createdBy" },
                { "Traité ?","est_traite" },
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<DossierPriseEnCharge,String> maCol = new TableColumn<>(colonnes[i][0]);

            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<DossierPriseEnCharge,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            tableauDossierPriseEnCharge.getColumns().add(maCol);
        }
        tableauDossierPriseEnCharge.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        MainController.getInstance().loadSide("/appli/patient/FormDossier.fxml");
    }
}