package appli.controller.medecin;

import appli.model.principal.Ordonnance;
import appli.service.OrdonnanceService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdonnanceFormController {
    @FXML
    private TableView<Ordonnance> ordonnanceTableView;
    private ObservableList<Ordonnance> ordonnances;
    private OrdonnanceService ordonnanceService = new OrdonnanceService();

    public void initialize() {
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
        ordonnanceTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

}
