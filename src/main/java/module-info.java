module appli.hsp_slam_jfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens appli.hsp_slam_jfx to javafx.fxml;
    exports appli.hsp_slam_jfx;
    exports appli.hsp_slam_jfx.main;
    opens appli.hsp_slam_jfx.main to javafx.fxml;
    exports appli.hsp_slam_jfx.controller;
    opens appli.hsp_slam_jfx.controller to javafx.fxml;
}