package ru.geekbrains.java3.dz.dz1.petrikovskiypavel;

import java.util.ArrayList;
import java.util.Arrays;

public class FruitsBox<T extends Fruit> {
    private ArrayList<T> aL = new ArrayList<>();

    FruitsBox(T... mas) {
        aL.addAll(Arrays.asList(mas));
    }

    float getWeight() {
        float boxWeight = 0.0f;
        for (T e : aL) {
            boxWeight += e.getWeight();
        }
        return boxWeight;
    }

    boolean compare(FruitsBox<? extends Fruit> boxF) {
        if (this.getWeight() == boxF.getWeight()) return true;
        else return false;
    }

    void pour(FruitsBox<T> boxF) {
        this.aL.addAll(boxF.getAllFruitsAndClearTheBox().subList(0,boxF.getAllFruitsAndClearTheBox().size()));
    }

    private ArrayList<T> getAllFruitsAndClearTheBox() {
        try {
            return this.aL;
        }
        finally {
            this.aL.clear();
        }
    }

    public void addFruit(T fr) {
        aL.add(fr);
    }

    @Override
    public String toString() {
        return "Фрукты в коробке: " +
                aL.toString();
    }
}
