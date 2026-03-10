package appli.controller.stock;

import appli.model.principal.Commande;
import appli.service.CommandeService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TableCommandeController implements Initializable {
    @FXML
    private TableView<Commande> tableauCommande;
    private ObservableList<Commande> commandes;
    private CommandeService commandeService = new CommandeService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        commandes = commandeService.findAll();
        if (commandes.isEmpty()) {
            System.out.println("Aucune commande trouvée");
        }
        tableauCommande.setItems(commandes);

        String[][] colonnes = {
                {"Utilisateur", "refUtilisateur"},
                {"Produit", "refProduit"},
                {"Fournisseur", "refFournisseur"},
                {"Quantité", "quantite"},
                {"Prix", "prix"},
        };

        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<Commande, String> maCol = new TableColumn<>(colonnes[i][0]);

            maCol.setCellValueFactory(
                    new PropertyValueFactory<Commande, String>(colonnes[i][1])
            );
            tableauCommande.getColumns().add(maCol);
        }
        tableauCommande.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }
}