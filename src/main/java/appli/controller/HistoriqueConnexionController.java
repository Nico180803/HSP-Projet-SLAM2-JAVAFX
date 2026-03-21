package appli.controller;

import appli.model.logs.HistoriqueConnexion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HistoriqueConnexionController {

    @FXML private TableView<HistoriqueConnexion> tableHistorique;
    @FXML private TableColumn<HistoriqueConnexion, String> colDate;
    @FXML private TableColumn<HistoriqueConnexion, String> colUtilisateur;
    @FXML private TableColumn<HistoriqueConnexion, String> colAction;
    @FXML private TableColumn<HistoriqueConnexion, String> colDescription;
    @FXML private TextField searchField;

    private ObservableList<HistoriqueConnexion> masterData;

    @FXML
    public void initialize() {

        colDate.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDate()));

        colUtilisateur.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getUtilisateur()));

        colAction.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getAction()));

        colDescription.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDescription()));

    }

    public void setData(ObservableList<HistoriqueConnexion> data) {
        this.masterData = data;
        tableHistorique.setItems(data);
    }

    @FXML
    private void handleSearch() {

        if (masterData == null) return;

        String keyword = searchField.getText();

        if (keyword == null || keyword.isEmpty()) {
            tableHistorique.setItems(masterData);
            return;
        }

        tableHistorique.setItems(
                masterData.filtered(h ->
                        h.getUtilisateur().toLowerCase()
                                .contains(keyword.toLowerCase()))
        );
    }

    @FXML
    private void handleRefresh() {

        if (masterData != null) {
            tableHistorique.setItems(masterData);
        }

        searchField.clear();
    }
}