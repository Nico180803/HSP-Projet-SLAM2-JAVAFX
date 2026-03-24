package appli.service;

import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.Chambre;

public class ChambreService {

    public void update(Chambre chambre) {
        GenericDAO<Chambre> chambreDAO = DaoFactory.getChambreDAO();
        assert chambreDAO != null;
        chambreDAO.update(chambre);
    }
}
