package appli.controller.medecin;

import appli.model.principal.DemandeProduit;
import appli.model.principal.Hospitalisation;
import appli.service.DemandeService;
import appli.service.HospitalisationService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TableauDemandeStockController implements Initializable {

    @FXML
    private TableView<DemandeProduit> tableauDemande;
    private ObservableList<DemandeProduit> demandeProduits;
    private DemandeService demandeService = new DemandeService();

    public void initialize(URL location, ResourceBundle resources) {
        demandeProduits = demandeService.findAll();
        if (demandeProduits == null || demandeProduits.isEmpty()) {
            System.out.println("Aucune fiche patient trouvée");
        }
        tableauDemande.setItems(demandeProduits);

        String[][] colonnes = {
                {"Medecin", "refMedecin"},
                {"Produit", "refProduit"},
                {"Quantité", "quantiteDemandee"},
                {"Statut", "statut"},
                {"Date de demande", "dateDemande"},


        };

        for (int i = 0; i < colonnes.length; i++) {
            //Création de la colonne avec le titre
            TableColumn<DemandeProduit, String> maCol = new TableColumn<>(colonnes[i][0]);

            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<DemandeProduit, String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            tableauDemande.getColumns().add(maCol);
        }
    }
}
