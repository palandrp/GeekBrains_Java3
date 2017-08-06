package ru.geekbrains.java3.dz.dz1.petrikovskiypavel;

import java.util.Arrays;

/*
 *  @author Petrikovskiy Pavel
 *  @version 20170806
 *  Метод для перемещения элементов:
 *  moveElements();
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
