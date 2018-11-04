package dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import model.BaseModel;
import model.Customer;
import model.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TransactionDao extends CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);

    public TransactionDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public List<Transaction> findTransactionByCustomer(Customer customer) {
        QueryBuilder<Transaction, Integer> queryBuilder = getQueryBuilder(Transaction.class);
        try {
            queryBuilder.where().eq("CUSTOMER_ID", customer);
            PreparedQuery<Transaction> preparedQuery = queryBuilder.prepare();
            return getDao(Transaction.class).query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
