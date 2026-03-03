package appli.controller.stock;

import appli.model.enums.Statut;
import appli.model.principal.DemandeProduit;
import appli.service.DemandeProduitService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TableDemandeProduitController implements Initializable {
    @FXML
    private TableView<DemandeProduit> tableauDemandeProduit;
    private ObservableList<DemandeProduit> demandeProduits;
    private DemandeProduitService demandeProduitService =  new DemandeProduitService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        demandeProduits = demandeProduitService.findAll();
        demandeProduits.removeIf(d -> d.getStatut() != Statut.EN_ATTENTE);
        if (demandeProduits.isEmpty()) {
            System.out.println("Aucun produit trouvé");
        }
        tableauDemandeProduit.setItems(demandeProduits);

        String [][] colonnes = {
                {"Medecin","refMedecin"},
                {"Produit","refProduit"},
                {"Quantité Demandée","quantiteDemandee"},
                {"Statut","statut"},
                {"Date de demande", "dateDemande"}
        };

        for (int i = 0; i < colonnes.length; i++) {
            //Création de la colonne avec le titre
            TableColumn<DemandeProduit,String> maCol = new TableColumn<>(colonnes[i][0]);

            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<DemandeProduit, String>(colonnes[i][1])
            );
            //Ajout de la colonne dans notre tableau
            tableauDemandeProduit.getColumns().add(maCol);
        }
        // Colonne Actions avec boutons Accepter / Refuser
        TableColumn<DemandeProduit, Void> colActions = new TableColumn<>("Actions");
        colActions.setCellFactory(col -> new TableCell<>() {
            private final Button btnAccepter = new Button("Accepter");
            private final Button btnRefuser = new Button("Refuser");
            private final HBox hbox = new HBox(10, btnAccepter, btnRefuser);

            {
                btnAccepter.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                btnRefuser.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");

                btnAccepter.setOnAction(event -> {
                    DemandeProduit demandeProduit = getTableView().getItems().get(getIndex());
                    demandeProduitService.acceptDemandeProduit(demandeProduit);
                    demandeProduits.remove(demandeProduit);
                    System.out.println("Accepter");
                });

                btnRefuser.setOnAction(event -> {
                    DemandeProduit demandeProduit = getTableView().getItems().get(getIndex());
                    demandeProduitService.refusDemandeProduit(demandeProduit);
                    demandeProduits.remove(demandeProduit);
                    System.out.println("Refuser");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });
        tableauDemandeProduit.getColumns().add(colActions);

        tableauDemandeProduit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }
}
