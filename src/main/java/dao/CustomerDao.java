package dao;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import model.Customer;

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

    public boolean isMail(String email) throws SQLException {
        QueryBuilder<Customer, Integer> queryBuilder = getQueryBuilder(Customer.class);
        queryBuilder.where().eq("EMAIL", email);
        PreparedQuery<Customer> preparedQuery = queryBuilder.prepare();
        Customer results = getDao(Customer.class).queryForFirst(preparedQuery);
        return results != null;
    }

    public Customer findCustomer(String emailOrLogin, String password) throws SQLException {
        QueryBuilder<Customer, Integer> queryBuilder = getQueryBuilder(Customer.class);
//        queryBuilder.selectColumns("ID");
//        queryBuilder.limit(1L);
        queryBuilder.where().eq("EMAIL", emailOrLogin).and().eq("PASSWORD", password);
        PreparedQuery<Customer> preparedQuery = queryBuilder.prepare();
//        Customer customer = getDao(Customer.class).queryForFirst(preparedQuery);
        Customer customer = getDao(Customer.class).queryForFirst(preparedQuery);
        return customer;
    }
}
