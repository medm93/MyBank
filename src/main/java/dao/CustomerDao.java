package dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import model.BaseModel;
import model.Customer;
import utils.exception.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class CustomerDao extends CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);

    public CustomerDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public Integer find(String emailOrLogin) throws SQLException {

        GenericRawResults<String[]> queryRaw = getDao(Customer.class).queryRaw(
                "SELECT id FROM customers WHERE 'login' = '" + emailOrLogin + "'"
        );
        String[] result = queryRaw.getFirstResult();
        return Integer.valueOf(result[0]);
    }


    public List<String[]> findCustomer1(String emailOrLogin, String password) {
        try {
            GenericRawResults<String[]> customer = getDao(Customer.class).queryRaw(
                    "SELECT * FROM customers WHERE EMAIL = " + emailOrLogin + " AND PASSWORD = " + password +
                            " OR LOGIN = " + emailOrLogin + " AND PASSWORD = " + password
            );
            return customer.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean queryForEmail(String email) throws ApplicationException {
        QueryBuilder<Customer, Integer> queryBuilder = getQueryBuilder(Customer.class);
        try {
            queryBuilder.where().eq("EMAIL", email);
            PreparedQuery<Customer> preparedQuery = queryBuilder.prepare();
            Customer results = getDao(Customer.class).queryForFirst(preparedQuery);
            return results != null;
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException("Błąd");
        }
    }

    public Customer queryForCustomer(Customer customer) throws SQLException {
        QueryBuilder<Customer, Integer> queryBuilder = getQueryBuilder(Customer.class);
        queryBuilder.where().idEq(getDao(Customer.class), customer);
        PreparedQuery<Customer> preparedQuery = queryBuilder.prepare();
        return getDao(Customer.class).queryForFirst(preparedQuery);
    }

    public Customer findCustomer(String emailOrLogin, String password) throws SQLException {
        QueryBuilder<Customer, Integer> queryBuilder = getQueryBuilder(Customer.class);
        queryBuilder.where().eq("EMAIL", emailOrLogin).and().eq("PASSWORD", password);
        PreparedQuery<Customer> preparedQuery = queryBuilder.prepare();
        return getDao(Customer.class).queryForFirst(preparedQuery);
    }

    public void updateAccountBalance(Customer customer, String newAccountBalance) throws SQLException {
        UpdateBuilder<Customer, Integer> updateBuilder = getUpdateBuilder(Customer.class);
        updateBuilder.updateColumnValue("ACCOUNT_BALANCE", newAccountBalance);
        updateBuilder.where().eq("ID", customer.getId());
        PreparedUpdate<Customer> preparedUpdate = updateBuilder.prepare();
        getDao(Customer.class).update(preparedUpdate);
    }
}
