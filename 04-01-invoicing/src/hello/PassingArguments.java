package hello;

class MyInt{
    int n;
    public MyInt(int n) {
        this.n = n;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyInt{");
        sb.append("n=").append(n);
        sb.append('}');
        return sb.toString();
    }
}

public class PassingArguments {
    public static void f(MyInt x) { // formal argument
        x.n = 42;
    }
    public static void main(String[] args) {
        MyInt m = new MyInt(2);
        System.out.println(m);
        f(m); // actual argument
        System.out.println(m);
    }
}
