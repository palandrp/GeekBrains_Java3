package ru.geekbrains.java3.dz.dz1.petrikovskiypavel;

public class Main {
    static public void main(String[] args) {
        MasConteinerDZ<Object> mC = new MasConteinerDZ<>(234,"Howk",531,"Maffet",42.42);
        mC.moveElelents(0,3);
        System.out.println(mC.toString());
        System.out.println();
        System.out.println(mC.masToArrList(mC.getMas()).toString());
    }
}
