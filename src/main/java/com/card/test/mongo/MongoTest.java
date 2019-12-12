package com.card.test.mongo;


import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoTest {


    public MongoTest() {
    }

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        MongoTemplate instance = MongoTemplateFactory.getInstance();
        System.out.println("use:"+(System.currentTimeMillis() - start1) +"MS");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++ ) {
            Student student = new Student("cui"+i,i,new Student("cui"+i,i-10));
            instance.insert(student,"testJava");
        }
        System.out.println("use:"+(System.currentTimeMillis() - start) +"MS");
        System.out.println("all use:"+(System.currentTimeMillis() - start1) +"MS");

    }


    static class Student {
        String name;
        int age;
        Student student;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Student(String name, int age, Student student) {
            this.name = name;
            this.age = age;
            this.student = student;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
    }


}
