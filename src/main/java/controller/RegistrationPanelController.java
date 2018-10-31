package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static controller.MainController.MAIN_CONTROLLER;

public class RegistrationPanelController {

    private static final String WELCOME_PANEL_VIEW_FXML = "/fxml/WelcomePanelView.fxml";

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registrationButton;

    @FXML
    void back(ActionEvent event) {
        try {
            MAIN_CONTROLLER.setCenter(WELCOME_PANEL_VIEW_FXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registration(ActionEvent event) {

    }

}
