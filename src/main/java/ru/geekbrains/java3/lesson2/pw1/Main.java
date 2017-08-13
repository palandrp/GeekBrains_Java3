package ru.geekbrains.java3.lesson2.pw1;


public class Main {
    public static void main(String[] args) {
        DateBase dateBase = new DateBase();
        dateBase.getConnectionToDB(DateBase.dbUrl,
                DateBase.user,
                DateBase.password, "log1");
    }
}
