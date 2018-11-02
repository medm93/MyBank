package controller;

import dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Customer;
import model.CustomerList;
import org.apache.commons.codec.digest.DigestUtils;
import utils.DBManager;
import utils.Dialogs;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.MainPanelController.MAIN_CONTROLLER;
import static controller.CustomerPanelController.CUSTOMER_PANEL_CONTROLLER;

public class LoginPanelController implements Initializable {

    public static LoginPanelController LOGIN_CONTROLLER;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInButton;


    private CustomerList customerList;
    private MainPanelController mainPanelController;

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
                MAIN_CONTROLLER.setCenter("/fxml/CustomerPanelView.fxml");
//                CUSTOMER_PANEL_CONTROLLER.logIn();
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

    public MainPanelController getMainPanelController() {
        return mainPanelController;
    }

    public void setMainPanelController(MainPanelController mainPanelController) {
        this.mainPanelController = mainPanelController;
    }

    ////////////////////////////////////////////////////
    private static final String WELCOME_PANEL_VIEW_FXML = "/fxml/WelcomePanelView.fxml";
    private static final String CUSTOMER_PANEL_VIEW_FXML = "/fxml/CustomerPanelView.fxml";

    @FXML
    private TextField emailOrLoginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void back(ActionEvent event) {
        try {
            MAIN_CONTROLLER.setCenter(WELCOME_PANEL_VIEW_FXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void login(ActionEvent event) {
        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        try {
            Customer customer = customerDao.findCustomer(
                    emailOrLoginField.getText(),
                    DigestUtils.sha512Hex(passwordField.getText())
            );
            if (customer != null) {
                MAIN_CONTROLLER.setCenter(CUSTOMER_PANEL_VIEW_FXML);
                CUSTOMER_PANEL_CONTROLLER.setCustomer(customer);
                CUSTOMER_PANEL_CONTROLLER.loadCustomerData();
                System.out.println("zaloguj");
            } else {
                System.out.println("error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            System.out.println("ID: " + customerDao.find(emailOrLoginField.getText()));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}