package hello;

public class Hello {
    private String name = "Anonymous"; //default initialization in declaration

    public Hello(String name){ //class constructor
        this.name = name;
    }

    public String sayHello() {
        return "Hello " + name;
    }

    @Override
    public String toString() {
        return "Hello( name: " + name + " )";
    }

    public static void main(String[] args) {
        Hello hello;
        hello = new Hello("Trayan");
        String message = hello.sayHello();
        System.out.println(message);

        Hello hello2 = new Hello("Georgi");
        System.out.println(hello2);
        System.out.println(hello2.sayHello());
    }
}
