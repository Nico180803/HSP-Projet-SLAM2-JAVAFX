package appli.dao.principal.jdbc;

import appli.dao.logs.jdbc.LogsUtilisateurDAO;
import appli.model.logs.LogsUtilisateur;
import appli.model.logs.TableCible;
import appli.model.logs.TypeAction;
import appli.model.principal.Utilisateur;

import java.time.LocalDateTime;

public class LoginControllerDAO {

    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private final LogsUtilisateurDAO logsDAO = new LogsUtilisateurDAO();


    public Utilisateur connecter(String email, String password) {

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return null;
        }

        Utilisateur utilisateur = utilisateurDAO.getByEmail(email);

        if (utilisateur != null) {

            LogsUtilisateur log = new LogsUtilisateur();
            log.setUtilisateur(utilisateur);
            log.setDateAction(LocalDateTime.now());
            log.setTypeAction(TypeAction.CONNEXION);
            log.setTableCible(TableCible.UTILISATEUR);
            log.setDescription(
                    "Connexion de l'utilisateur : " +
                            utilisateur.getNom() + " " + utilisateur.getPrenom()
            );

            logsDAO.create(log);
            return utilisateur;
        }

        return null;
    }
}
