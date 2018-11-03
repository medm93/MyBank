package controller;

import dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Customer;
import modelFX.CustomerModel;
import utils.DBManager;
import utils.DialogsUtils;
import utils.exception.ApplicationException;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.MainPanelController.MAIN_CONTROLLER;
import static utils.ValidatorManager.validationOfRegistrationData;

public class RegistrationPanelController implements Initializable {

    private static final String WELCOME_PANEL_VIEW_FXML = "/fxml/WelcomePanelView.fxml";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @FXML
    private TextField firstNameField;

    @FXML
    private Label firstNameError;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label lastNameError;

    @FXML
    private TextField emailField;

    @FXML
    private Label emailError;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordError;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label confirmPasswordError;

    @FXML
    private Button registrationButton;

    private CustomerModel customerModel;

    @FXML
    void back(ActionEvent event) {
        MAIN_CONTROLLER.setCenter(WELCOME_PANEL_VIEW_FXML);
    }

    @FXML
    void registration(ActionEvent event) {
        Customer customer = new Customer(
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                passwordField.getText()
        );

        try {
            if (validationOfRegistrationData(this, customer)) {
                return;
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        try {
            customerDao.createOrUpdate(customer.encryptPassword());
        } catch (ApplicationException e) {
            DialogsUtils.errorAlert("Błąd!", e.getMessage());
        }
        DialogsUtils.informationAlert("Rejestracja zakończona", "Twoja rejestracja przebiegła pomyślnie.");
        MAIN_CONTROLLER.setCenter(WELCOME_PANEL_VIEW_FXML);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerModel = new CustomerModel();
        firstNameField.textProperty().addListener(observable -> firstNameError.setText(null));
        lastNameField.textProperty().addListener(observable -> lastNameError.setText(""));
        emailField.textProperty().addListener(observable -> emailError.setText(""));
        passwordField.textProperty().addListener(observable -> passwordError.setText(""));
        confirmPasswordField.textProperty().addListener(observable -> confirmPasswordError.setText(""));
    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public void setFirstNameField(TextField firstNameField) {
        this.firstNameField = firstNameField;
    }

    public Label getFirstNameError() {
        return firstNameError;
    }

    public void setFirstNameError(Label firstNameError) {
        this.firstNameError = firstNameError;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(TextField lastNameField) {
        this.lastNameField = lastNameField;
    }

    public Label getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(Label lastNameError) {
        this.lastNameError = lastNameError;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }

    public Label getEmailError() {
        return emailError;
    }

    public void setEmailError(Label emailError) {
        this.emailError = emailError;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public Label getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(Label passwordError) {
        this.passwordError = passwordError;
    }

    public PasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public void setConfirmPasswordField(PasswordField confirmPasswordField) {
        this.confirmPasswordField = confirmPasswordField;
    }

    public Label getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(Label confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

    public Button getRegistrationButton() {
        return registrationButton;
    }

    public void setRegistrationButton(Button registrationButton) {
        this.registrationButton = registrationButton;
    }
}
