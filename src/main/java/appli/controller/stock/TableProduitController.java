package appli.controller.stock;

import appli.model.principal.Produit;
import appli.service.ProduitService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TableProduitController implements Initializable {
    @FXML
    private TableView<Produit> tableauProduit;
    private ObservableList<Produit> produits;
    private ProduitService produitService =  new ProduitService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        produits = produitService.findAll();
        if (produits.isEmpty()) {
            System.out.println("Aucun produit trouvé");
        }
        tableauProduit.setItems(produits);

        String [][] colonnes = {
                {"Libelle","libelle"},
                {"Description","description"},
                {"Dangerosité","dangerosite"},
                {"Quantité","quantite"},
        };

        for (int i = 0; i < colonnes.length; i++) {
            //Création de la colonne avec le titre
            TableColumn<Produit,String> maCol = new TableColumn<>(colonnes[i][0]);

            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Produit, String>(colonnes[i][1])
            );
            //Ajout de la colonne dans notre tableau
            tableauProduit.getColumns().add(maCol);
        }
        tableauProduit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }
}
