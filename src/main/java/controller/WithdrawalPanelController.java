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

public class WithdrawalPanelController implements Initializable {

    @FXML
    private TextField title;

    @FXML
    private TextField amountOfMoney;

    @FXML
    private Button withdrawalButton;

    private Map<String, Customer> customerList;
    private String session;

    @FXML
    void withdrawal(ActionEvent event) throws IOException {
        if (title.getText().isEmpty() || amountOfMoney.getText().isEmpty()) {
            DialogsUtils.errorAlert("Błąd!", "Wszystkie pola muszą być uzupełnione.");
            return;
        }
        if (amountOfMoney.getText().contains(".")) {
            if (amountOfMoney.getText().substring(amountOfMoney.getText().indexOf(".")).length() > 3) {
                DialogsUtils.errorAlert("Błąd!", "Nie właściwy format liczby.");
                return;
            }
        }
        try {
            customerList.get(session).doWithdrawal(title.getText(), amountOfMoney.getText());
        } catch (NumberFormatException exception) {
            DialogsUtils.errorAlert("Błąd!", "Kwota została odzielona: \",\" zamiast \".\".");
        } finally {
            MAIN_CONTROLLER.setCenter("/fxml/CustomerPanelView.fxml");
//            CUSTOMER_PANEL_CONTROLLER.logIn();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerList = MAIN_CONTROLLER.getCustomerList().getCustomerList();
        session = MAIN_CONTROLLER.getSession();
    }
}
