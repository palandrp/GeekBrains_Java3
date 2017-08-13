package ru.geekbrains.java3.lesson1;

import java.util.Arrays;

/**
 * Created by Home-pc on 03.08.2017.
 */
public class MasConteiner<T extends Number> {
    private T[] mas;

    double avg() {
        if (mas.length == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (T item : mas) {
            sum += item.doubleValue();
        }
        return sum / mas.length;
    }

    boolean equalsAvg(MasConteiner<? extends Number> masConteiner) {
        return avg() == masConteiner.avg();
    }


    MasConteiner(T... mas) {
//        this.mas = new T[8];  // error
        this.mas = mas;
    }

    public T[] getMas() {
        return mas;
    }

    public void setMas(T[] mas) {
        this.mas = mas;
    }

    @Override
    public String toString() {
        return "MasConteiner{" +
                "mas=" + Arrays.toString(mas) +
                '}';
    }
}
