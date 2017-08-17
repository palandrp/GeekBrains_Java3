package ru.geekbrains.java3.dz.dz3.petrikovskiypavel;

import java.io.*;
import java.util.Scanner;

/**
 * Домашнее задание:
 * 1. Метод читает файл в массив байт.
 * 2. Склеивание 10 файлов в один. Для простоты в цикле используется
 *    один и тот же файл 10 раз. Но можно заморочиться и указывать 10
 *    разных имен файлов при желании.
 * 3. Чтение файла из консоли постранично, за страницу принято 1800
 *    символов, соответственно указатель двигается на 3600 байт и
 *    затем считывает 3600 байт конвертируя в символы.
 *
 * @author Paul Petrikovskiy
 * @version 17 Aug 2017
 */
public class MiniReader {

    public static void main(String[] args) {
        new MiniReader().go();
    }
    void go() {
        System.out.println("Введите имя файла: ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        try {
            // Задание 1
            toByteA(filename);
            // Задание 2
            FileOutputStream out = new FileOutputStream("New File.txt", true);
            for (int i = 0; i < 10; i++) {
                byte[] bA = toByteA(filename);
                out.write(bA);
            }
            // Задание 3
            // Читаем страницу из большого файла,
            // Для простоты используем созданный в задании 2 файл,
            // который называется "New File.txt"
            new ConsoleApp("New File.txt",scanner);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Что-то пошло не так... Скорее всего неверное имя файла.");
        }
    }
    byte[] toByteA(String name) throws IOException {
        File file = new File(name);
        byte[] byteA = new byte[(int)file.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        bis.read(byteA);
        return byteA;
    }

    class ConsoleApp {
        ConsoleApp(String filename, Scanner sc) throws IOException {
            print(filename,sc);
        }
    }
    private void print(String filename, Scanner sc) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(filename, "r");
        int x = 0;
        while (x != -1) {
            raf.seek(x*3600);
            for (int i = 0; i < 3600; i++) {
                System.out.print((char) raf.read());
            }
            System.out.println();
            System.out.println("Следующая страница,");
            System.out.print("(-1 для выхода, номер первой страницы - \"0\"): ");
            x = sc.nextInt();
        }
    }
}
