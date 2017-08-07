package ru.geekbrains.java3.dz.dz1.petrikovskiypavel;

public class Apple extends Fruit {

    Apple(float weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return "Яблоко весом " + weight + "; ";
    }
}
