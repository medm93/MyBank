package modelFX;

import dao.CustomerDao;
import model.Customer;
import org.apache.commons.codec.digest.DigestUtils;
import org.sqlite.core.DB;
import utils.DBManager;
import utils.exception.ApplicationException;

public class CustomerModel {

    public void saveCustomerInDatabase(String firstName, String lastName, String email, String password) throws ApplicationException {
        CustomerDao customerDao = new CustomerDao(DBManager.getConnectionSource());
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(DigestUtils.sha512Hex(password));
        customerDao.createOrUpdate(customer);
        DBManager.closeConnectionSource();
    }
}
