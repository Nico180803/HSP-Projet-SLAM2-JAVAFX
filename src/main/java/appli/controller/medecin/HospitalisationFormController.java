package appli.controller.medecin;

import appli.model.principal.FichePatient;
import appli.model.principal.Hospitalisation;
import appli.service.HospitalisationService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HospitalisationFormController implements Initializable {

    @FXML
    private TableView<Hospitalisation> hospitalisationTableView;
    private ObservableList<Hospitalisation> hospitalisations;
    private HospitalisationService hospitalisationService = new HospitalisationService();

    public void initialize(URL location, ResourceBundle resources) {
        hospitalisations = hospitalisationService.findAll();
        if (hospitalisations == null || hospitalisations.isEmpty()) {
            System.out.println("Aucune fiche patient trouvée");
        }
        hospitalisationTableView.setItems(hospitalisations);

        String[][] colonnes = {
                {"Dossier", "refDossier"},
                {"Chambre", "refChambre"},
                {"Date de début", "dateDebut"},
                {"Date de fin", "dateFin"},


        };

        for (int i = 0; i < colonnes.length; i++) {
            //Création de la colonne avec le titre
            TableColumn<Hospitalisation, String> maCol = new TableColumn<>(colonnes[i][0]);

            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Hospitalisation, String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            hospitalisationTableView.getColumns().add(maCol);
        }
        hospitalisationTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }
}
