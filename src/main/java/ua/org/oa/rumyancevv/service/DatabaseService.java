package ua.org.oa.rumyancevv.service;

import ua.org.oa.rumyancevv.exceptions.ConnectionProviderException;

import java.io.IOException;
import java.sql.SQLException;

public interface DatabaseService {
    void dataBaseInit(String initSqlPath) throws ConnectionProviderException, SQLException, IOException;
}
