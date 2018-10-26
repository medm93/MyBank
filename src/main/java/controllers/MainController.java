package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import models.CustomerList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public static MainController MAIN_CONTROLLER;

    @FXML
    private BorderPane borderPane;

    @FXML
    private LoginController loginController;

    @FXML
    private UserPanelController userPanelController;

    private CustomerList customerList;
    private String status;

    public void initialize(URL location, ResourceBundle resources) {
        MAIN_CONTROLLER = this;
        customerList = new CustomerList();
        loginController.setMainController(this);
        loginController.setCustomerList(customerList);

    }

    public void setCenter(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = fxmlLoader.load();
        borderPane.setCenter(parent);
        System.out.println(fxmlLoader.getController());
    }

    public FXMLLoader getFXMLLoader(String fxmlPath) {
        return new FXMLLoader(this.getClass().getResource(fxmlPath));
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }

    public void setCustomerList(CustomerList customerList) {
        this.customerList = customerList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public UserPanelController getUserPanelController() {
        return userPanelController;
    }

    public void setUserPanelController(UserPanelController userPanelController) {
        this.userPanelController = userPanelController;
    }
}
