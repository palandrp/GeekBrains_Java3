package ru.geekbrains.java3.lesson1;

/**
 * Created by Home-pc on 03.08.2017.
 */
public class Main {
    public static void main(String[] args) {
//        Box box1 = new Box("string_____");
//        Box box2 = new Box(5);
//        box1.info();
//        box2.info();

        //...
        //...

        int x = 10;
//        x +=  (Integer) box2.getObj();
//        System.out.println(x);
//
//        x +=  (Integer) box1.getObj();   // Error
//        System.out.println(x);

//        BoxInt boxInt = new BoxInt(7);
//        x += boxInt.getObj();
//        System.out.println(x);

//        BoxGen<Integer> integerBoxGen = new BoxGen<>(6);
//        integerBoxGen.info();
//        x += integerBoxGen.getObj();
//        System.out.println(x);
//
//        BoxGen<String> stringBoxGen = new BoxGen<>("dfhgffdh");
//        stringBoxGen.info();
//
//        BoxGen<Long> longBoxGen = new BoxGen<>(9L);
//        longBoxGen.info();
//
//        BoxGen boxGen  = new BoxGen(35325);
//        boxGen.info();
//
//        List<Integer> integers = new ArrayList<>();


        MasConteiner<Integer> integerMasConteiner = new MasConteiner<>(1, 2, 3, 4, 5);
        System.out.println(integerMasConteiner);
        System.out.println(integerMasConteiner.avg());

        MasConteiner<Float> floatMasConteiner = new MasConteiner<>(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);
        System.out.println(floatMasConteiner);
        System.out.println(floatMasConteiner.avg());

        boolean isRavno = integerMasConteiner.equalsAvg(floatMasConteiner);
        System.out.println(isRavno);


    }
}
