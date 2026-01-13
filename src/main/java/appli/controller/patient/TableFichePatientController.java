package appli.controller.patient;
import appli.model.principal.FichePatient;
import appli.service.FichePatientService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class TableFichePatientController implements Initializable {
    @FXML
    private TableView<FichePatient> tableauFichePatient;
    private ObservableList<FichePatient> fichePatients;
    private FichePatientService fichePatientService = new FichePatientService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fichePatients = fichePatientService.findAll();
        if (fichePatients == null || fichePatients.isEmpty()) {
            System.out.println("Aucune fiche patient trouvée");
            tableauFichePatient.setItems(fichePatients);
        }


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
    }
}