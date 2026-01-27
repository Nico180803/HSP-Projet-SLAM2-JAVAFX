package appli.controller.main;

import static java.lang.System.load;

public class LoginController {
    public void onPageInscriptionButtonClick() {
        load("/appli/patient/TableFichePatient.fxml");
        System.out.println("Arrivée sur la page d'inscription");
    }
    public void onMdpOublieButtonClick(){
        load("/appli/mdp/TableFicheMdp.fxml");
        System.out.println("Arrivée sur la page d'oubli de mot de passe");
    }
    public void onConnexionButtonClick(){
        load("/appli/shared/Overlay.fxml");
        System.out.println("Arrivé sur l'overlay");
    }
}
