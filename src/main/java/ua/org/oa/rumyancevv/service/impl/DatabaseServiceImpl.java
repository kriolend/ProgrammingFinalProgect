package ua.org.oa.rumyancevv.service.impl;

import com.ibatis.common.jdbc.ScriptRunner;
import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;
import ua.org.oa.rumyancevv.jdbc.provider.ConnectionProvider;
import ua.org.oa.rumyancevv.service.DatabaseService;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseServiceImpl implements DatabaseService {

    @Override
    public void dataBaseInit(String initSqlPath) throws ConnectionProviderException, SQLException, IOException {
        Connection connection = ConnectionProvider.getConnection("/");
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            ScriptRunner scriptRunner = new ScriptRunner(connection, false, true);
            BufferedReader reader = new BufferedReader(new FileReader(initSqlPath));
            scriptRunner.runScript(reader);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }
}
