module appli.hsp_slam_jfx {
    requires javafx.controls;
    requires javafx.fxml;


    exports appli.main;
    opens appli.main to javafx.fxml;
    opens appli.controller.main to javafx.fxml;
    opens appli.controller.management to javafx.fxml;
    opens appli.controller.patient to javafx.fxml;
    opens appli.controller.stock to javafx.fxml;
    opens appli.controller.medecin to javafx.fxml;
    opens appli.controller.shared to javafx.fxml;
    exports appli;
    opens appli to javafx.fxml;




}