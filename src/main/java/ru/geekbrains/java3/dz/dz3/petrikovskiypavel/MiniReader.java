package ru.geekbrains.java3.dz.dz3.petrikovskiypavel;

import java.io.*;
import java.util.Scanner;

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
public class MiniReader {

    public static void main(String[] args) {
        new MiniReader().go();
    }
    void go() {
        Scanner scanner = new Scanner(System.in);
        try { toByteA(scanner.nextLine()); }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Что-то пошло не так...");
        }
    }
    byte[] toByteA(String name) throws IOException {
        File file = new File(name);
        byte[] byteA = new byte[(int)file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(byteA);
        return byteA;
    }

}
