package ru.innopolis.db.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionDB.class);
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    public static final String POSTGRES_URL_USERS = "jdbc:postgresql://localhost:5432/budget";
    public static final String POSTGRES_USER = "postgres";
    public static final String POSTGRES_PASSWORD = "postgres";

    public static Connection connectDB(){
        Connection connection = null;
        try {
            LOGGER.debug("getting connection");
            Class.forName(POSTGRES_DRIVER);
            connection = DriverManager.getConnection(POSTGRES_URL_USERS, POSTGRES_USER, POSTGRES_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("error during getting connection", e);
        }
        return connection;
    }
}
