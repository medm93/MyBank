package modelFX;

import dao.CustomerDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Customer;
import org.apache.commons.codec.digest.DigestUtils;
import utils.DBManager;
import utils.converters.ConverterCustomer;
import utils.exception.ApplicationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

public class CustomerModel {

    private ObjectProperty<CustomerFX> customerFXObjectProperty = new SimpleObjectProperty<>(new CustomerFX());
    private StringProperty accountBalanceProperty = new SimpleStringProperty();

    private void initAccountBalance(Customer customer) {
        accountBalanceProperty = ConverterCustomer.convertToCustomerFX(customer).accountBalanceProperty();
    }

    public void initCustomer(Customer c) throws SQLException {
        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        Customer customer = customerDao.queryForCustomer(c);
        customerFXObjectProperty.setValue(ConverterCustomer.convertToCustomerFX(customer));
        DBManager.closeConnectionSource();
    }

    public void initCustomer(String emailOrLogin, String password) throws SQLException {
        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        Customer customer = customerDao.findCustomer(emailOrLogin, DigestUtils.sha512Hex(password));
        System.out.println(customer);
        customerFXObjectProperty.setValue(ConverterCustomer.convertToCustomerFX(customer));
        DBManager.closeConnectionSource();
    }

    public boolean findCustomerInDatabase(String emailOrLogin, String password) throws SQLException {
        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        Customer customer = customerDao.findCustomer(emailOrLogin, DigestUtils.sha512Hex(password));
        DBManager.closeConnectionSource();
        return customer != null;
    }

    public void saveCustomerInDatabase(CustomerFX customerFX) throws ApplicationException {
        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        Customer customer = ConverterCustomer.convertToCustomer(customerFX);
        customerDao.createOrUpdate(customer);
        DBManager.closeConnectionSource();
    }

    public void updateAccountBalanceInDatabase() throws ApplicationException, SQLException {
        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        Customer customer = ConverterCustomer.convertToCustomer(getCustomerFXObjectProperty());
        customerDao.updateAccountBalance(customer, customer.getAccountBalance());
        DBManager.closeConnectionSource();
//
    }



    private void init(Customer customer) {
        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        customerFXObjectProperty.setValue(ConverterCustomer.convertToCustomerFX(customer));
        DBManager.closeConnectionSource();
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

    public String getAccountBalanceProperty() {
        return accountBalanceProperty.get();
    }

    public StringProperty accountBalancePropertyProperty() {
        return accountBalanceProperty;
    }

    public void setAccountBalanceProperty(String accountBalanceProperty) {
        this.accountBalanceProperty.set(accountBalanceProperty);
    }
}
