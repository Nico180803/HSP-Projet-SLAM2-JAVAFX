package appli.controller.medecin;

import appli.controller.main.MainController;
import appli.factory.DaoFactory;
import appli.model.enums.Statut;
import appli.model.principal.DemandeProduit;
import appli.model.principal.Produit;
import appli.model.principal.Utilisateur;
import appli.service.DemandeService;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import session.SessionUtilisateur;

import java.time.LocalDateTime;

public class DemandeStockController {

    public ChoiceBox<Produit> refProduit;
    public TextField quantiteDemandee;

    private final DemandeService demandeService = new DemandeService();

    public void initialize() {
        refProduit.getItems().addAll(DaoFactory.getProduitDAO().getAll());
    }

    public void onValiderFiche(ActionEvent actionEvent) {
        Produit produit = refProduit.getSelectionModel().getSelectedItem();
        String quantiteText = quantiteDemandee.getText();

        if (produit == null || quantiteText == null || quantiteText.isEmpty()) {
            System.out.println("Erreur : champs incomplets");
            return;
        }

        int quantite;
        try {
            quantite = Integer.parseInt(quantiteText);
        } catch (NumberFormatException e) {
            System.out.println("Erreur : la quantité doit être un nombre");
            return;
        }

        Utilisateur medecin = SessionUtilisateur.getInstance().getUtilisateur();

        DemandeProduit demande = new DemandeProduit(medecin, produit, quantite, Statut.EN_ATTENTE, LocalDateTime.now());
        demandeService.add(demande);
        MainController.getInstance().refreshMain();
    }
}
