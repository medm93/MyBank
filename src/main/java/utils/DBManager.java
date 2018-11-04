package utils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Customer;
import model.Transaction;

import java.io.IOException;
import java.sql.SQLException;

public class DBManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBManager.class);
    private static final String JDBC_DRIVER_SQLITE = "jdbc:sqlite:database.db";
    private static ConnectionSource connectionSource;

    //only development
    public static void initializeDatabase() {
        createConnectionSource();
//        dropTable();
        createTable();
        closeConnectionSource();
    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_SQLITE);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    //only development
    private static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Customer.class);
            TableUtils.createTableIfNotExists(connectionSource, Transaction.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    //only development
    private static void dropTable() {
        try {
//            TableUtils.dropTable(connectionSource, Customer.class, true);
            TableUtils.dropTable(connectionSource, Transaction.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
