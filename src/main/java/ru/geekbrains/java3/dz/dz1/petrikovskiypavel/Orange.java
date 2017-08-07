package ru.geekbrains.java3.dz.dz1.petrikovskiypavel;

public class Orange extends Fruit {

    Orange(float weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return "Аппельсин весом " + weight + "; ";
    }
}
