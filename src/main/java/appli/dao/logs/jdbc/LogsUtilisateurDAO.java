package appli.dao.logs.jdbc;

import appli.dao.principal.jdbc.UtilisateurDAO;
import appli.model.logs.LogsUtilisateur;
import appli.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LogsUtilisateurDAO {

    public List<LogsUtilisateur> getAll() {

        List<LogsUtilisateur> logs = new ArrayList<>();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        TableCibleDAO tableCibleDAO = new TableCibleDAO();
        TypeActionDAO typeActionDAO = new TypeActionDAO();

        String sql = "SELECT * FROM logs_utilisateur";

        try {
            Connection cnx = DatabaseConnection.getLogsConnexion();
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LogsUtilisateur log = new LogsUtilisateur(
                        rs.getInt("id"),
                        utilisateurDAO.getById(rs.getInt("refUser")),
                        typeActionDAO.getById(rs.getInt("refTypeAction")),
                        tableCibleDAO.getById(rs.getInt("refTableCible")),
                        rs.getInt("refLigne"),
                        rs.getTimestamp("dateAction").toLocalDateTime(),
                        rs.getString("details")
                );

                logs.add(log);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logs;
    }
}