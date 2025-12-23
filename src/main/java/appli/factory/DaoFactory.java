package appli.factory;

import appli.dao.logs.LogsUtilisateurDAO;
import appli.dao.logs.TableCibleDAO;
import appli.dao.logs.TypeActionDAO;
import appli.model.logs.TypeAction;

public class DaoFactory {

    private static final String ENVIRONEMENT = "PROD";

    private DaoFactory(){}

    public static LogsUtilisateurDAO getLogsUtilisateurDAO(){
        if(ENVIRONEMENT.equals("PROD")){
            return new LogsUtilisateurDAO();
        }
        return null;
    }

    public static TableCibleDAO getTableCibleDAO(){
        if(ENVIRONEMENT.equals("PROD")){
            return new TableCibleDAO();
        }
        return null;
    }

    public static TypeActionDAO getTypeActionDAO(){
        if(ENVIRONEMENT.equals("PROD")){
            return new TypeActionDAO();
        }
        return null;
    }

}
