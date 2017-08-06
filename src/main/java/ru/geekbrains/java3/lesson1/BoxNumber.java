package ru.geekbrains.java3.lesson1;

import java.io.Serializable;

/**
 * Created by Home-pc on 03.08.2017.
 */
public class BoxNumber<T extends Number & Serializable> {
    private T obj;

    public BoxNumber(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }


    public void info() {
        System.out.println("obj: " + obj);
        System.out.println("type: " + obj.getClass());
    }

    public int rang() {
        return obj.intValue();
    }
}
