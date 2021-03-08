package hello;

import java.util.Date;

public class Person {
    private String name = "Anonymous"; //default initialization in declaration

    public Person(String name){ //class constructor
        this.name = name;
    }

    public String sayHello() {
        return new Date() + ": Hello " + name;
    }

    @Override
    public String toString() {
        return "Person( name: " + name + " )";
    }

    public static void main(String[] args) {
        Person person1;
        person1 = new Person("Trayan");
        String message = person1.sayHello();
        System.out.println(message);

        Person person2 = new Person("Georgi");
        System.out.println(person2);
        System.out.println(person2.sayHello());
    }
}
