package ru.geekbrains.java3.dz.dz1.petrikovskiypavel;

public class Main {
    static public void main(String[] args) {
        MasConteinerDZ<Object> mC = new MasConteinerDZ<>(234,"Howk",531,"Maffet",42.42);
        mC.moveElelents(0,3);
        System.out.println(mC.toString());
        System.out.println();
        System.out.println(mC.masToArrList(mC.getMas()).toString());

        FruitsBox<Apple> appleBox1 = new FruitsBox<>(   new Apple(1.1f),
                                                        new Apple(1.2f),
                                                        new Apple(1.3f),
                                                        new Apple(1.4f),
                                                        new Apple(1.0f),
                                                        new Apple(0.9f));

        FruitsBox<Apple> appleBox2 = new FruitsBox<>(   new Apple(1.13f),
                                                        new Apple(1.22f),
                                                        new Apple(1.31f),
                                                        new Apple(1.45f),
                                                        new Apple(1.06f),
                                                        new Apple(0.97f));

        FruitsBox<Orange> orangeBox = new FruitsBox<>(  new Orange(1.5f),
                                                        new Orange(1.6f),
                                                        new Orange(1.7f),
                                                        new Orange(1.55f),
                                                        new Orange(1.56f),
                                                        new Orange(0.57f));

        System.out.println(appleBox1.toString());
        System.out.println();
        System.out.println(appleBox2.toString());
        System.out.println();
        System.out.println(orangeBox.toString());
        System.out.println();
        appleBox1.pour(appleBox2);
        System.out.println(appleBox1.toString());
        System.out.println();
        System.out.println(appleBox2.toString());
    }
}
