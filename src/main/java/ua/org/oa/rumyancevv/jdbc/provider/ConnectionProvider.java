package ua.org.oa.rumyancevv.jdbc.provider;


import org.apache.commons.dbcp.BasicDataSource;
import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.exceptions.PropertiesException;

import java.sql.Connection;
import java.sql.SQLException;

import static ua.org.oa.rumyancevv.properties.PropertiesUtils.getProperties;

public class ConnectionProvider {
    private final BasicDataSource dataSource;
    public ConnectionProvider(String schema) throws PropertiesException {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(getProperties().getProperty("JDBC_DRIVER_CLASS"));
        ds.setUsername(getProperties().getProperty("JDBC_LOGIN"));
        ds.setPassword(getProperties().getProperty("JDBC_PASSWORD"));
        ds.setUrl(getProperties().getProperty("JDBC_URL")  + schema);
        ds.setMaxOpenPreparedStatements(2000);
        ds.setDefaultAutoCommit(false);
        ds.setMaxActive(200);
        ds.setDefaultTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        dataSource = ds;
    }

    public static Connection getConnection(String schema) throws SQLException, ConnectionProviderException {
        Connection connection = null;
        try {
            connection = new ConnectionProvider(schema).newConnection(schema);
        } catch (PropertiesException e) {
            throw new ConnectionProviderException("Connection provider error");
        }
        return connection;
    }

    public Connection newConnection(String schema) throws SQLException {
        return dataSource.getConnection();
    }
}
