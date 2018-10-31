package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static controller.MainController.MAIN_CONTROLLER;

public class WelcomePanelController {

    private static final String LOGIN_PANEL_VIEW_FXML = "/fxml/LoginPanelView.fxml";
    private static final String REGISTRATION_PANEL_VIEW_FXML = "/fxml/RegistrationPanelView.fxml";

    @FXML
    private Button registrationButton;

    @FXML
    private Button loginButton;

    @FXML
    void login(ActionEvent event) {
        try {
            MAIN_CONTROLLER.setCenter(LOGIN_PANEL_VIEW_FXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registration(ActionEvent event) {
        try {
            MAIN_CONTROLLER.setCenter(REGISTRATION_PANEL_VIEW_FXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}