package top.uaian.jdk;

public class Statictest {
    static int a = 0;
    static {
        a = 1;
        b = 1;
    }
    static int b = 0;

    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(b);
    }
}
