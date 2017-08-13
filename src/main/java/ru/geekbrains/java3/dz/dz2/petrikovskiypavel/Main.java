package ru.geekbrains.java3.dz.dz2.petrikovskiypavel;

import java.sql.*;

public class Main {


    public static void main(String[] args) {
        new DBUse().go();
    }
}

class DBUse {
    private final String DBURL = "jdbc:postgresql://localhost:5432/main";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";
    private String dbName = "main";
    private String dbTable = "goods";
    private String dbString = "CREATE TABLE if not exists " + dbTable +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            " ProdID INTEGER AUTOINCREMENT NOT NULL," +
            " Title TEXT NOT NULL," +
            " Cost INTEGER NOT NULL)";

    void go() {
        try {
            Connection connection = open(DBURL,USER,PASSWORD);
            create(connection,dbName,dbTable,dbString);
            connection.close();
        } catch (SQLException | NullPointerException exception) {
            exception.printStackTrace();
            System.out.println("Что-то пошло не так...");
        }
    }

    private Connection open(String DBURL, String USER, String PASSWORD)
            throws NullPointerException, SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        Connection connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
        System.out.println("Подключение к базе данных прошло успешно!\n");
        return connection;
    }

    private void create(Connection connection, String dbName,
                        String dbTabele, String dbString)
            throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(dbString);
        System.out.println("Table " + dbTabele + " in DB " + dbName +
                " created successfully");
    }
    
}
