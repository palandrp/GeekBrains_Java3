package ru.geekbrains.java3.lesson1;

/**
 * Created by Home-pc on 03.08.2017.
 */
public class Box {
    private Object obj;

    public Box(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
    public void info() {
        System.out.println("obj: " + obj);
        System.out.println("type: " + obj.getClass());
    }

    public <T extends Number, Y extends Number> Double sum(T a, Y b) {
        return a.doubleValue()+b.doubleValue();
    }
}
