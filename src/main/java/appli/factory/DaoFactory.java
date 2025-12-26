package appli.factory;

import appli.dao.logs.LogsUtilisateurDAO;
import appli.dao.logs.TableCibleDAO;
import appli.dao.logs.TypeActionDAO;
import appli.dao.principal.*;

public class DaoFactory {

    private static final String ENVIRONEMENT = "JDBC";

    private DaoFactory(){}

    public static LogsUtilisateurDAO getLogsUtilisateurDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new LogsUtilisateurDAO();
        }
        return null;
    }

    public static TableCibleDAO getTableCibleDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new TableCibleDAO();
        }
        return null;
    }

    public static TypeActionDAO getTypeActionDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new TypeActionDAO();
        }
        return null;
    }


    public static ChambreDAO getChambreDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new ChambreDAO();
        }
        return null;
    }

    public static DemandeProduitDAO getDemandeProduitDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new DemandeProduitDAO();
        }
        return null;
    }

    public static DossierPriseEnChargeDAO getDossierPriseEnChargeDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new DossierPriseEnChargeDAO();
        }
        return null;
    }

    public static FichePatientDAO getFichePatientDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new FichePatientDAO();
        }
        return null;
    }

    public static FournisseurDAO getFournisseurDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new FournisseurDAO();
        }
        return null;
    }

    public static HospitalisationDAO getHospitalisationDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new HospitalisationDAO();
        }
        return null;
    }

    public static OrdonnanceDAO getOrdonnanceDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new OrdonnanceDAO();
        }
        return null;
    }

    public static ProduitDAO getProduitDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new ProduitDAO();
        }
        return null;
    }

    public static ProduitFournisseurDAO getProduitFournisseurDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new ProduitFournisseurDAO();
        }
        return null;
    }

    public static UtilisateurDAO getUtilisateurDAO(){
        if(ENVIRONEMENT.equals("JDBC")){
            return new UtilisateurDAO();
        }
        return null;
    }

}
