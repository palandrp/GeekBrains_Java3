package ru.geekbrains.java3.dz.dz3.petrikovskiypavel;

import java.io.*;
import java.util.Arrays;
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
 * @version 17 Aug 2017
 */
public class MiniReader {

    public static void main(String[] args) {
        new MiniReader().go();
    }
    void go() {
//        System.out.println("Введите имя файла: ");
        Scanner scanner = new Scanner(System.in);
//        String filename = scanner.nextLine();
        try {
            // Задание 1
//            toByteA(filename);
            // Задание 2
//            FileOutputStream out = new FileOutputStream("New File.txt", true);
//            for (int i = 0; i < 10; i++) {
//                byte[] bA = toByteA(filename);
//                out.write(bA);
//            }
            // Задание 3
            // Читаем страницу из большого файла,
            // Для простоты используем созданный в задании 2 файл,
            // который называется New File.txt
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
            raf.seek(x*1800);
            for (int i = 0; i < 1800; i++) {
                System.out.print((char) raf.read());
            }
            System.out.println();
            System.out.println("Следующая страница,");
            System.out.print("(-1 для выхода, номер первой страницы - \"0\"): ");
            x = sc.nextInt();
        }
    }
}
