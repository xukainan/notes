package top.uaian.jdk;

public class YihuoDemo {
    public static void main(String[] args) {
        int a = 1, b=2;
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println(a);
        System.out.println(b);
    }
}
