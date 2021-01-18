package hello;

import java.util.Date; // Fully qualified class name

public class Hello {
    private String sayHello(String name) {
        return "Hi, " + name + " from Java! - " + new Date();
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        String message = hello.sayHello("Trayan") ;
        System.out.println(message);
    }
}
