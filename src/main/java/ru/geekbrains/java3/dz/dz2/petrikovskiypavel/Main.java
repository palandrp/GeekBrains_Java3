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
    private String dbTable = "Goods";
    private String dbCreateString = "CREATE TABLE if not exists " + dbTable +
            " (id SERIAL PRIMARY KEY NOT NULL, prodID SERIAL NOT NULL," +
            " title TEXT NOT NULL, cost INTEGER NOT NULL)";
    private String addString =
            "INSERT INTO " + dbTable +
            " (title,cost) VALUES ('good1',10)";
//    private String tbDrop = "DROP TABLE " + dbTable;
    
    void go() {
        Connection connection = null;
        try {
            connection = open(DBURL,USER,PASSWORD);
//            drop(connection);
            connection.setAutoCommit(false);
            create(connection,dbName,dbTable,dbCreateString);
            add(connection,addString);
            connection.commit();
        } catch (SQLException | NullPointerException exception) {
            exception.printStackTrace();
            System.out.println("Что-то пошло не так...");
            try {
                connection.rollback();
                System.out.println("Откат изменений!... Done!");
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
                System.out.println("Не удалось откатить транзакцию!");
            }
        } finally {
            try {
                connection.close();
                System.out.println("Закрываем соединение... Done!");
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
                System.out.println("Не удалось закрыть соединений!");
            }
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
        System.out.println("Таблица " + dbTabele + " в БД " + dbName +
                " успешно создана!");
    }
    private void add(Connection connection, String addString)
            throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(addString);
        System.out.println("Запись добавлена в таблицу " + dbTable +
                " в БД " + dbName + " успешно!");
    }
//    private void drop(Connection connection)
//            throws SQLException {
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(tbDrop);
//        System.out.println("Успешно удалена таблица " + dbTable +
//                " в БД " + dbName + "!");
//    }
}
