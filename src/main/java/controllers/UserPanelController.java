package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import models.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import static controllers.LoginPanelController.LOGIN_CONTROLLER;
import static controllers.MainController.MAIN_CONTROLLER;

public class UserPanelController implements Initializable {

    public static UserPanelController USER_PANEL_CONTROLLER;

    @FXML
    private Label name;

    @FXML
    private Label login;

    @FXML
    private Button logoutButton;

    @FXML
    private Label accountBalance;

    @FXML
    private ToggleButton paymentButton;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private ToggleButton withdrawalButton;

    @FXML
    private ToggleButton historyButton;

    @FXML
    private VBox vBox;

    private Map<String, Customer> customerList;
    private String session;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        USER_PANEL_CONTROLLER = this;
        customerList = MAIN_CONTROLLER.getCustomerList().getCustomerList();
        session = MAIN_CONTROLLER.getSession();
    }

    public void logIn() {
        login.setText(session);
        String firstName = customerList.get(session).getFirstName();
        String lastName = customerList.get(session).getLastName();
        name.setText(firstName + " " + lastName);
        accountBalance.setText(customerList.get(session).getAccountBalance() + " PLN");
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        MAIN_CONTROLLER.setCenter("/fxml/LoginPanelView.fxml");
        LOGIN_CONTROLLER.setCustomerList(MAIN_CONTROLLER.getCustomerList());
    }

    @FXML
    void paymentOnAction(ActionEvent event) throws IOException {
        clearVBox();
        if(paymentButton.isSelected()) {
            setVBox("/fxml/PaymentPanelView.fxml");
        }
    }

    @FXML
    void withdrawalOnAction(ActionEvent event) throws IOException {
        clearVBox();
        if(withdrawalButton.isSelected()) {
            clearVBox();
            setVBox("/fxml/WithdrawalPanelView.fxml");
        }
    }

    @FXML
    void historyOnAction(ActionEvent event) throws IOException {
        clearVBox();
        if(historyButton.isSelected()) {
            clearVBox();
            setVBox("/fxml/HistoryPanelView1.fxml");
        }
    }

    public void setVBox(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = fxmlLoader.load();
        vBox.getChildren().add(parent);
    }

    private void clearVBox() {
        vBox.getChildren().clear();
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public Label getLogin() {
        return login;
    }

    public void setLogin(Label login) {
        this.login = login;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(Button logoutButton) {
        this.logoutButton = logoutButton;
    }

    public Label getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Label accountBalance) {
        this.accountBalance = accountBalance;
    }

    public ToggleButton getPaymentButton() {
        return paymentButton;
    }

    public void setPaymentButton(ToggleButton paymentButton) {
        this.paymentButton = paymentButton;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    public ToggleButton getWithdrawalButton() {
        return withdrawalButton;
    }

    public void setWithdrawalButton(ToggleButton withdrawalButton) {
        this.withdrawalButton = withdrawalButton;
    }

    public ToggleButton getHistoryButton() {
        return historyButton;
    }

    public void setHistoryButton(ToggleButton historyButton) {
        this.historyButton = historyButton;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Map<String, Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(Map<String, Customer> customerList) {
        this.customerList = customerList;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}