package ru.geekbrains.java3.lesson1;

/**
 * Created by Home-pc on 03.08.2017.
 */
public class BoxGen<T> {
    private T obj;

    public BoxGen(T obj) {
//        this.obj = new T();  //  error
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
}
