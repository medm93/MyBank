package controller;

import dao.CustomerDao;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Customer;
import utils.DBManager;
import utils.Dialogs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.MainController.MAIN_CONTROLLER;

public class RegistrationPanelController implements Initializable {

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
        if (firstNameField.getText().isEmpty()) {
            Dialogs.errorAlert("Błąd formularza", "Proszę wprowdzić imię.");
            return;
        }
        if (lastNameField.getText().isEmpty()) {
            Dialogs.errorAlert("Błąd formularza", "Proszę wprowdzić nazwisko.");
            return;
        }
        if (emailField.getText().isEmpty()) {
            Dialogs.errorAlert("Błąd formularza", "Proszę wprowdzić e-mail.");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            Dialogs.errorAlert("Błąd formularza", "Proszę wprowdzić hasło.");
            return;
        }
        if (confirmPasswordField.getText().isEmpty()) {
            Dialogs.errorAlert("Błąd formularza", "Proszę wprowdzić powtórnie hasło.");
            return;
        }
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            Dialogs.errorAlert("Błąd formularza", "Hasła są różne! Wprowadź identyczne hasła.");
            return;
        }
        Customer customer = new Customer(
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                passwordField.getText()
        );
        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        customerDao.createOrUpdate(customer);
        Dialogs.informationAlert("Rejestracja zakończona", "Twoja rejestracja przebiegła pomyślnie.");
        try {
            MAIN_CONTROLLER.setCenter(WELCOME_PANEL_VIEW_FXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
