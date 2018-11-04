package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelFX.CustomerFX;
import modelFX.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.MainPanelController.MAIN_CONTROLLER;


public class LoginPanelController implements Initializable {

    public static LoginPanelController LOGIN_PANEL_CONTROLLER;
    private static final String WELCOME_PANEL_VIEW_FXML = "/fxml/WelcomePanelView.fxml";
    private static final String CUSTOMER_PANEL_VIEW_FXML = "/fxml/CustomerPanelView.fxml";

    @FXML
    private TextField emailOrLogin;

    @FXML
    private PasswordField password;

    @FXML
    private Label error;

    private CustomerModel customerModel;
    private CustomerFX customerFX;

    public void initialize(URL location, ResourceBundle resources) {
        LOGIN_PANEL_CONTROLLER = this;
        customerModel = new CustomerModel();
    }

    @FXML
    void back(ActionEvent event) {
        MAIN_CONTROLLER.setCenter(WELCOME_PANEL_VIEW_FXML);
    }

    @FXML
    void login(ActionEvent event) {
        try {
            if (customerModel.findCustomerInDatabase(emailOrLogin.getText(), password.getText())) {
                customerModel.initCustomer(emailOrLogin.getText(), password.getText());
                MAIN_CONTROLLER.setCenter(CUSTOMER_PANEL_VIEW_FXML);
            } else {
                error.setText("Opss! Coś poszło nie tak");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public TextField getEmailOrLogin() {
        return emailOrLogin;
    }

    public void setEmailOrLogin(TextField emailOrLogin) {
        this.emailOrLogin = emailOrLogin;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public CustomerFX getCustomerFX() {
        return customerFX;
    }

    public void setCustomerFX(CustomerFX customerFX) {
        this.customerFX = customerFX;
    }
}