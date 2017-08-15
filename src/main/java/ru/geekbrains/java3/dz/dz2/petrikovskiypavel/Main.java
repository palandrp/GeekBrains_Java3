package ru.geekbrains.java3.dz.dz2.petrikovskiypavel;

/**
 * Java 3. Лекция 2. Домашняя работа.
 * 1. Создает БД, затем таблицу, затем помещает в таблицу
 *    10000 записей.
 * 2. Класс который создает и держит соединение имеет
 *    публичные методы для работы с выбранной таблицей.
 *    Но за разрыв соединения с БД отвечает пользовательское
 *    приложение.
 * 3. Пользовательское приложение имеет три метода для
 *    работы с данными представленной таблицы. Логика работы
 *    с таблицей разделена на две части, одну предоставляет
 *    хендлер БД, в этой части выполняется работа с соединением
 *    и SQL-стетментом. Вторая часть реализована в пользовательском
 *    консольном приложении и отвечает за работу с результатами
 *    выполнения запроса к БД.
 *
 * @author Paul Petrikovskiy
 * @version 14 Aug 2017
 */
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new MyConsoleAppForDB(new DBHandler());
    }
}
class DBHandler {
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
    public Connection connection = null;
    void go() throws SQLException, NullPointerException {
        connection = null;
        connection = open(DBURL,USER,PASSWORD);
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
    private void create(String dbName, String dbhTabele, String dbhString)
            throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(dbhString);
        System.out.println("Таблица " + dbhTabele + " в БД " + dbName +
                " успешно создана!");
    }
    private void add(String addString, String param1, Integer param2)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(addString);
        preparedStatement.setString(1,param1);
        preparedStatement.setInt(2,param2);
        preparedStatement.executeUpdate();
    }
    private void clear(String dbTable)
            throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM " + dbTable);
        System.out.println(dbTable + " успешно очищена!");
    }
    public ResultSet dbhGetCost(String getCostString, String param1)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getCostString);
        preparedStatement.setString(1,param1);
        return preparedStatement.executeQuery();
    }
    public void upTabelString(String upTableString, Integer param1, String param2)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(upTableString);
        preparedStatement.setInt(1,param1);
        preparedStatement.setString(2,param2);
        preparedStatement.executeUpdate();
        connection.commit();
    }
    public ResultSet showListString(String showListString, Integer param1, Integer param2)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(showListString);
        preparedStatement.setInt(1,param1);
        preparedStatement.setInt(2,param2);
        return preparedStatement.executeQuery();
    }
}

class MyConsoleAppForDB {
    MyConsoleAppForDB(DBHandler dbh) {
        String getCostString = "SELECT cost FROM goods WHERE title=?";
        String upTableString = "UPDATE goods SET cost=? WHERE title=?";
        String showListString = "SELECT title,cost FROM goods " +
                "WHERE cost>=? AND cost<=?";
        try {
            dbh.go();
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("Хай! Это консолька для взаимодействия с базой данных.\n" +
                    "Использование:\n" +
                    " /c товар - показать цену товара,\n" +
                    " /cu товар цена - изменить цену товара,\n" +
                    " /di цена1 цена2 - вывести товары в указанном ценовом диапазоне;");
            String[] strings = scanner.nextLine().split(" ");
            switch (strings[0]){
                case "/c":
                    try {
                        ResultSet resultSet = dbh.dbhGetCost(getCostString,strings[1]);
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
                    try {
                        dbh.upTabelString(upTableString,Integer.parseInt(strings[2]),strings[1]);
                        System.out.println("Вроде все хорошо...");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Аппликушка сообщает об ошибке...");
                    }
                    break;
                case "/di":
                    try {
                        ResultSet resultSet = dbh.showListString(showListString,
                                Integer.parseInt(strings[1]),
                                Integer.parseInt(strings[2]));
                        while (resultSet.next()) {
                            String title = resultSet.getString("title");
                            String cost = resultSet.getString("cost");
                            System.out.println("Товар: " + title + " " + "Цена: " + cost);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Аппликушка сообщает об ошибке...");
                    }
                    break;
                default:
                    System.out.println("Нет такой команды!");
            }
        } catch (SQLException | NullPointerException exception) {
            exception.printStackTrace();
            System.out.println("Что-то пошло не так...");
            try {
                dbh.connection.rollback();
                System.out.println("Откат изменений!... Done!");
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
                System.out.println("Не удалось откатить транзакцию!");
            }
        } finally {
            try {
                dbh.connection.close();
                System.out.println("Закрываем соединение... Done!");
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
                System.out.println("Не удалось закрыть соединений!");
            }
        }
    }
}
