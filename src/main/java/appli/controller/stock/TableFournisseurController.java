package appli.controller.stock;

import appli.model.principal.Fournisseur;
import appli.service.FournisseurService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TableFournisseurController implements Initializable {
    @FXML
    private TableView<Fournisseur> tableauFournisseur;
    private ObservableList<Fournisseur> fournisseurs;
    private FournisseurService fournisseurService = new FournisseurService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fournisseurs = fournisseurService.findAll();
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur trouvé");
        }
        tableauFournisseur.setItems(fournisseurs);

        String[][] colonnes = {
                {"Nom", "nom"},
                {"Email", "email"},
                {"Téléphone", "tel"},
        };

        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<Fournisseur, String> maCol = new TableColumn<>(colonnes[i][0]);

            maCol.setCellValueFactory(
                    new PropertyValueFactory<Fournisseur, String>(colonnes[i][1])
            );
            tableauFournisseur.getColumns().add(maCol);
        }
        tableauFournisseur.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }
}