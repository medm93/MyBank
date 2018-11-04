package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Customer;
import model.TransactionType;
import modelFX.CustomerFX;
import modelFX.CustomerModel;
import modelFX.TransactionModel;
import utils.ValidatorManager;
import utils.exception.ApplicationException;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class TransactionPanelController implements Initializable {

    public static TransactionPanelController TRANSACTION_PANEL_CONTROLLER;

    @FXML
    private TextField title;

    @FXML
    private Label titleError;

    @FXML
    private ComboBox<TransactionType> transactionType;

    @FXML
    private Label transactionTypeError;

    @FXML
    private TextField amount;

    @FXML
    private Label amountError;

    @FXML
    private Button send;

    private CustomerFX customerFX;

    private CustomerModel customerModel;
    private TransactionModel transactionModel;

    @FXML
    void send (ActionEvent event) {

//        if (ValidatorManager.validationOfTransactionData(this)) return;
//        try {
//            customerModel.saveCustomerInDatabase(customerFX);
////            transactionModel.saveTransactionInDatabase(
////                    customer,
////                    title.getText(),
////                    transactionType.getSelectionModel().getSelectedItem(),
////                    new BigDecimal(amount.getText())
////            );
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//
//        try {
//        if (transactionType.getSelectionModel().getSelectedItem().equals(TransactionType.PAYMENT)) {
//            System.out.println("Wpłata");
//            customerModel.updateAccountBalanceInDatabase(customerFX, new BigDecimal(amount.getText()));
//        } else {
//            System.out.println("Wypłata");
//            customerModel.updateAccountBalanceInDatabase(customerFX, new BigDecimal("-" + amount.getText()));
//        }
//        } catch (ApplicationException | SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Mamy transakcje");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TRANSACTION_PANEL_CONTROLLER = this;
        customerModel = new CustomerModel();
        transactionModel = new TransactionModel();
        transactionType.getItems().setAll(TransactionType.values());
        transactionType.getSelectionModel().select(0);
        send.disableProperty().bind(title.textProperty().isEmpty().or(amount.textProperty().isEmpty()));
        title.textProperty().addListener(observable -> titleError.setText(null));
        amount.textProperty().addListener(observable -> amountError.setText(null));
    }

    public TextField getTitle() {
        return title;
    }

    public void setTitle(TextField title) {
        this.title = title;
    }

    public Label getTitleError() {
        return titleError;
    }

    public void setTitleError(Label titleError) {
        this.titleError = titleError;
    }

    public ComboBox<TransactionType> getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(ComboBox<TransactionType> transactionType) {
        this.transactionType = transactionType;
    }

    public Label getTransactionTypeError() {
        return transactionTypeError;
    }

    public void setTransactionTypeError(Label transactionTypeError) {
        this.transactionTypeError = transactionTypeError;
    }

    public TextField getAmount() {
        return amount;
    }

    public void setAmount(TextField amount) {
        this.amount = amount;
    }

    public Label getAmountError() {
        return amountError;
    }

    public void setAmountError(Label amountError) {
        this.amountError = amountError;
    }

    public Button getSend() {
        return send;
    }

    public void setSend(Button send) {
        this.send = send;
    }

    public CustomerFX getCustomerFX() {
        return customerFX;
    }

    public void setCustomerFX(CustomerFX customerFX) {
        this.customerFX = customerFX;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public TransactionModel getTransactionModel() {
        return transactionModel;
    }

    public void setTransactionModel(TransactionModel transactionModel) {
        this.transactionModel = transactionModel;
    }
}