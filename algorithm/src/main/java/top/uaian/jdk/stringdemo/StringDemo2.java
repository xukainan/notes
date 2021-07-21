package top.uaian.jdk.stringdemo;

public class StringDemo2 {
    public static void main(String[] args) {
        String s1 = new StringBuilder("ja").append("va").toString();
        System.out.println(s1 == s1.intern()); //false java是关键字，在String Pool中存在
        String s2 = new StringBuilder("xu").append("kainan").toString();
        System.out.println(s2 == s2.intern()); //true xukainan在池中没有，会直接返回heap的引用
        String s3 = new String("test");
        System.out.println(s3 == s3.intern()); //false test作为字面量，放入String Pool中，而new时，s3指向的是heap中新生成的String对象
        String s4 = new StringBuilder("abc").toString();
        System.out.println(s4 == s4.intern()); //false 同上
        String s5 = new StringBuilder("dd").append("ee").toString();
        System.out.println(s5 == s5.intern()); //true

        String a = "Alice"; //String Pool
        String b = "Bob"; //String Pool

        String a1 = "Alice"; //String Pool
        String b1 = new String("Bob"); //heap

        System.out.println("a == a1? " + (a == a1)); //a == a1 ?true
        System.out.println("b == b1? " + (b == b1)); //b == b1 ?false
    }
}
