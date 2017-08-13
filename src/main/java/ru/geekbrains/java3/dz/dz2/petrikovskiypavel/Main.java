package ru.geekbrains.java3.dz.dz2.petrikovskiypavel;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new MyConsoleAppForDB(new DBUse());
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
            " (title,cost) VALUES (?,?)";
//    private String tbDrop = "DROP TABLE " + dbTable;
    public Connection connection = null;
    
    void go() throws SQLException, NullPointerException {
        connection = null;
        connection = open(DBURL,USER,PASSWORD);
//            drop(connection);
        connection.setAutoCommit(false);
        create(dbName,dbTable,dbCreateString);
        clear(dbTable);
        for (int i=1; i < 10001; i++) {
            add(addString,"good" + i,i*10);
        }
        connection.commit();
    }
    private Connection open(String DBURL, String USER, String PASSWORD)
            throws NullPointerException, SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        Connection connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
        System.out.println("Подключение к базе данных прошло успешно!\n");
        return connection;
    }
    private void create(String dbName, String dbTabele, String dbString)
            throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(dbString);
        System.out.println("Таблица " + dbTabele + " в БД " + dbName +
                " успешно создана!");
    }
    private void add(String addString, String param1, Integer param2)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(addString);
        preparedStatement.setString(1,param1);
        preparedStatement.setInt(2,param2);
        preparedStatement.executeUpdate();
//        System.out.println("Запись добавлена в таблицу " + dbTable +
//                " в БД " + dbName + " успешно!");
    }
//    private void drop(Connection connection)
//            throws SQLException {
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(tbDrop);
//        System.out.println("Успешно удалена таблица " + dbTable +
//                " в БД " + dbName + "!");
//    }
    private void clear(String dbTable) throws SQLException {
    Statement statement = connection.createStatement();
    statement.executeUpdate("DELETE FROM " + dbTable);
    System.out.println(dbTable + " успешно очищена!");
    }
    public ResultSet dbGetCost(Connection connection, String getCostString,
                     String param1)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getCostString);
        preparedStatement.setString(1,param1);
        return preparedStatement.executeQuery();
    }
}

class MyConsoleAppForDB {

    MyConsoleAppForDB(DBUse db) {
        String getCostString = "SELECT cost FROM goods WHERE title=?";
        try {
            db.go();
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("Хай! Это консолька для взаимодействия с базой данных.\n" +
                    "Использование:\n" +
                    " /c 'товар' - показать цену товара,\n" +
                    " /cu 'товар' 'цена' - изменить цену товара,\n" +
                    " /di 'цена1' 'цена2' - вывести товары в указанном ценовом диапазоне;");
            String[] strings = scanner.nextLine().split(" ");
            switch (strings[0]){
                case "/c":
                    try {
                        ResultSet resultSet = db.dbGetCost(db.connection,
                                getCostString,strings[1]);
                        boolean flag = false;
                        while (resultSet.next()) {
                            flag = true;
                            String cost = resultSet.getString("cost");
                            System.out.println("Цена товара: " + cost);
                        }
                        if (!flag)
                            System.out.println("Нет такого товара: " + strings[1]);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Аппликушка сообщает об ошибке...");
                    }
                    break;
                case "/cu":
//                System.out.println("Выбор: /сu!");
                    break;
                case "/di":
//                System.out.println("Выбор: /di!");
                    break;
                default:
                    System.out.println("Нет такой команды!");
            }
        } catch (SQLException | NullPointerException exception) {
            exception.printStackTrace();
            System.out.println("Что-то пошло не так...");
            try {
                db.connection.rollback();
                System.out.println("Откат изменений!... Done!");
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
                System.out.println("Не удалось откатить транзакцию!");
            }
        } finally {
            try {
                db.connection.close();
                System.out.println("Закрываем соединение... Done!");
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
                System.out.println("Не удалось закрыть соединений!");
            }
        }
    }
}
