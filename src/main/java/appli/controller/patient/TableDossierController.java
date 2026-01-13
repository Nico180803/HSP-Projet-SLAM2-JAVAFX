package appli.controller.patient;
import appli.model.principal.FichePatient;
import appli.model.principal.Utilisateur;
import appli.service.FichePatientService;
import javafx.collections.FXCollections;
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
    private TableView<FichePatient> tableauFichePatient;
    private ObservableList<FichePatient> fichePatients;
    private FichePatientService fichePatientService = new FichePatientService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}