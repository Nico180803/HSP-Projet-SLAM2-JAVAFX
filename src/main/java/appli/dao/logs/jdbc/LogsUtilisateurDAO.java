package appli.dao.logs.jdbc;

import appli.model.logs.LogsUtilisateur;
import appli.model.logs.TableCible;
import appli.model.logs.TypeAction;
import appli.model.principal.Utilisateur;
import appli.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LogsUtilisateurDAO {

    public void create(LogsUtilisateur log){
    }

    public List<LogsUtilisateur> getAll() {

        List<LogsUtilisateur> logs = new ArrayList<>();

        String sql = "SELECT * FROM logs_utilisateur";

        try {
            Connection cnx = DatabaseConnection.getLogsConnexion();
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LogsUtilisateur log = new LogsUtilisateur();

                log.setDateAction(rs.getTimestamp("dateAction").toLocalDateTime());
                log.setDetails(rs.getString("details"));


                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("refUser"));
                log.setUtilisateur(user);

                log.setTypeAction(TypeAction.CONNEXION);
                log.setTableCible(TableCible.UTILISATEUR);

                logs.add(log);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logs;
    }
}