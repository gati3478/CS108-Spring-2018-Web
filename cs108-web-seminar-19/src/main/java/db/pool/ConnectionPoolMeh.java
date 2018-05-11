package db.pool;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is by no means a good way to implement a *good* Connection Pool.
 * Use either Tomcat or MySQL or DBCP Connection Pool instead.
 */
@SuppressWarnings({"unused", "FieldCanBeLocal"})
public final class ConnectionPoolMeh implements DataSourceMeh {

    private String account;
    private String password;
    private String server;

    // Connection pool representation
    private BlockingQueue<Connection> pool;
    private final AtomicInteger connectionCount = new AtomicInteger();
    private static int CONNECTION_LIMIT_DEFAULT = 24;

    public ConnectionPoolMeh() throws ClassNotFoundException {
        // Connection pool
        pool = new LinkedBlockingQueue<>(CONNECTION_LIMIT_DEFAULT);
    }

    @Override
    public void setDriver(String driverClassName) throws ClassNotFoundException {
        // Driver registration (static blocks)
        Class.forName(driverClassName);
    }

    @Override
    public void setUsername(String username) {
        this.account = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public Connection getConnectionFromPool() throws SQLException {
        Connection connection = null;
        try {
            connection = pool.poll(10, TimeUnit.MILLISECONDS);

            if (connection == null) {
                synchronized (connectionCount) {
                    if (connectionCount.get() < pool.size() + pool.remainingCapacity()) {
                        connection = createConnection();
                        pool.offer(connection);
                        connectionCount.incrementAndGet();
                    }
                }
                connection = pool.take();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return connection;
    }

    @Override
    public void returnConnectionToPool(Connection con) {
        // Try to add back
        boolean added = pool.offer(con);

        // Close if pool is already full
        if (!added) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() {
        boolean error = false;

        for (Connection con : pool) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                error = true;
            }
        }

        if (error) {
            // TODO: Needs better handling
            AbandonedConnectionCleanupThread.checkedShutdown();
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://"
                + server, account, password);
    }

}
