package ru.geekbrains.java3.dz.dz1.petrikovskiypavel;

public class Fruit {
    float weight;

    Fruit(float weight) {
        this.weight = weight;
    }

    float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "" + weight;
    }
}
