package ru.geekbrains.java3.dz.dz4.petrikovskiypavel;

/**
 * Домашнее задание:
 * 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз, порядок
 *    должен быть именно ABСABСABС. Используйте wait/notify/notifyAll.
 * 2. Написать совсем небольшой метод, в котором 3 потока построчно пишут данные в файл (штук
 *    по 10 записей, с периодом в 20 мс)
 * 3. Написать класс МФУ, на котором возможны одновременная печать и сканирование
 *    документов, при этом нельзя одновременно печатать два документа или сканировать (при
 *    печати в консоль выводится сообщения "отпечатано 1, 2, 3,... страницы", при сканировании
 *    тоже самое только "отсканировано...", вывод в консоль все также с периодом в 50 мс.)
 *
 * @author Paul Petrikovskiy
 * @version 20 Aug 2017
 */
public class HomeWork4 {
    String ch = "";
    public static void main(String[] args) { new HomeWork4().go(); }
    void go(){
        Printer p = new Printer();
        new Thread(new Sender(p,'A')).start();
        new Thread(new Sender(p,'B')).start();
        new Thread(new Sender(p,'C')).start();
    }
}
class Printer {
    boolean wait = false;
    synchronized void print(char ch) throws InterruptedException {
        if (wait) wait();
        wait = true;
        System.out.print(ch);
    }
    synchronized void gogogo() throws InterruptedException {
        notify();
    }
}
class Sender implements Runnable {
    char ch = '0';
    Printer p;
    Sender(Printer printer, char ch) {
        this.p = printer;
        this.ch = ch;
    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                p.print(ch);
                p.gogogo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
