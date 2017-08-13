package ru.geekbrains.java3.dz.dz2.petrikovskiypavel;

import java.sql.*;


public class DateBase {
    public static String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    public static String user = "postgres";
    public static String password = "postgres";
    final String NAME_DB = "store.db";
    final String TABLE_DB = "GOODS";
    public static final String SQL_SELECT_ALL = "SELECT login, nickname, password  FROM main";
    public static final String SQL_SELECT =
            "SELECT login, nickname, password  FROM main where login = ? ";
    final String CREATE_TBL =
            "CREATE TABLE if not exists " + TABLE_DB +
                    "(ID INTEGER PRIMARY KEY," +
                    " GRP1  TEXT," +
                    " GRP2  TEXT," +
                    " GRP3  TEXT," +
                    " GRP4  TEXT," +
                    " GRP5  TEXT," +
                    " NAME  TEXT NOT NULL," +
                    " VCODE TEXT," +
                    " FNAME TEXT," +
                    " PRICE INTEGER)";


    public void getConnectionToDB(String dbUrl, String user, String password, String login) {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            if (connection != null) {
                System.out.println("connection to db");
                System.out.println("Подключение к базе данных прошло успешно!\n");
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
                if (login != null) {
                    findLogin(preparedStatement, login);
//                    findLogin(statement, login);
                } else {
                    showFullTableColumnNumber(statement);
//                showFullTable(statement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void findLogin(PreparedStatement preparedStatement, String inLogin) {
        try {
            preparedStatement.setString(1, inLogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String nickname = resultSet.getString("nickname");
                String password = resultSet.getString("password");
                System.out.println(login + " " + nickname + " " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void findLogin(Statement statement, String inLogin) {
        try {
            String sql = SQL_SELECT_ALL + " where login = '" + inLogin + "'";
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String nickname = resultSet.getString("nickname");
                String password = resultSet.getString("password");
                System.out.println(login + " " + nickname + " " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showFullTableColumnNumber(Statement statement) {
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                String login = resultSet.getString(1);
                String nickname = resultSet.getString(2);
                String password = resultSet.getString(3);
                System.out.println(login + " " + nickname + " " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showFullTable(Statement statement) {
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String nickname = resultSet.getString("nickname");
                String password = resultSet.getString("password");
                System.out.println(login + " " + nickname + " " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void open(String nameDB) { // open connection or create DB
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection("jdbc:sqlite:" + nameDB);
            System.out.println("Opening DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void createTable(String nameDB, String tableDB, String createTbl) { // create table
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate(createTbl);
            stmt.close();
            System.out.println("Table " + tableDB + " in DB " + nameDB + " created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void add(String nameDB, String tableDB, String[] fields) { // add record
        try {
            stmt = connect.createStatement();
            String sql =
                    "INSERT INTO " + tableDB +
                            " (GRP1,GRP2,GRP3,GRP4,GRP5,NAME,ID,VCODE,FNAME,PRICE)" +
                            " VALUES ('" +
                            fields[0] + "', '" + // GRP1
                            fields[1] + "', '" + // GRP2
                            fields[2] + "', '" + // GRP3
                            fields[3] + "', '" + // GRP4
                            fields[4] + "', '" + // GRP5
                            fields[5] + "', " +  // NAME
                            fields[6] + ", '" +  // ID
                            fields[7] + "', '" + // VCODE
                            fields[8] + "', " +  // FNAME
                            fields[9] + ");";    // PRICE
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Record added to the table " + tableDB + " in DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update(String nameDB, String tableDB, String[] fields) { // update record
        try {
            stmt = connect.createStatement();
            String sql = "UPDATE " + tableDB + " set " +
                    "GRP1='" + fields[0] + "'," +
                    "GRP2='" + fields[1] + "'," +
                    "GRP3='" + fields[2] + "'," +
                    "GRP4='" + fields[3] + "'," +
                    "GRP5='" + fields[4] + "'," +
                    "PRICE=" + fields[9] + " where ID='" + fields[6] + "';";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Record updated in the table " + tableDB + " in DB " + nameDB + " successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void close() { // close connection
        try {
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

