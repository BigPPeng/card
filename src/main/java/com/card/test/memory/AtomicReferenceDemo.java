package com.card.test.memory;

import com.google.common.util.concurrent.AtomicDouble;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by cuihp on 2020/3/5.
 */
public class AtomicReferenceDemo {


    public static void main(String[] args) {
        AtomicReference<Student> atomicReference = new AtomicReference<>();
        Student student = new Student(1,"11");
        Student student2 = new Student(2,"22");
        atomicReference.set(student);



        boolean b = atomicReference.compareAndSet(student, student2);
        System.out.println(b + " " + atomicReference.get().toString());
        Student student3 = new Student(3,"33");
        boolean bb = atomicReference.compareAndSet(student, student3);
        System.out.println(bb + " " + atomicReference.get().toString());

    }







}



class Student{
    int age;
    String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}