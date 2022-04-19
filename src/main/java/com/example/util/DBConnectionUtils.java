package com.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionUtils {

    private static Integer commitRecurrence;
    private static Integer queryExecuted = 0;

    private DBConnectionUtils(){}

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = loadDatabaseProperties();

        String url = properties.getProperty("database.url");
        String user = properties.getProperty("database.user");
        String passwd = properties.getProperty("database.password");

        commitRecurrence = Integer.valueOf((String)properties.get("database.commit.recurrence"));

        Connection connection = DriverManager.getConnection(url, user, passwd);
        connection.setAutoCommit(false);

        return connection;
    }

    private static Properties loadDatabaseProperties() throws IOException {
        Properties props = new Properties();
        Path myPath = Paths.get("src/main/resources/database.properties");

        BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);

        props.load(bf);
        return props;
    }

    public static boolean shouldCommitBeExecuted(){
        return ++queryExecuted%commitRecurrence == 0; // tricky :)
    }
}
