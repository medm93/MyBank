package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;
import utils.DialogsUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import static controller.MainPanelController.MAIN_CONTROLLER;

public class PaymentPanelController implements Initializable {

    private final static String USER_PANEL_VIEW = "/fxml/CustomerPanelView.fxml";

    @FXML
    private TextField title;

    @FXML
    private TextField amountOfMoney;

    @FXML
    private Button paymentButton;

    @FXML
    void payment(ActionEvent event) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
