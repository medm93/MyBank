package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import models.CustomerList;
import utils.Dialogs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInButon;

    private CustomerList customerList;
    private MainController mainController;

    public void initialize(URL location, ResourceBundle resources) {
        customerList = new CustomerList();
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        Window owner = signInButon.getScene().getWindow();
        if (loginTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            Dialogs.loginAlert(owner, "Błąd formularza!", "Wszystkie pola muszą być uzupełnione.");
            return;
        }

        if (customerList.getCustomerList().containsKey(loginTextField.getText())) {
            if (customerList.getCustomerList().get(loginTextField.getText()).getPassword().equals(passwordTextField.getText())) {
                mainController.setCenter("/fxml/Test.fxml");
            } else {
                Dialogs.loginAlert(owner, "Błąd formularza!", "Zły login lub hasło");
            }
        } else {
            Dialogs.loginAlert(owner, "Błąd formularza!", "Zły login lub hasło");
        }
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}