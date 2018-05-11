package db.pool;

import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("SameParameterValue")
public interface DataSourceMeh {

    void setDriver(String driverClassName) throws ClassNotFoundException;

    void setUsername(String username);

    void setPassword(String password);

    void setServer(String server);

    Connection getConnectionFromPool() throws SQLException;

    void returnConnectionToPool(Connection con);

    void close();
}
