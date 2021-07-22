package top.uaian.jdk;

public class ShortDemo {
    public static void main(String[] args) {
        short i = 1;
        //i = i + 1; //编译报错 i+1 会自动提升类型
        i += 1; // +=会特殊处理
    }
}
