package ru.geekbrains.java3.dz.dz1.petrikovskiypavel;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *  @author Petrikovskiy Pavel
 *  @version 20170806
 *  Метод для перемещения элементов:
 *  moveElements(int a, int b);
 *  Метод для помещения массива в
 *  ArrayList:
 *  masToArrList(T... mas);
 */
public class MasConteinerDZ<T> {
    private T[] mas;

    public MasConteinerDZ(T... mas) {
        this.mas = mas;
    }

    void moveElelents(int a, int b) {
        if (a > mas.length-1 || b > mas.length-1) {
            System.out.println("Номер элемента выходит за границы массива.");
            return;
        }
        T temp1 = mas[a];
        mas[a] = mas[b];
        mas[b] = temp1;
    }

    ArrayList masToArrList(T... mas) {
        ArrayList<T> aL = new ArrayList<>();
        for (T e : mas) {
            aL.add(e);
        }
        return aL;
    }

    public T[] getMas() {
        return mas;
    }

    public void setMas(T[] mas) {
        this.mas = mas;
    }

    @Override
    public String toString() {
        return "MasConteinerDZ{" +
                "mas=" + Arrays.toString(mas) +
                '}';
    }
}
