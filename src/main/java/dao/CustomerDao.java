package dao;

import com.j256.ormlite.support.ConnectionSource;

public class CustomerDao extends CommonDao {
    public CustomerDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }
}
