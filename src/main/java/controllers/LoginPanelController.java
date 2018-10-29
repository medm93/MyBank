package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.CustomerList;
import utils.Dialogs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controllers.MainController.MAIN_CONTROLLER;
import static controllers.UserPanelController.USER_PANEL_CONTROLLER;

public class LoginPanelController implements Initializable {

    public static LoginPanelController LOGIN_CONTROLLER;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInButton;


    private CustomerList customerList;
    private MainController mainController;

    public void initialize(URL location, ResourceBundle resources) {
        LOGIN_CONTROLLER = this;
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        if (loginTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            Dialogs.errorAlert( "Błąd formularza!", "Wszystkie pola muszą być uzupełnione.");
            return;
        }

        if (customerList.getCustomerList().containsKey(loginTextField.getText())) {
            if (customerList.getCustomerList().get(loginTextField.getText()).getPassword().equals(passwordTextField.getText())) {
                MAIN_CONTROLLER.setSession(loginTextField.getText());
                MAIN_CONTROLLER.setCenter("/fxml/UserPanelView.fxml");
//                USER_PANEL_CONTROLLER.setMainController(mainController);
                USER_PANEL_CONTROLLER.logIn();
            } else {
                Dialogs.errorAlert( "Błąd formularza!", "Zły login lub hasło");
            }
        } else {
            Dialogs.errorAlert("Błąd formularza!", "Zły login lub hasło");
        }
    }

    public CustomerList getCustomerList() {
        return customerList;
    }

    public void setCustomerList(CustomerList customerList) {
        this.customerList = customerList;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}