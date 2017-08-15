package ru.geekbrains.java3.lesson3;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String phone;
    private transient String password;

    private Zachetka z;

    public Zachetka getZ() {
        return z;
    }

    public Student(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.z = new Zachetka("123");
        System.out.println("Constructor");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (!phone.equals(student.phone)) return false;
        if (!password.equals(student.password)) return false;
        return z.equals(student.z);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + phone.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + z.hashCode();
        return result;
    }

    public void info() {
        System.out.println(name + " " + phone + " " + z.getId());
    }
}
