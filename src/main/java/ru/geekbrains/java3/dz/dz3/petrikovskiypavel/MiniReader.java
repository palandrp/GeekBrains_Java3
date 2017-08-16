package ru.geekbrains.java3.dz.dz3.petrikovskiypavel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Домашнее задание:
 * 1) Прочитать файл(около 50 байт) в байтовый массив и вывести этот массив в консоль;
 * 2) Последовательно сшить 10 файлов в один(файлы также ~100 байт).
 * Может пригодиться следующая конструкция:
 * ArrayList<FileInputStream> al = new ArrayList<>();
 * ...
 * Enumeration<FileInputStream> e = Collections.enumeration(al);
 * 3) Написать консольное приложение, которое умеет постранично читать текстовые
 * файлы(размером > 10 mb), вводим страницу, программа выводит ее в консоль(за страницу
 * можно принимаем 1800 символов). Время чтения файла должно находится в разумных
 * пределах(программа не должна загружаться дольше 10 секунд), ну и чтение тоже не
 * должно занимать >5 секунд.
 * Чтобы не было проблем с кодировкой используйте латинские буквы.
 *
 * @author Paul Petrikovskiy
 * @version 16 Aug 2017
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello java!");
    }

    class MiniReader {
        String filename = "";

        MiniReader(String filename) {
            this.filename = filename;
        }
        void outToConsole() throws IOException {
            StringBuilder sb = new StringBuilder("");
            BufferedInputStream in =
                    new BufferedInputStream(new FileInputStream(filename));
            int x;
            do {
                x = in.read();
                sb.append((char)x);
            } while (x != -1);
        }
        void concatFiles() {

        }
    }

    class ConsoleApp {

    }
}
