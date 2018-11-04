package modelFX;

import dao.TransactionDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import model.Transaction;
import model.TransactionType;
import utils.DBManager;
import utils.converters.ConverterCustomer;
import utils.converters.ConverterTransaction;
import utils.exception.ApplicationException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransactionModel {

    private ObjectProperty<CustomerFX> customerFXObjectProperty = new SimpleObjectProperty<>();
    private ObservableList<TransactionFX> transactions = FXCollections.observableArrayList();

    public void init(CustomerFX customerFX) {
        TransactionDao transactionDao = new TransactionDao(DBManager.getConnectionSource());
        List<Transaction> transactionList =
                transactionDao.findTransactionByCustomer(ConverterCustomer.convertToCustomer(customerFX));
        transactions.clear();
        transactionList.forEach(transaction -> {
            transactions.add(ConverterTransaction.convertToTransactionFX(transaction));
        });
        DBManager.closeConnectionSource();
    }

    public void saveTransactionInDatabase(Customer customer, String title, TransactionType transactionType,
                                           String amount) throws ApplicationException {
        TransactionDao transactionDao = new TransactionDao(DBManager.getConnectionSource());
        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setDate(new Date());
        transaction.setTitle(title);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transactionDao.createOrUpdate(transaction);
        DBManager.closeConnectionSource();
        init(ConverterCustomer.convertToCustomerFX(customer));
    }

    public CustomerFX getCustomerFXObjectProperty() {
        return customerFXObjectProperty.get();
    }

    public ObjectProperty<CustomerFX> customerFXObjectPropertyProperty() {
        return customerFXObjectProperty;
    }

    public void setCustomerFXObjectProperty(CustomerFX customerFXObjectProperty) {
        this.customerFXObjectProperty.set(customerFXObjectProperty);
    }

    public ObservableList<TransactionFX> getTransactions() {
        return transactions;
    }

    public void setTransactions(ObservableList<TransactionFX> transactions) {
        this.transactions = transactions;
    }


}
